package com.shashankpk.AISkillMatch.dto;

import lombok.Data;

@Data
public class ResumeDTO {
    private Long id;
    private Long userId;
    private String fileName;
    private String skills;
}
