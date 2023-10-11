package com.Prodiit.Prototype.Services;

import com.Prodiit.Prototype.Models.Entitys.RoleEntity;
import com.Prodiit.Prototype.Models.Entitys.UserEntity;
import com.Prodiit.Prototype.Respositorys.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
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
    public RoleEntity asignarRolAUsuario(RoleEntity role, UserEntity userEntity) {
        userEntity.setRole(role);
        return roleRepository.save(role);
    }

    //permiso de Administrador para agregar un invitado

}
