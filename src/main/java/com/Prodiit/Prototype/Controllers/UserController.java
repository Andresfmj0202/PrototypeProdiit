package com.Prodiit.Prototype.Controllers;

import com.Prodiit.Prototype.Models.Dtos.RoleDTO;
import com.Prodiit.Prototype.Models.Dtos.UserDTO;
import com.Prodiit.Prototype.Models.Dtos.UserRoleAssignmentDTO;
import com.Prodiit.Prototype.Models.Entitys.RoleEntity;
import com.Prodiit.Prototype.Models.Entitys.UserEntity;
import com.Prodiit.Prototype.Services.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //crear usuario
    @PostMapping
    public UserDTO createUser(@RequestBody UserDTO userDTO) {
        // Llama al servicio para crear y guardar el usuario y devuelve el resultado
        return userService.createAndSaveUser(userDTO);
    }


    //obtener todos los usuarios
    @GetMapping
    public List<UserDTO> getAllUsers() {
        List<UserEntity> userEntities = userService.getAllUsers();
        return userService.mapToDTOList(userEntities);
    }

    //obtener usuario por id
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable UUID id) {
        Optional<UserEntity> userEntityOptional = userService.findUserById(id);
        if (userEntityOptional.isPresent()) {
            UserEntity userEntity = userEntityOptional.get();
            UserDTO userDTO = userService.mapToDTO(userEntity);
            return ResponseEntity.ok(userDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

     //obtener usuario por nombre

    @GetMapping("/name/{name}")
    public List<UserDTO> getUserByUsername(@PathVariable String name) {
        List<UserEntity> users = userService.findByname(name);
        return userService.mapUserEntitiesToDTOs(users);
    }


    //obtener usuario por email
    @GetMapping("/email/{email}")
    public List<UserDTO> getUserByEmail(@PathVariable String email) {
        List<UserEntity> users = userService.findByEmail(email);
        return userService.mapUserEntitiesToDTOs(users);
    }
     //actualizar usuario
     @PutMapping("addCompany/{id}")
    public UserEntity updateUserCompany(@PathVariable UUID id, @RequestBody UserEntity user){
        user.setUserId(id);
        return userService.updateUserCompany(id,user);
    }
    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable UUID userId, @RequestBody UserDTO updatedUserDTO) {
        try {
            UserDTO updatedUser = userService.updateUser(userId, updatedUserDTO);
            return ResponseEntity.ok(updatedUser);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    //borrar usuario
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable UUID id){
        userService.deleteUser(id);
    }

    @PutMapping("/status/{id}")
    public boolean updateStatus(@PathVariable UUID id) {
        return userService.statusUser(id);
    }
}
