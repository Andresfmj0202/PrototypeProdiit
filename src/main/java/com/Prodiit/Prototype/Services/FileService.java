package com.Prodiit.Prototype.Services;

import com.Prodiit.Prototype.Exceptions.PDALProcessingException;
import com.Prodiit.Prototype.Models.Dtos.FileDTO;
import com.Prodiit.Prototype.Models.Entitys.FileEntity;
import com.Prodiit.Prototype.Respositorys.FileRepository;
import io.pdal.Pipeline;
import io.pdal.PointView;
import io.pdal.PointViewIterator;
import io.pdal.pipeline.ReaderType;
import io.pdal.pipeline.WriterType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
public class FileService {

    @Autowired
    private FileRepository fileRepository;

    public FileDTO createAndSaveFile(FileDTO fileDTO) {
        FileEntity entity = fileRepository.save(mapToEntity(fileDTO));
        return mapToDTO(entity);
    }

    public FileEntity mapToEntity(FileDTO fileDTO) {
        FileEntity entity = new FileEntity();
        entity.setFileId(UUID.randomUUID());
        entity.setName(fileDTO.getName());
        entity.setType(fileDTO.getType());
        entity.setSize(fileDTO.getSize());
        // Mapea otros campos si es necesario
        return entity;
    }

    public FileDTO mapToDTO(FileEntity fileEntity) {
        FileDTO dto = new FileDTO();
        dto.setFileId(fileEntity.getFileId());
        dto.setName(fileEntity.getName());
        dto.setType(fileEntity.getType());
        dto.setSize(fileEntity.getSize());
        return dto;
    }

    public FileDTO createAndSaveFileWithPDAL(MultipartFile file, String pdalJson) {
        FileEntity entity = new FileEntity();

        try {
            // Crear el pipeline PDAL
            Pipeline pipeline = new Pipeline(pdalJson, 8);

            // Agregar el lector LAS para procesar el archivo LAZ
            PipelineManager manager = pipeline.getManager();
            manager.addReader(ReaderType.LasReader);

            // Agregar el escritor
            manager.addWriter(WriterType.LasWriter);

            // Asignar el archivo LAZ al lector
            manager.getStage("readers.las").addInputFile(file.getBytes());

            // Validar la tubería
            pipeline.validate();
            pipeline.execute();

            // Obtener el punto de vista después de ejecutar la tubería
            PointView pointView = getFirstPointView(pipeline);

            // Aquí puedes procesar el punto de vista PDAL según tus necesidades
            // Por ejemplo, obtener datos de las coordenadas X e Y
            double x = pointView.getDouble(0, "X");
            double y = pointView.getDouble(0, "Y");

            // Aquí puedes recuperar los resultados de la operación y actualizar la entidad si es necesario

            // entity = fileRepository.save(entity);

            // Cerrar el punto de vista y el pipeline
            pointView.close();
            pipeline.close();

            // Devolver los datos procesados en fileDTO
            return mapToDTO(entity);
        }catch (Exception e) {
            throw new PDALProcessingException("Error procesando el archivo con PDAL", e);
        }
    }

    private PointView getFirstPointView(Pipeline pipeline) {
        PointViewIterator pointViewIterator = pipeline.getPointViews();
        if (pointViewIterator.hasNext()) {
            return pointViewIterator.next();
        }
        return null;
    }
}

