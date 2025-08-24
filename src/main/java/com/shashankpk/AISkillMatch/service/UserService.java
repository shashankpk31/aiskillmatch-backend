package com.shashankpk.AISkillMatch.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.shashankpk.AISkillMatch.dto.UserDTO;
import com.shashankpk.AISkillMatch.exception.ResourceNotFoundException;
import com.shashankpk.AISkillMatch.model.User;
import com.shashankpk.AISkillMatch.repository.UserRepository;

@Service
public class UserService {
	 private final UserRepository userRepository;
	    private final PasswordEncoder passwordEncoder;

	    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
	        this.userRepository = userRepository;
	        this.passwordEncoder = passwordEncoder;
	    }

	    public User register(UserDTO userDTO) {
	        User user = new User();
	        user.setUsername(userDTO.getUsername());
	        user.setEmail(userDTO.getEmail());
	        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
	        user.setRole(User.Role.valueOf(userDTO.getRole()));
	        user.setSkills(userDTO.getSkills());
	        user.setCompany(userDTO.getCompany());
	        return userRepository.save(user);
	    }

	    public User update(Long id, UserDTO userDTO) {
	        User user = userRepository.findById(id)
	                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
	        user.setUsername(userDTO.getUsername());
	        user.setEmail(userDTO.getEmail());
	        if (userDTO.getPassword() != null) {
	            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
	        }
	        user.setSkills(userDTO.getSkills());
	        user.setCompany(userDTO.getCompany());
	        return userRepository.save(user);
	    }

	    public void softDelete(Long id) {
	        User user = userRepository.findById(id)
	                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
	        user.setDeleted(true);
	        user.setEndDate(LocalDateTime.now());
	        userRepository.save(user);
	    }

	    public List<User> findAll() {
	        return userRepository.findAll();
	    }

	    public User findByEmail(String email) {
	        return userRepository.findByEmail(email)
	                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
	    }
}
