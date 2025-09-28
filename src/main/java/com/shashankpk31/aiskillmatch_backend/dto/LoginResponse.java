package com.shashankpk31.aiskillmatch_backend.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private boolean loggedin;
    private String token;
}

