package com.Prodiit.Prototype.Controllers;

import com.Prodiit.Prototype.Models.Dtos.RoleDTO;
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
    public Object save(@RequestBody RoleDTO roleDTO) {
        RoleEntity roleEntity = roleService.saveRole(roleDTO);
        return roleEntity;
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
    @PutMapping("/assignUserToRole")
    public ResponseEntity<RoleDTO> assignUserRole(@RequestBody UserRoleAssignmentDTO assignmentDTO) {
        try {
            // Llama al servicio para procesar la asignación
            RoleDTO assignedRoleDTO = roleService.assignUserToRole(assignmentDTO);

            return ResponseEntity.ok(assignedRoleDTO);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
