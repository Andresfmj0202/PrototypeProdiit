package com.Prodiit.Prototype.Controllers;

import com.Prodiit.Prototype.Models.Dtos.FileDTO;
import com.Prodiit.Prototype.Services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
public class FileController {

    private final FileService fileService;

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

//    @PostMapping("/processPointCloud")
//    public FileDTO uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("pdalJson") String pdalJson) {
//        return fileService.createAndSaveFileWithPDAL(file, pdalJson);
//    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        return fileService.uploadFile(file);
    }
}
