package com.shashankpk31.aiskillmatch_backend.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shashankpk31.aiskillmatch_backend.dto.ApiResponse;
import com.shashankpk31.aiskillmatch_backend.dto.LoginRequest;
import com.shashankpk31.aiskillmatch_backend.dto.LoginResponse;
import com.shashankpk31.aiskillmatch_backend.dto.PasswordResetConfirmRequest;
import com.shashankpk31.aiskillmatch_backend.dto.PasswordResetRequest;
import com.shashankpk31.aiskillmatch_backend.dto.RegisterRequest;
import com.shashankpk31.aiskillmatch_backend.entity.PasswordResetToken;
import com.shashankpk31.aiskillmatch_backend.entity.UserAuth;
import com.shashankpk31.aiskillmatch_backend.entity.UserProfile;
import com.shashankpk31.aiskillmatch_backend.repository.PasswordResetTokenRepository;
import com.shashankpk31.aiskillmatch_backend.repository.ProfileRepository;
import com.shashankpk31.aiskillmatch_backend.repository.UserAuthRepository;
import com.shashankpk31.aiskillmatch_backend.service.EmailService;
import com.shashankpk31.aiskillmatch_backend.util.JwtUtil;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
public class AuthController {

	private final AuthenticationManager authenticationManager;
	private final UserAuthRepository UserAuthRepository;
	private final PasswordEncoder passwordEncoder;
	private final UserDetailsService userDetailsService;
	private final JwtUtil jwtUtil;
	private final EmailService emailService;
	private final PasswordResetTokenRepository tokenRepository;
	private final ProfileRepository profileRepository;

