package com.shashankpk.AISkillMatch.dto;

import lombok.Data;

@Data
public class LoginResponse {
	private Long id;
    private String token;
    private String role;

    public LoginResponse(String token, String role, Long id) {
    	this.id=id;
        this.token = token;
        this.role = role;
    }
}
