package com.task.admin_microservice.dto;

import lombok.Data;

@Data
public class PropertyDTO {
    private Long id;
    private String name;
    private String address;
    private String description;
}
