package com.Prodiit.Prototype.Services;
import com.Prodiit.Prototype.Models.Entitys.UserEntity;
import com.Prodiit.Prototype.Respositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;


@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    public boolean validateLogin(String email, String password) {
        // Buscar el usuario en la base de datos por nombre de usuario o correo electrónico
        // Si el usuario existe, buscar su contraseña
        //en este caso usamos email para encontrar el usuario
        List<UserEntity> userEntities = userRepository.findByEmail(email);

        if (!userEntities.isEmpty()) {
            UserEntity userEntity = userEntities.get(0); // Obtener el primer usuario de la lista

            // Obtener la sal almacenada en la base de datos
            String storedSalt = userEntity.getSalt();

            // Aplicar el mismo proceso de hashing y salting a la contraseña ingresada
            String hashedPassword = hashPassword(password, storedSalt);

            // Comparar el hash calculado con el hash almacenado en la base de datos
            if (hashedPassword.equals(userEntity.getPassword())) {
                // Iniciar la sesión del usuario y permitir el acceso
                return true;
            }
        }

        // Autenticación fallida
        return false;
    }
    private String hashPassword(String password, String salt) {
        try {
            // Crear una instancia de MessageDigest con el algoritmo de hash deseado (por ejemplo, SHA-256)
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            // Convertir la sal en bytes
            byte[] saltBytes = Base64.getDecoder().decode(salt);

            // Obtener los bytes de la contraseña
            byte[] passwordBytes = password.getBytes(StandardCharsets.UTF_8);

            // Combinar la sal y la contraseña
            byte[] saltedPassword = new byte[saltBytes.length + passwordBytes.length];
            System.arraycopy(saltBytes, 0, saltedPassword, 0, saltBytes.length);
            System.arraycopy(passwordBytes, 0, saltedPassword, saltBytes.length, passwordBytes.length);

            // Calcular el hash de la contraseña con la sal
            byte[] hashBytes = md.digest(saltedPassword);

            // Convertir el hash a una representación en base64
            String hashedPassword = Base64.getEncoder().encodeToString(hashBytes);

            // Imprimir el hash y la sal (esto es opcional)
            System.out.println("Contraseña encriptada: " + hashedPassword);
            System.out.println("Sal utilizada: " + salt);

            // Retornar el hash resultante
            return hashedPassword;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null; // Maneja el error de manera apropiada en tu aplicación
        }
    }

}
