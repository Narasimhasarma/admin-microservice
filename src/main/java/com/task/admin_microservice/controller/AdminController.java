package com.task.admin_microservice.controller;

import com.task.admin_microservice.dto.AdminDTO;
import com.task.admin_microservice.dto.FeedbackDTO;
import com.task.admin_microservice.dto.PropertyDTO;
import com.task.admin_microservice.dto.UserDTO;
import com.task.admin_microservice.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admins")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping
    public ResponseEntity<AdminDTO> createAdmin(@RequestBody AdminDTO adminDTO) {
        AdminDTO createdAdmin = adminService.createAdmin(adminDTO);
        return ResponseEntity.ok(createdAdmin);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdminDTO> getAdminById(@PathVariable Long id) {
        AdminDTO adminDTO = adminService.getAdminById(id);
        return ResponseEntity.ok(adminDTO);
    }

    @GetMapping
    public ResponseEntity<List<AdminDTO>> getAllAdmins() {
        List<AdminDTO> adminDTOs = adminService.getAllAdmins();
        return ResponseEntity.ok(adminDTOs);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdminDTO> updateAdmin(@PathVariable Long id, @RequestBody AdminDTO adminDTO) {
        AdminDTO updatedAdmin = adminService.updateAdmin(id, adminDTO);
        return ResponseEntity.ok(updatedAdmin);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable Long id) {
        adminService.deleteAdmin(id);
        return ResponseEntity.noContent().build();
    }

    // Fetching Property details
    @GetMapping("/properties/{propertyId}")
    public ResponseEntity<PropertyDTO> getPropertyById(@PathVariable Long propertyId) {
        PropertyDTO propertyDTO = adminService.getPropertyById(propertyId);
        return ResponseEntity.ok(propertyDTO);
    }

    @GetMapping("/properties")
    public ResponseEntity<List<PropertyDTO>> getAllProperties() {
        List<PropertyDTO> propertyDTOs = adminService.getAllProperties();
        return ResponseEntity.ok(propertyDTOs);
    }

    // Fetching User details
    @GetMapping("/users/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long userId) {
        UserDTO userDTO = adminService.getUserById(userId);
        return ResponseEntity.ok(userDTO);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> userDTOs = adminService.getAllUsers();
        return ResponseEntity.ok(userDTOs);
    }

    // Fetching Feedback details
    @GetMapping("/feedbacks/{propertyId}")
    public ResponseEntity<List<FeedbackDTO>> getFeedbackByPropertyId(@PathVariable Long propertyId) {
        List<FeedbackDTO> feedbackDTOs = adminService.getFeedbackByPropertyId(propertyId);
        return ResponseEntity.ok(feedbackDTOs);
    }

    @GetMapping("/feedbacks")
    public ResponseEntity<List<FeedbackDTO>> getAllFeedbacks() {
        List<FeedbackDTO> feedbackDTOs = adminService.getAllFeedbacks();
        return ResponseEntity.ok(feedbackDTOs);
    }

}