	public AuthController(AuthenticationManager authenticationManager, UserAuthRepository UserAuthRepository,
			PasswordEncoder passwordEncoder, UserDetailsService userDetailsService, JwtUtil jwtUtil,
			EmailService emailService, PasswordResetTokenRepository tokenRepository,
			ProfileRepository profileRepository) {
		this.authenticationManager = authenticationManager;
		this.UserAuthRepository = UserAuthRepository;
		this.passwordEncoder = passwordEncoder;
		this.userDetailsService = userDetailsService;
		this.jwtUtil = jwtUtil;
		this.emailService = emailService;
		this.tokenRepository = tokenRepository;
		this.profileRepository = profileRepository;
	}

	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest loginResponse) {
		try {
			Authentication auth = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginResponse.getUsername(), loginResponse.getPassword()));
			if (auth == null) {
				return ResponseEntity.status(401).body(new LoginResponse(false, "Invalid credentials"));
			}
		} catch (BadCredentialsException ex) {
			return ResponseEntity.status(401).body(new LoginResponse(false, "Invalid credentials"));
		}
		// load user details and generate JWT
		UserDetails userDetails = userDetailsService.loadUserByUsername(loginResponse.getUsername());
		String token = jwtUtil.generateToken(userDetails.getUsername(),
				userDetails.getAuthorities().iterator().next().getAuthority());
		return ResponseEntity.ok(new LoginResponse(true, token));
	}

	@PostMapping("/register")
	@Transactional
	public ResponseEntity<?> register(@RequestBody @Valid RegisterRequest body) throws Exception {
		
		if (UserAuthRepository.existsByUsername(body.getUsername())) {
			return ResponseEntity.badRequest().body(new ApiResponse(false, "Username already taken"));
		}
		if (UserAuthRepository.existsByEmail(body.getEmail())) {
			return ResponseEntity.badRequest().body(new ApiResponse(false, "Email already registered"));
		}

		// Create and save UserAuth entity
		UserAuth auth = new UserAuth();
		auth.setUsername(body.getUsername());
		auth.setEmail(body.getEmail());
		auth.setPassword(passwordEncoder.encode(body.getPassword()));
		auth.setRole(body.getRole());
		auth.setIsActive(true);
		auth.setRecordStartDate(new Timestamp(System.currentTimeMillis()));
		auth.setRecordEndDate(Timestamp.valueOf("9999-01-01 00:00:00"));
		auth.setCreatedBy("SYSTEM");
		UserAuthRepository.save(auth);

		// Create and save UserProfile entity linked to auth
		UserProfile profile = new UserProfile();
		profile.setUserAuth(auth);
		profile.setFullName(body.getFullName());
		profile.setHeadline(body.getHeadline());
		profile.setBio(body.getBio());
		profile.setSkills(body.getSkills());
		profile.setRecordStartDate(new Timestamp(System.currentTimeMillis()));
		profile.setRecordEndDate(Timestamp.valueOf("9999-01-01 00:00:00"));
		profile.setCreatedBy("SYSTEM");
		profile.setDeletedFlag(false);

		// Handle experience
		if (body.getExperience() != null && !body.getExperience().isEmpty()) {
			profile.setExperience(new ObjectMapper().valueToTree(body.getExperience()));
		} else {
			profile.setExperience(null);
		}

		// Handle education
		if (body.getEducation() != null && !body.getEducation().isEmpty()) {
			try {
				profile.setEducation(new ObjectMapper().valueToTree(body.getEducation()));
			} catch (Exception e) {
				return ResponseEntity.badRequest().body(new ApiResponse(false, "Invalid education data format."));
			}
		}

		// Handle projects
		if (body.getProjects() != null && !body.getProjects().isEmpty()) {
			profile.setProjects(new ObjectMapper().valueToTree(body.getProjects()));
		} else {
			profile.setProjects(null);
		}

		profileRepository.save(profile);
		emailService.sendSimpleMessage(auth.getEmail(), "Welcome to SkillMatch",
				"Thanks for registering, " + profile.getFullName());

		return ResponseEntity.ok(new ApiResponse(true, "User registered successfully"));

	}

	@PostMapping("/password-reset-request")
	public ResponseEntity<?> requestReset(@RequestBody PasswordResetRequest body) {
		String email = body.getEmail();
		Optional<UserAuth> maybeUser = UserAuthRepository.findByEmail(email);
		if (maybeUser.isEmpty()) {
			return ResponseEntity.ok(new ApiResponse(true, "If account exists, a reset email was sent"));
		}
		UserAuth user = maybeUser.get();
		String token = UUID.randomUUID().toString();
		PasswordResetToken prt = new PasswordResetToken();
		prt.setUserId(user.getUserId());
		prt.setToken(token);
		prt.setExpiresAt(LocalDateTime.now().plusHours(1));
		prt.setUsed(false);
		prt.setRecordStartDate(LocalDateTime.now());
		prt.setCreatedBy(maybeUser.get().getUsername());
		prt.setDeletedFlag(false);
		tokenRepository.save(prt);

		String resetLink = String.format("%s/reset-password?token=%s", "https://your-frontend.app", token);
		String emailText = "Click the link to reset your password (expires in 1 hour):\n\n" + resetLink;
		emailService.sendSimpleMessage(user.getEmail(), "Password Reset", emailText);

		return ResponseEntity.ok(new ApiResponse(true, "If account exists, a reset email was sent"));
	}

	@PostMapping("/password-reset")
	public ResponseEntity<?> doReset(@RequestBody PasswordResetConfirmRequest body) {
		String token = body.getToken();
		String newPassword = body.getPassword();

		var maybe = tokenRepository.findByToken(token);
		if (maybe.isEmpty())
			return ResponseEntity.badRequest().body(new ApiResponse(true,  "Invalid token"));

		PasswordResetToken prt = maybe.get();
		if (prt.getUsed() != null && prt.getUsed())
			return ResponseEntity.badRequest().body(new ApiResponse(true, "Token already used"));
		if (prt.getExpiresAt().isBefore(LocalDateTime.now()))
			return ResponseEntity.badRequest().body(new ApiResponse(true,  "Token expired"));

		UserAuth user = UserAuthRepository.findById(prt.getUserId()).orElseThrow();
		user.setPassword(passwordEncoder.encode(newPassword));
		UserAuthRepository.save(user);

		prt.setUsed(true);
		prt.setUpdatedBy(user.getUsername());
		tokenRepository.save(prt);

		emailService.sendSimpleMessage(user.getEmail(), "Password changed",
				"Your password has been changed successfully.");
		return ResponseEntity.ok(new ApiResponse(true, "Password changed"));
	}

}