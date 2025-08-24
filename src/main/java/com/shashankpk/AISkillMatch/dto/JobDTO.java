package com.shashankpk.AISkillMatch.dto;

import lombok.Data;

@Data
public class JobDTO {
    private Long id;
    private Long userId;
    private String title;
    private String description;
    private String skills;
    private String location;
    private String salary;
}