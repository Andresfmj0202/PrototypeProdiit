package com.Prodiit.Prototype.Controllers;

import com.Prodiit.Prototype.Services.CompanyAddUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/companies/users")
public class CompanyAddUserController {

    private final CompanyAddUserService companyAddUserService;

    @Autowired
    public CompanyAddUserController(CompanyAddUserService companyAddUserService) {
        this.companyAddUserService = companyAddUserService;
    }

    // Agregar un usuario a una empresa
    @PostMapping("/addUser")
    public ResponseEntity<String> addUserToCompany(
            @RequestBody Map<String, UUID> requestBody) {
        try {
            UUID userId = requestBody.get("userId");
            UUID companyId = requestBody.get("companyId");
            companyAddUserService.addUserToCompany(userId, companyId);
            return ResponseEntity.ok("Usuario agregado exitosamente a la empresa.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al agregar el usuario a la empresa: " + e.getMessage());
        }
    }
/*
    // Agregar una compañía a un usuario
    @PostMapping("/addCompany")
    public ResponseEntity<String> addCompanyToUser(
            @RequestBody Map<String, UUID> requestBody) {
        try {
            UUID userId = requestBody.get("userId");
            UUID companyId = requestBody.get("companyId");
            companyAddUserService.addUserToCompany(userId, companyId);
            return ResponseEntity.ok("Compañía agregada exitosamente al usuario.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al agregar la compañía al usuario: " + e.getMessage());
        }
    }

 */
}
