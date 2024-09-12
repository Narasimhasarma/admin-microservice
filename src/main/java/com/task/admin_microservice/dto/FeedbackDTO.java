package com.task.admin_microservice.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FeedbackDTO {

    private Long id;
    private Long propertyId;
    private String comments;
}
