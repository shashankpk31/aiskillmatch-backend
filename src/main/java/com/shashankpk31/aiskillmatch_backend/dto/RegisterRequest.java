package com.shashankpk31.aiskillmatch_backend.dto;

import java.util.List;

import com.shashankpk31.aiskillmatch_backend.data.Education;
import com.shashankpk31.aiskillmatch_backend.data.Experience;
import com.shashankpk31.aiskillmatch_backend.data.Project;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @NotBlank(message  = "Username is required")
    private String username;

    @NotBlank(message = "Email is required")
    @Email(message = "Email is invalid")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    @NotBlank(message="Role is required")
    private String role;
    
    // profile-specific
    @NotBlank(message = "Full name is required")
    private String fullName;

    private String headline;
    private String bio;
    private String[] skills;
    private List<Experience> experience;
    private List<Education> education;  
    private List<Project> projects;  
}
