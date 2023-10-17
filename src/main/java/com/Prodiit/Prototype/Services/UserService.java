package com.Prodiit.Prototype.Services;

import com.Prodiit.Prototype.Models.Dtos.UserDTO;
import com.Prodiit.Prototype.Models.Entitys.RoleEntity;
import com.Prodiit.Prototype.Models.Entitys.UserEntity;
import com.Prodiit.Prototype.Respositorys.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.*;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO createAndSaveUser(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setName(userDTO.getName());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setImage(userDTO.getImage());

        // Obtener o asignar el objeto RoleEntity (dependiendo de tu lógica)
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setRoleId(userDTO.getRoleId());
        userEntity.setRole(roleEntity);

        // Generar un ID único y asignarlo a la entidad
        userEntity.setUserId(UUID.randomUUID());

        String password = userDTO.getPassword(); // Asegúrate de que getPassword() obtenga la contraseña

        if (password != null) {
            try {
                SecureRandom random = new SecureRandom();
                byte[] salt = new byte[16];
                random.nextBytes(salt);
                userEntity.setSalt(Base64.getEncoder().encodeToString(salt));

                MessageDigest md = MessageDigest.getInstance("SHA-256");
                byte[] passwordBytes = password.getBytes(StandardCharsets.UTF_8);
                byte[] saltedPassword = new byte[salt.length + passwordBytes.length];
                System.arraycopy(salt, 0, saltedPassword, 0, salt.length);
                System.arraycopy(passwordBytes, 0, saltedPassword, salt.length, passwordBytes.length);
                byte[] hashBytes = md.digest(saltedPassword);
                String hashedPassword = Base64.getEncoder().encodeToString(hashBytes);
                userEntity.setPassword(hashedPassword);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        } else {
            // Manejar el caso en el que la contraseña es nula
            // Puedes lanzar una excepción, establecer una contraseña por defecto, etc.
        }

        UserEntity createdUserEntity = userRepository.save(userEntity);

        UserDTO createdUserDTO = new UserDTO();
        createdUserDTO.setUserId(createdUserEntity.getUserId());
        createdUserDTO.setName(createdUserEntity.getName());
        createdUserDTO.setEmail(createdUserEntity.getEmail());
        createdUserDTO.setImage(createdUserEntity.getImage());
        createdUserDTO.setRoleId(createdUserEntity.getRole().getRoleId());

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
                userEntity.getPassword()
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

        // Actualiza cualquier otro campo necesario

        // Guarda el usuario actualizado en la base de datos
        UserEntity updatedUserEntity = userRepository.save(existingUserEntity);

        // Convierte el usuario actualizado en UserDTO y devuélvelo
        UserDTO updatedUserResultDTO = new UserDTO();
        updatedUserResultDTO.setUserId(updatedUserEntity.getUserId());
        updatedUserResultDTO.setName(updatedUserEntity.getName());
        updatedUserResultDTO.setEmail(updatedUserEntity.getEmail());
        updatedUserResultDTO.setImage(updatedUserEntity.getImage());
        updatedUserResultDTO.setRoleId(updatedUserEntity.getRole().getRoleId());

        return updatedUserResultDTO;
    }
    public void deleteUser(UUID id){
        userRepository.deleteById(id);
    }



}
