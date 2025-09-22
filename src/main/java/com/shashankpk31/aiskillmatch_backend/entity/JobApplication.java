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
@Table(name = "SM_JOB_APPLICATIONS")
public class JobApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SM_JOB_APPLICATIONS")
    @SequenceGenerator(name = "SEQ_SM_JOB_APPLICATIONS", sequenceName = "SEQ_SM_JOB_APPLICATIONS", allocationSize = 1)
    @Column(name = "SJA_ID")
    private Long applicationId;

    @ManyToOne
    @JoinColumn(name = "SUA_USER_ID")
    private UserAuth userAuth;

    @ManyToOne
    @JoinColumn(name = "SJA_JOB_ID")
    private Job job;

    @ManyToOne
    @JoinColumn(name = "SJA_RESUME_ID")
    private Resume resume;

    @Column(name = "SJA_STATUS", nullable = false)
    private String status = "Applied";

    @Column(name = "SJA_APPLIED_AT", nullable = false)
    private Timestamp appliedAt;

    @Column(name = "SJA_RECORD_START_DATE", nullable = false)
    private Timestamp recordStartDate;

    @Column(name = "SJA_RECORD_END_DATE", nullable = false)
    private Timestamp recordEndDate;

    @Column(name = "SJA_CREATED_BY", nullable = false)
    private String createdBy;

    @Column(name = "SJA_UPDATED_BY")
    private String updatedBy;

    @Column(name = "SJA_DELETED_FLAG", nullable = false)
    private Boolean deletedFlag = false;
}