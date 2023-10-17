package com.Prodiit.Prototype.Models.Dtos;

import java.util.UUID;

public class UserCompanyRelationshipDTO {
    private UUID userId;
    private UUID companyId;

    public UserCompanyRelationshipDTO() {
    }

    public UserCompanyRelationshipDTO(UUID userId, UUID companyId) {
        this.userId = userId;
        this.companyId = companyId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getCompanyId() {
        return companyId;
    }

    public void setCompanyId(UUID companyId) {
        this.companyId = companyId;
    }
}
