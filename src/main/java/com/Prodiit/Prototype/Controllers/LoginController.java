package com.Prodiit.Prototype.Controllers;

import com.Prodiit.Prototype.Models.Entitys.UserEntity;
import com.Prodiit.Prototype.Models.Requests.LoginRequest;
import com.Prodiit.Prototype.Models.Responses.LoginResponse;
import com.Prodiit.Prototype.Respositorys.UserRepository;
import com.Prodiit.Prototype.Services.LoginService;
import com.Prodiit.Prototype.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


// Controlador para gestionar la autenticación

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        boolean isAuthenticated = loginService.validateLogin(loginRequest.getEmail(), loginRequest.getPassword());

        if (isAuthenticated) {
            // Usuario autenticado con éxito
            String accessToken = loginService.generateAccessToken(loginRequest.getEmail());
            // Obtén la entidad de usuario completa
            UserEntity userEntity = loginService.getUserEntityByEmail(loginRequest.getEmail());
            if (userEntity != null) {
                // Crear una instancia de LoginResponse con el token y el usuario completo
                LoginResponse response = new LoginResponse(accessToken, userEntity);
                return ResponseEntity.ok(response);
            } else {
                // Manejar el caso en el que no se encuentra el usuario
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } else {
            // Error de autenticación
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }
}