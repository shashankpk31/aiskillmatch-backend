package com.shashankpk.AISkillMatch.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.shashankpk.AISkillMatch.dto.JobDTO;
import com.shashankpk.AISkillMatch.exception.ResourceNotFoundException;
import com.shashankpk.AISkillMatch.model.Job;
import com.shashankpk.AISkillMatch.model.User;
import com.shashankpk.AISkillMatch.repository.JobRepository;
import com.shashankpk.AISkillMatch.repository.UserRepository;

@Service
public class JobService {
    private final JobRepository jobRepository;
    private final UserRepository userRepository;

    public JobService(JobRepository jobRepository, UserRepository userRepository) {
        this.jobRepository = jobRepository;
        this.userRepository = userRepository;
    }

    public Job createJob(JobDTO jobDTO) {
        User user = userRepository.findById(jobDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Job job = new Job();
        job.setUser(user);
        job.setTitle(jobDTO.getTitle());
        job.setDescription(jobDTO.getDescription());
        job.setSkills(jobDTO.getSkills());
        job.setLocation(jobDTO.getLocation());
        job.setSalary(jobDTO.getSalary());
        return jobRepository.save(job);
    }

    public Job updateJob(Long id, JobDTO jobDTO) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Job not found"));
        job.setTitle(jobDTO.getTitle());
        job.setDescription(jobDTO.getDescription());
        job.setSkills(jobDTO.getSkills());
        job.setLocation(jobDTO.getLocation());
        job.setSalary(jobDTO.getSalary());
        return jobRepository.save(job);
    }

    public void softDelete(Long id) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Job not found"));
        job.setDeleted(true);
        job.setEndDate(LocalDateTime.now());
        jobRepository.save(job);
    }

    public List<Job> findAll() {
        return jobRepository.findAll();
    }
}
