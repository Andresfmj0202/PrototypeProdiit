package com.Prodiit.Prototype.Services;

import com.Prodiit.Prototype.Models.Dtos.UserRoleAssignmentDTO;
import com.Prodiit.Prototype.Models.Entitys.RoleEntity;
import com.Prodiit.Prototype.Models.Entitys.UserEntity;
import com.Prodiit.Prototype.Respositorys.RoleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RoleService {

    @Autowired
    private  final RoleRepository roleRepository;
    private  final UserService userService;




    public RoleService(RoleRepository roleRepository, UserService userService) {
        this.roleRepository = roleRepository;
        this.userService = userService;

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

    //asignar un rol a un usuario
    public RoleEntity asignarRolAUsuario(UserRoleAssignmentDTO assignmentDTO) {
        UUID userId = assignmentDTO.getUserId();
        Long roleId = assignmentDTO.getRoleId();

        // Recupera el usuario y el rol de sus respectivos servicios o repositorios
        UserEntity userEntity = userService.findUserById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
        RoleEntity role = roleRepository.findById(roleId)
                .orElseThrow(() -> new EntityNotFoundException("Rol no encontrado"));

        // Asigna el rol al usuario
        userEntity.setRole(role);

        // Guarda los cambios en el usuario
        userService.createAndSaveUser(userEntity);

        return role;
    }

    //permiso de Administrador para agregar un invitado

}
