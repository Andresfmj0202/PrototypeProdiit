package com.Prodiit.Prototype.Services;

import com.Prodiit.Prototype.Models.Dtos.UserDTO;
import com.Prodiit.Prototype.Models.Entitys.RoleEntity;
import com.Prodiit.Prototype.Models.Entitys.UserEntity;
import com.Prodiit.Prototype.Respositorys.RoleRepository;
import com.Prodiit.Prototype.Respositorys.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDTO createAndSaveUser(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setName(userDTO.getName());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setImage(userDTO.getImage());

        // Establecer el valor predeterminado de status en true
        userEntity.setStatus(true);

        // Obtener o asignar el objeto RoleEntity (dependiendo de tu lógica)
        RoleEntity roleEntity = roleRepository.getReferenceById(userDTO.getRoleId());
        userEntity.setRole(roleEntity);

        // Generar un ID único y asignarlo a la entidad
        userEntity.setUserId(UUID.randomUUID());

        String password = userDTO.getPassword(); // Asegúrate de que getPassword() obtenga la contraseña

        if (password != null) {
            // Genera una contraseña segura con Bcrypt
            String hashedPassword = passwordEncoder.encode(password);
            userEntity.setPassword(hashedPassword);
        } else {
            // Manejar el caso en el que la contraseña es nula
            throw new IllegalArgumentException("La contraseña no puede ser nula.");
            // Puedes lanzar una excepción, establecer una contraseña por defecto, etc.
        }

        UserEntity createdUserEntity = userRepository.save(userEntity);

        UserDTO createdUserDTO = new UserDTO();
        createdUserDTO.setUserId(createdUserEntity.getUserId());
        createdUserDTO.setName(createdUserEntity.getName());
        createdUserDTO.setEmail(createdUserEntity.getEmail());
        createdUserDTO.setImage(createdUserEntity.getImage());
        createdUserDTO.setRoleId(createdUserEntity.getRole().getRoleId());
        createdUserDTO.setStatus(createdUserEntity.isStatus()); // Agregar el estado

        return createdUserDTO;
    }
    // Obtener todos los usuarios
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public List<UserDTO> mapToDTOList(List<UserEntity> userEntities) {
        List<UserDTO> userDTOs = new ArrayList<>();
        for (UserEntity userEntity : userEntities) {
            userDTOs.add(mapToDTO(userEntity));
        }
        return userDTOs;
    }


    public List<UserDTO> mapUserEntitiesToDTOs(List<UserEntity> userEntities) {
        List<UserDTO> userDTOs = new ArrayList<>();
        for (UserEntity userEntity : userEntities) {
            userDTOs.add(mapUserEntityToDTO(userEntity));
        }
        return userDTOs;
    }
    public UserDTO mapUserEntityToDTO(UserEntity userEntity) {
        return new UserDTO(
                userEntity.getUserId(),
                userEntity.getName(),
                userEntity.getEmail(),
                userEntity.getImage(),
                userEntity.getRole().getRoleId(),
                userEntity.getPassword(),
                userEntity.isStatus()
                // Ajusta esto según tu modelo de datos
        );
    }

    public UserDTO mapToDTO(UserEntity userEntity) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(userEntity.getUserId());
        userDTO.setName(userEntity.getName());
        userDTO.setEmail(userEntity.getEmail());
        userDTO.setRoleId(userEntity.getRole().getRoleId());
        userDTO.setPassword(userEntity.getPassword());
        userDTO.setImage(userEntity.getImage());

        return userDTO;
    }

    public Optional<UserEntity> findUserById(UUID id) {
        return userRepository.findById(id);
    }

    public List<UserEntity> findByname(String name) {
        return userRepository.findByname(name);
    }

    public List<UserEntity> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    public UserEntity updateUserCompany(UUID id, UserEntity user){
        user.setUserId(id);
        return userRepository.save(user);
    }
    public UserDTO updateUser(UUID userId, UserDTO updatedUserDTO) {
        // Recupera el usuario existente de la base de datos
        UserEntity existingUserEntity = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));

        // Actualiza los campos del usuario con la información de UserDTO
        existingUserEntity.setName(updatedUserDTO.getName());
        existingUserEntity.setEmail(updatedUserDTO.getEmail());
        existingUserEntity.setImage(updatedUserDTO.getImage());
        existingUserEntity.setStatus(updatedUserDTO.isStatus()); // Actualizar el estado

        // Actualiza el campo "role" utilizando el ID proporcionado en UserDTO
        long roleId = updatedUserDTO.getRoleId();
        RoleEntity roleEntity = roleRepository.findById(roleId)
                .orElseThrow(() -> new EntityNotFoundException("Rol no encontrado"));
        existingUserEntity.setRole(roleEntity);

        // Guarda el usuario actualizado en la base de datos
        UserEntity updatedUserEntity = userRepository.save(existingUserEntity);

        // Convierte el usuario actualizado en UserDTO y devuélvelo
        UserDTO updatedUserResultDTO = new UserDTO();
        updatedUserResultDTO.setUserId(updatedUserEntity.getUserId());
        updatedUserResultDTO.setName(updatedUserEntity.getName());
        updatedUserResultDTO.setEmail(updatedUserEntity.getEmail());
        updatedUserResultDTO.setImage(updatedUserEntity.getImage());
        updatedUserResultDTO.setRoleId(updatedUserEntity.getRole().getRoleId());
        updatedUserResultDTO.setStatus(updatedUserEntity.isStatus()); // Agregar el estado

        return updatedUserResultDTO;
    }
    public void deleteUser(UUID id){
        userRepository.deleteById(id);
    }

    public boolean statusUser(UUID id){
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
        userEntity.setStatus(!userEntity.isStatus());
        userRepository.save(userEntity);
        return userEntity.isStatus();
    }


}
