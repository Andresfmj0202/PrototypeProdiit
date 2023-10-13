package com.Prodiit.Prototype.Controllers;

import com.Prodiit.Prototype.Models.Dtos.UserRoleAssignmentDTO;
import com.Prodiit.Prototype.Models.Entitys.RoleEntity;
import com.Prodiit.Prototype.Services.RoleService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/role")
public class RoleController {



    private final RoleService roleService;

    @Autowired
    public RoleController( RoleService roleService) {

        this.roleService = roleService;
    }
    //Crear Role
    @PostMapping
    public Object save(@RequestBody RoleEntity roleEntity) {
        return roleService.saveRole(roleEntity);
    }

    //Obtener todos los Roles
    @GetMapping
    public List<RoleEntity> getAll(){
        return roleService.findAll();
    }

    //Obtener Role por nombre
    @GetMapping("/name/{name}")
    public Optional<RoleEntity> getByName(@PathVariable String name){
        return roleService.findByRoleName(name);
    }

    //Actualizar Role
    @PutMapping("/{id}")
    public RoleEntity update(@PathVariable Long id, @RequestBody RoleEntity role){
        role.setRoleId(id);
        return roleService.updateRole(id,role);
    }

    //Borrar Role
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        roleService.deleteRole(id);
    }

    //Asignar un rol a un usuario
    @PutMapping("/user/{userId}/role/{roleId}")
    public ResponseEntity<RoleEntity> asignarRolAUsuario(@PathVariable UUID userId, @PathVariable Long roleId) {
        try {
            // Crea un DTO para representar la asignación de roles
            UserRoleAssignmentDTO assignmentDTO = new UserRoleAssignmentDTO();
            assignmentDTO.setUserId(userId);
            assignmentDTO.setRoleId(roleId);

            // Llama a un servicio para procesar la asignación
            RoleEntity assignedRole = roleService.asignarRolAUsuario(assignmentDTO);

            return ResponseEntity.ok(assignedRole);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
