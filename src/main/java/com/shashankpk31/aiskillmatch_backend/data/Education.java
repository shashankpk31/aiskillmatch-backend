package com.shashankpk31.aiskillmatch_backend.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Education {
    private String institution; 
    private String degree;      
    private String fieldOfStudy; 
    private String duration;
}