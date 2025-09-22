package com.shashankpk31.aiskillmatch_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.shashankpk31.aiskillmatch_backend.entity.UserProfile;

public interface ProfileRepository extends JpaRepository<UserProfile,Long>{
    
}