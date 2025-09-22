package com.shashankpk31.aiskillmatch_backend.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Experience {
    private String organization; 
    private String role;         
    private String duration;     
    private String[] responsibilities; 
}
