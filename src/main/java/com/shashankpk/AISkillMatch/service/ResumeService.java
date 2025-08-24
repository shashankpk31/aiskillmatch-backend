package com.shashankpk.AISkillMatch.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.shashankpk.AISkillMatch.exception.InvalidFileException;
import com.shashankpk.AISkillMatch.exception.ResourceNotFoundException;
import com.shashankpk.AISkillMatch.model.Resume;
import com.shashankpk.AISkillMatch.model.User;
import com.shashankpk.AISkillMatch.repository.ResumeRepository;
import com.shashankpk.AISkillMatch.repository.UserRepository;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.sax.BodyContentHandler;

@Service
public class ResumeService {
    private final ResumeRepository resumeRepository;
    private final UserRepository userRepository;

    public ResumeService(ResumeRepository resumeRepository, UserRepository userRepository) {
        this.resumeRepository = resumeRepository;
        this.userRepository = userRepository;
    }

    public Resume uploadResume(Long userId, MultipartFile file) throws TikaException, IOException {
        if (file.getSize() > 5 * 1024 * 1024) {
            throw new InvalidFileException("File size exceeds 5MB");
        }
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Resume resume = new Resume();
        resume.setUser(user);
        resume.setFileName(file.getOriginalFilename());
        resume.setFileData(file.getBytes());

        // Parse skills with Apache Tika
        String skills = extractSkills(file);
        resume.setSkills(skills);

        return resumeRepository.save(resume);
    }

    private String extractSkills(MultipartFile file) throws IOException, TikaException {
        AutoDetectParser parser = new AutoDetectParser();
        BodyContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        try {
			parser.parse(file.getInputStream(), handler, metadata);
		} catch (Exception e) {
			e.printStackTrace();
		} 
        String text = handler.toString().toLowerCase();
        List<String> predefinedSkills = Arrays.asList("java", "python", "javascript", "sql", "react", "spring");
        return predefinedSkills.stream()
                .filter(text::contains)
                .collect(Collectors.joining(","));
    }

    public void softDelete(Long id) {
        Resume resume = resumeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resume not found"));
        resume.setDeleted(true);
        resume.setEndDate(LocalDateTime.now());
        resumeRepository.save(resume);
    }

    public List<Resume> findByUserId(Long userId) {
        return resumeRepository.findByUserId(userId);
    }
}
