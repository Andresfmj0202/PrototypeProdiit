package com.Prodiit.Prototype.Services;

import com.Prodiit.Prototype.Models.Dtos.CompanyDTO;
import com.Prodiit.Prototype.Models.Dtos.UserCompanyRelationshipDTO;
import com.Prodiit.Prototype.Models.Dtos.UserDTO;
import com.Prodiit.Prototype.Models.Entitys.CompanyEntity;
import com.Prodiit.Prototype.Models.Entitys.UserEntity;
import com.Prodiit.Prototype.Respositorys.CompanyAddUserRepository;
import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;
@Service
public class CompanyAddUserService {

    private final UserService userService;
    private final CompanyService companyService;

    @Autowired
    public CompanyAddUserService(UserService userService, CompanyService companyService) {
        this.userService = userService;
        this.companyService = companyService;
    }

    //agregar un usuario a una compañía
    public void addUserToCompany(UUID userId, UUID companyId) {
        // Obtener el usuario por su ID
        Optional<UserEntity> userOptional = userService.findUserById(userId);

        if (userOptional.isPresent()) {
            UserEntity user = userOptional.get();

            // Obtener la compañía por su ID
            Optional<CompanyEntity> companyOptional = companyService.getCompanyById(companyId);

            if (companyOptional.isPresent()) {
                CompanyEntity company = companyOptional.get();

                // Agregar la compañía al conjunto de compañías del usuario
                user.getCompanies().add(company);

                // Guardar el usuario actualizado en la base de datos
                userService.updateUserCompany(userId, user);
            }
        }
    }

}

