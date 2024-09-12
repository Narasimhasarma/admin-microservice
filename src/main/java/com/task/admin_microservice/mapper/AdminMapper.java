package com.task.admin_microservice.mapper;

import com.task.admin_microservice.dto.AdminDTO;
import com.task.admin_microservice.entity.Admin;
import org.springframework.stereotype.Component;

@Component
public class AdminMapper {

    public AdminDTO toDTO(Admin admin) {
        if (admin == null) {
            return null;
        }
        AdminDTO dto = new AdminDTO();
        dto.setId(admin.getId());
        dto.setUsername(admin.getUsername());
        dto.setPassword(admin.getPassword());
        return dto;
    }

    public Admin toEntity(AdminDTO adminDTO) {
        if (adminDTO == null) {
            return null;
        }
        Admin admin = new Admin();
        admin.setId(adminDTO.getId());
        admin.setUsername(adminDTO.getUsername());
        admin.setPassword(adminDTO.getPassword());
        return admin;
    }
}
