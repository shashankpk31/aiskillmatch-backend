package com.shashankpk.AISkillMatch.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.shashankpk.AISkillMatch.model.Job;
import com.shashankpk.AISkillMatch.model.Resume;
import com.shashankpk.AISkillMatch.repository.JobRepository;
import com.shashankpk.AISkillMatch.repository.ResumeRepository;

public class RecommendationService {
	 private final JobRepository jobRepository;
	    private final ResumeRepository resumeRepository;

	    public RecommendationService(JobRepository jobRepository, ResumeRepository resumeRepository) {
	        this.jobRepository = jobRepository;
	        this.resumeRepository = resumeRepository;
	    }

	    public List<Job> getJobRecommendations(Long userId) {
	        List<Resume> resumes = resumeRepository.findByUserId(userId);
	        if (resumes.isEmpty()) {
	            return jobRepository.findAll();
	        }
	        String userSkills = resumes.stream()
	                .map(Resume::getSkills)
	                .filter(skills -> skills != null && !skills.isEmpty())
	                .collect(Collectors.joining(","));
	        List<String> userSkillList = Arrays.asList(userSkills.split(","));
	        return jobRepository.findAll().stream()
	                .filter(job -> {
	                    List<String> jobSkills = Arrays.asList(job.getSkills().split(","));
	                    return jobSkills.stream().anyMatch(userSkillList::contains);
	                })
	                .collect(Collectors.toList());
	    }

	    public List<String> getMissingSkills(Long userId, Long jobId) {
	        List<Resume> resumes = resumeRepository.findByUserId(userId);
	        Job job = jobRepository.findById(jobId)
	                .orElseThrow(() -> new RuntimeException("Job not found"));
	        String userSkills = resumes.stream()
	                .map(Resume::getSkills)
	                .filter(skills -> skills != null && !skills.isEmpty())
	                .collect(Collectors.joining(","));
	        List<String> userSkillList = Arrays.asList(userSkills.split(","));
	        List<String> jobSkills = Arrays.asList(job.getSkills().split(","));
	        return jobSkills.stream()
	                .filter(skill -> !userSkillList.contains(skill))
	                .collect(Collectors.toList());
	    }
}
