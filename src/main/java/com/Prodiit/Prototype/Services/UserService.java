package com.Prodiit.Prototype.Services;

import com.Prodiit.Prototype.Models.Entitys.UserEntity;
import com.Prodiit.Prototype.Respositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    public UserEntity createAndSaveUser(UserEntity userEntity) {
        // Generar un ID único y asignarlo a la entidad
        userEntity.setUserId(UUID.randomUUID());

        try {
            // Generar una sal aleatoria
            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[16];
            random.nextBytes(salt);

            // Asignar la sal directamente a la entidad
            userEntity.setSalt(Base64.getEncoder().encodeToString(salt));

            // Luego, sigue con el cálculo y asignación del hash de la contraseña

            // Crear una instancia de MessageDigest con el algoritmo de hash deseado (por ejemplo, SHA-256)
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            // Obtener los bytes de la contraseña
            byte[] passwordBytes = userEntity.getPassword().getBytes(StandardCharsets.UTF_8);

            // Combinar la sal y la contraseña
            byte[] saltedPassword = new byte[salt.length + passwordBytes.length];
            System.arraycopy(salt, 0, saltedPassword, 0, salt.length);
            System.arraycopy(passwordBytes, 0, saltedPassword, salt.length, passwordBytes.length);

            // Calcular el hash de la contraseña con la sal
            byte[] hashBytes = md.digest(saltedPassword);

            // Convertir el hash a una representación en base64
            String hashedPassword = Base64.getEncoder().encodeToString(hashBytes);

            // Asignar el hash a la entidad
            userEntity.setPassword(hashedPassword);

            // Imprimir el hash y la sal (esto es opcional)
            System.out.println("Contraseña encriptada: " + hashedPassword);
            System.out.println("Sal utilizada: " + userEntity.getSalt());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        // Guardar la entidad en la base de datos
        return userRepository.save(userEntity);
    }

    public List<UserEntity> getAllUsers(){
        return userRepository.findAll();
    }

    public Optional<UserEntity> getUserById(UUID id){
        return userRepository.findById(id);
    }

    public List<UserEntity> findByname(String name) {
        return userRepository.findByname(name);
    }

    public List<UserEntity> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    public UserEntity updateUser(UUID id, UserEntity user){
        user.setUserId(id);
        return userRepository.save(user);
    }

    public void deleteUser(UUID id){
        userRepository.deleteById(id);
    }



}
