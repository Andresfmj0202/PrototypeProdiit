package com.Prodiit.Prototype.Services;

import com.Prodiit.Prototype.Models.Entitys.UserEntity;
import com.Prodiit.Prototype.Respositorys.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public boolean validateLogin(String email, String password) {
        // Realiza la validación del usuario como lo hacías antes
        List<UserEntity> userEntities = userRepository.findByEmail(email);

        if (!userEntities.isEmpty()) {
            UserEntity userEntity = userEntities.get(0); // Obtener el primer usuario de la lista

            String storedPassword = userEntity.getPassword();

            // Verificar la contraseña ingresada con la almacenada
            if (passwordEncoder.matches(password, storedPassword)) {
                // Contraseña válida
                return true;
            }
        }

        // Autenticación fallida
        return false;
    }

    public SecretKey generateSecureKey() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
            keyGenerator.init(256); // Tamaño de clave en bits
            return keyGenerator.generateKey();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            // Maneja la excepción de manera adecuada en tu aplicación
            return null; // O devuelve otro valor predeterminado
        }
    }

    // Genera un token JWT
    public String generateAccessToken(String email) {
        // Definir los claims del token (puedes agregar información adicional aquí)
        Claims claims = Jwts.claims();
        claims.put("sub", email);

        // Definir la fecha de expiración del token (ejemplo: 1 hora)
        Date expirationDate = new Date(System.currentTimeMillis() + 3600000);

        // Clave secreta para firmar el token (debes almacenarla de manera segura)
        SecretKey secretKey = generateSecureKey(); // Genera una clave segura

        if (secretKey != null) {
            // Generar el token JWT
            String token = Jwts.builder()
                    .setClaims(claims)
                    .setExpiration(expirationDate)
                    .signWith(SignatureAlgorithm.HS256, secretKey)
                    .compact();

            return token;
        } else {
            // Maneja el caso en el que no se pudo generar la clave
            return null; // O devuelve otro valor predeterminado
        }
    }
}
