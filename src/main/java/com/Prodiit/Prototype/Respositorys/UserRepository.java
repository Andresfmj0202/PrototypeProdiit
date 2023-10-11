package com.Prodiit.Prototype.Respositorys;

import com.Prodiit.Prototype.Models.Entitys.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {


    Optional<UserEntity> findById(UUID userId); // Método no estático para buscar por ID


   List<UserEntity> findByname(String name);

   List<UserEntity> findByEmail(String email);
}
