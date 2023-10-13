package com.Prodiit.Prototype.Models.Dtos;

import java.util.UUID;

public class UserRoleAssignmentDTO {
    private UUID userId;
    private Long roleId;

    public UserRoleAssignmentDTO() {
    }

    public UserRoleAssignmentDTO(UUID userId, Long roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
