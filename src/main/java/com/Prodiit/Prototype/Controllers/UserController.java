package com.Prodiit.Prototype.Controllers;

import com.Prodiit.Prototype.Models.Entitys.UserEntity;
import com.Prodiit.Prototype.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public List<UserEntity> getAllUsers(){
        return userService.getAllUsers();
    }

    //obtener usuario por id
    @GetMapping("/{id}")
     public Optional<UserEntity> getUserById(@PathVariable UUID id) {
        return userService.getUserById(id);
     }

     //obtener usuario por nombre
     @GetMapping("/name/{name}")
     public List<UserEntity> getUserByUsername(@PathVariable String name){
         return userService.findByname(name);
     }

     //obtener usuario por email
     @GetMapping("/email/{email}")
     public List<UserEntity> getUserByEmail(@PathVariable String email){
         return userService.findByEmail(email);
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
