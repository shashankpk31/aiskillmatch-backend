package com.shashankpk.AISkillMatch.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shashankpk.AISkillMatch.model.Resume;

public interface ResumeRepository extends JpaRepository<Resume, Long> {
    List<Resume> findByUserId(Long userId);
}