package com.Prodiit.Prototype.Controllers;

import com.Prodiit.Prototype.Models.Requests.LoginRequest;
import com.Prodiit.Prototype.Services.LoginService;
import com.Prodiit.Prototype.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


// Controlador para gestionar la autenticación
@RestController
@RequestMapping("/api/auth")
public class LoginController {

    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    // Autenticar y devolver un token (o error de autenticación)
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        boolean isAuthenticated = loginService.validateLogin(loginRequest.getEmail(), loginRequest.getPassword());

        if (isAuthenticated) {
            // Usuario autenticado con éxito
            // Aquí, normalmente generas y devuelves un token de acceso en lugar de simplemente un mensaje
            String accessToken = generateAccessToken(loginRequest.getEmail());
            return ResponseEntity.ok("Autenticación exitosa. Token: " + accessToken);
        } else {
            // Error de autenticación
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error de autenticación");
        }
    }

    // Genera un token de acceso (esto es un ejemplo, debes implementar esto de manera segura)
    private String generateAccessToken(String email) {
        // Implementa la generación de tokens JWT u otro método seguro aquí
        return "sample-access-token";
    }
}
