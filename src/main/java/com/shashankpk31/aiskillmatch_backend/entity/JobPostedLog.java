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
@Table(name = "SM_JOB_POSTED_LOG")
public class JobPostedLog {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SM_JOB_POSTED_LOG")
    @SequenceGenerator(name = "SEQ_SM_JOB_POSTED_LOG", sequenceName = "SEQ_SM_JOB_POSTED_LOG", allocationSize = 1)
    @Column(name = "SJPL_ID", nullable = false)
    private Long logId;

    @ManyToOne
    @JoinColumn(name = "SJPL_JOB_ID")
    private Job job;

    @Column(name = "SJPL_ACTION", nullable = false)
    private String action;

    @Column(name = "SJPL_TIMESTAMP", nullable = false)
    private Timestamp timestamp;

    @ManyToOne
    @JoinColumn(name = "SJPL_UPDATED_BY")
    private UserAuth updatedBy;

    @Column(name = "SJPL_RECORD_START_DATE", nullable = false)
    private Timestamp recordStartDate;

    @Column(name = "SJPL_RECORD_END_DATE", nullable = false)
    private Timestamp recordEndDate;

    @Column(name = "SJPL_CREATED_BY", nullable = false)
    private String createdBy;

    @Column(name = "SJPL_UPDATED_BY_NAME")
    private String updatedByName;

    @Column(name = "SJPL_DELETED_FLAG", nullable = false)
    private Boolean deletedFlag = false;
}