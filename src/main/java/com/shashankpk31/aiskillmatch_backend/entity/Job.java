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
@Table(name = "SM_JOBS")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SM_JOBS")
    @SequenceGenerator(name = "SEQ_SM_JOBS", sequenceName = "SEQ_SM_JOBS", allocationSize = 1)
    @Column(name = "SJ_JOB_ID", nullable = false)
    private Long jobId;

    @Column(name = "SJ_TITLE", nullable = false)
    private String title;

    @Column(name = "SJ_DESCRIPTION", nullable = false)
    private String description;

    @Column(name = "SJ_LOCATION")
    private String location;

    @Column(name = "SJ_SALARY_MIN")
    private Integer salaryMin;

    @Column(name = "SJ_SALARY_MAX")
    private Integer salaryMax;

    @ManyToOne
    @JoinColumn(name = "SJ_POSTED_BY")
    private UserAuth postedBy;

    @Column(name = "SJ_STATUS", nullable = false)
    private String status = "ACTIVE";

    @Column(name = "SJ_RECORD_START_DATE", nullable = false)
    private Timestamp recordStartDate;

    @Column(name = "SJ_RECORD_END_DATE", nullable = false)
    private Timestamp recordEndDate;

    @Column(name = "SJ_CREATED_BY", nullable = false)
    private String createdBy;

    @Column(name = "SJ_UPDATED_BY")
    private String updatedBy;

    @Column(name = "SJ_DELETED_FLAG", nullable = false)
    private Boolean deletedFlag = false;
}