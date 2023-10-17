package com.Prodiit.Prototype.Services;

import com.Prodiit.Prototype.Models.Dtos.RoleDTO;
import com.Prodiit.Prototype.Models.Dtos.UserDTO;
import com.Prodiit.Prototype.Models.Dtos.UserRoleAssignmentDTO;
import com.Prodiit.Prototype.Models.Entitys.RoleEntity;
import com.Prodiit.Prototype.Models.Entitys.UserEntity;
import com.Prodiit.Prototype.Respositorys.RoleRepository;
import com.Prodiit.Prototype.Respositorys.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RoleService {

    @Autowired
    private  final RoleRepository roleRepository;
    private  final UserService userService;
    private  final UserRepository userRepository;




    public RoleService(RoleRepository roleRepository, UserService userService, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userService = userService;
        this.userRepository = userRepository;

    }

    public RoleEntity saveRole(RoleEntity roleEntity) {
        return roleRepository.save(roleEntity);
    }

    public Optional<RoleEntity>findByName(String name) {
        return roleRepository.findByName(name);
    }

    public Optional<RoleEntity> findById(long id) {
        return roleRepository.findById(id);
    }

    public List<RoleEntity> findAll() {
        return roleRepository.findAll();
    }

    public Optional<RoleEntity> findByRoleName(String name) {
        return roleRepository.findByName(name);
    }

    public RoleEntity updateRole(long id,RoleEntity roleEntity) {
        roleEntity.setRoleId(id);
       return roleRepository.save(roleEntity);
    }
    public void deleteRole(long id) {
        roleRepository.deleteById(id);
    }

    public RoleDTO assignUserToRole(UserRoleAssignmentDTO assignmentDTO) {
        // Recupera el rol desde la base de datos
        RoleEntity roleEntity = roleRepository.findById(assignmentDTO.getRoleId())
                .orElseThrow(() -> new EntityNotFoundException("Rol no encontrado"));

        // Llama al servicio de usuarios para obtener el usuario
        UserEntity userEntity = userRepository.findById(assignmentDTO.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));

        // Asigna el rol al usuario
        userEntity.setRole(roleEntity);

        // Realiza la actualización del usuario en el servicio de usuarios
        userRepository.save(userEntity);

        // Convierte el usuario actualizado en UserDTO
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(userEntity.getUserId());
        userDTO.setName(userEntity.getName());
        userDTO.setEmail(userEntity.getEmail());
        userDTO.setImage(userEntity.getImage());
        userDTO.setRoleId(userEntity.getRole().getRoleId());

        // Crea y devuelve un RoleDTO
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setRoleId(roleEntity.getRoleId());
        roleDTO.setName(roleEntity.getName());
        roleDTO.setDescription(roleEntity.getDescription());
        roleDTO.setUsers(Collections.singletonList(userDTO));

        return roleDTO;
    }

}
