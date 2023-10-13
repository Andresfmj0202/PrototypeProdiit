package com.Prodiit.Prototype.Controllers;

import com.Prodiit.Prototype.Models.Dtos.UserDTO;
import com.Prodiit.Prototype.Models.Entitys.UserEntity;
import com.Prodiit.Prototype.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Object createUser(@RequestBody UserEntity userEntity) {

        return userService.createAndSaveUser(userEntity);
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
        return mapUserEntitiesToDTOs(users);
    }

    private List<UserDTO> mapUserEntitiesToDTOs(List<UserEntity> userEntities) {
        List<UserDTO> userDTOs = new ArrayList<>();
        for (UserEntity userEntity : userEntities) {
            userDTOs.add(mapUserEntityToDTO(userEntity));
        }
        return userDTOs;
    }
    private UserDTO mapUserEntityToDTO(UserEntity userEntity) {
        return new UserDTO(
                userEntity.getUserId(),
                userEntity.getName(),
                userEntity.getEmail(),
                userEntity.getImage(),
                userEntity.getRole().getRoleId()
                // Ajusta esto según tu modelo de datos
        );
    }



    //obtener usuario por email
    @GetMapping("/email/{email}")
    public List<UserDTO> getUserByEmail(@PathVariable String email) {
        List<UserEntity> users = userService.findByEmail(email);
        return mapUserEntitiesToDTOs(users);
    }
     //actualizar usuario
     @PutMapping("/{id}")
    public UserEntity updateUser(@PathVariable UUID id, @RequestBody UserEntity user){
        user.setUserId(id);
        return userService.updateUser(id,user);
    }

    //borrar usuario
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable UUID id){
        userService.deleteUser(id);
    }
}
