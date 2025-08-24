package com.shashankpk.AISkillMatch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shashankpk.AISkillMatch.model.Job;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
}
