package com.task.admin_microservice.service;

import com.task.admin_microservice.dto.AdminDTO;
import com.task.admin_microservice.dto.FeedbackDTO;
import com.task.admin_microservice.dto.PropertyDTO;
import com.task.admin_microservice.dto.UserDTO;
import com.task.admin_microservice.entity.Admin;
import com.task.admin_microservice.mapper.AdminMapper;
import com.task.admin_microservice.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.ArrayList;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private RestTemplate restTemplate;

    // Admin CRUD operations
    public AdminDTO createAdmin(AdminDTO adminDTO) {
        Admin admin = adminMapper.toEntity(adminDTO);
        Admin savedAdmin = adminRepository.save(admin);
        return adminMapper.toDTO(savedAdmin);
    }

    public AdminDTO getAdminById(Long id) {
        Admin admin = adminRepository.findById(id).orElseThrow(() -> new RuntimeException("Admin not found"));
        return adminMapper.toDTO(admin);
    }

    public List<AdminDTO> getAllAdmins() {
        List<Admin> admins = adminRepository.findAll();
        List<AdminDTO> adminDTOs = new ArrayList<>();
        for (Admin admin : admins) {
            adminDTOs.add(adminMapper.toDTO(admin));
        }
        return adminDTOs;
    }

    public AdminDTO updateAdmin(Long id, AdminDTO adminDTO) {
        Admin existingAdmin = adminRepository.findById(id).orElseThrow(() -> new RuntimeException("Admin not found"));
        existingAdmin.setUsername(adminDTO.getUsername());
        existingAdmin.setPassword(adminDTO.getPassword());
        Admin updatedAdmin = adminRepository.save(existingAdmin);
        return adminMapper.toDTO(updatedAdmin);
    }

    public void deleteAdmin(Long id) {
        adminRepository.deleteById(id);
    }

    // Fetching Property details
    public PropertyDTO getPropertyById(Long propertyId) {
        String propertyUrl = "http://property-service/properties/" + propertyId;
        return restTemplate.getForObject(propertyUrl, PropertyDTO.class);
    }

    public List<PropertyDTO> getAllProperties() {
        String propertyUrl = "http://property-service/properties";
        ResponseEntity<List<PropertyDTO>> response = restTemplate.exchange(
                propertyUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<PropertyDTO>>() {}
        );
        return response.getBody();
    }

    // Fetching User details
    public UserDTO getUserById(Long userId) {
        String userUrl = "http://user-service/users/" + userId;
        return restTemplate.getForObject(userUrl, UserDTO.class);
    }

    public List<UserDTO> getAllUsers() {
        String userUrl = "http://user-service/users";
        ResponseEntity<List<UserDTO>> response = restTemplate.exchange(
                userUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<UserDTO>>() {}
        );
        return response.getBody();
    }

    // Fetching Feedback details
    public List<FeedbackDTO> getFeedbackByPropertyId(Long propertyId) {
        String feedbackUrl = "http://feedback-service/feedbacks?propertyId=" + propertyId;
        ResponseEntity<List<FeedbackDTO>> response = restTemplate.exchange(
                feedbackUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<FeedbackDTO>>() {}
        );
        return response.getBody();
    }

    public List<FeedbackDTO> getAllFeedbacks() {
        String feedbackUrl = "http://FEEDBACK-SERVICE/feedbacks";
        ResponseEntity<List<FeedbackDTO>> response = restTemplate.exchange(
                feedbackUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<FeedbackDTO>>() {}
        );
        return response.getBody();
    }
}
