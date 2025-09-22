package com.shashankpk31.aiskillmatch_backend.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    private String projectName; 
    private String description;  
    private String technologies; 
    private String duration;
}
