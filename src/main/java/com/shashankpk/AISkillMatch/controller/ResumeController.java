package com.shashankpk.AISkillMatch.controller;

import java.io.IOException;
import java.util.List;

import org.apache.tika.exception.TikaException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.shashankpk.AISkillMatch.model.Resume;
import com.shashankpk.AISkillMatch.service.ResumeService;

@RestController
@RequestMapping("/api/resumes")
public class ResumeController {
    private final ResumeService resumeService;

    public ResumeController(ResumeService resumeService) {
        this.resumeService = resumeService;
    }

    @PostMapping("/upload")
    @PreAuthorize("hasAuthority('JOBSEEKER')")
    public ResponseEntity<Resume> uploadResume(@RequestParam("file") MultipartFile file,
                                              @RequestParam("userId") Long userId) throws IOException, TikaException {
        return ResponseEntity.ok(resumeService.uploadResume(userId, file));
    }

    @GetMapping("/user/{userId}")
    @PreAuthorize("hasAuthority('JOBSEEKER') or hasAuthority('ADMIN')")
    public ResponseEntity<List<Resume>> getResumesByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(resumeService.findByUserId(userId));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('JOBSEEKER') or hasAuthority('ADMIN')")
    public ResponseEntity<Void> deleteResume(@PathVariable Long id) {
        resumeService.softDelete(id);
        return ResponseEntity.noContent().build();
    }
}
