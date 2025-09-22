package com.shashankpk31.aiskillmatch_backend.entity;

import java.sql.Timestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "SM_RESUMES")
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SM_RESUMES")
    @SequenceGenerator(name = "SEQ_SM_RESUMES", sequenceName = "SEQ_SM_RESUMES", allocationSize = 1)
    @Column(name = "SR_RESUME_ID", nullable = false)
    private Long resumeId;

    @ManyToOne
    @JoinColumn(name = "SUA_USER_ID")
    private UserAuth userAuth;

    @Column(name = "SR_FILE_NAME", nullable = false)
    private String fileName;

    @Column(name = "SR_VERSION", nullable = false)
    private Integer version;

    @Column(name = "SR_FILE_PATH", nullable = false)
    private String filePath;

    @Column(name = "SR_UPLOAD_DATE", nullable = false)
    private Timestamp uploadDate;

    @Column(name = "SR_RECORD_START_DATE", nullable = false)
    private Timestamp recordStartDate;

    @Column(name = "SR_RECORD_END_DATE", nullable = false)
    private Timestamp recordEndDate;

    @Column(name = "SR_CREATED_BY", nullable = false)
    private String createdBy;

    @Column(name = "SR_UPDATED_BY")
    private String updatedBy;

    @Column(name = "SR_DELETED_FLAG", nullable = false)
    private Boolean deletedFlag = false;
}