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
@Table(name = "SM_JOB_TAGS")
public class JobTag {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SM_JOB_TAGS")
    @SequenceGenerator(name = "SEQ_SM_JOB_TAGS", sequenceName = "SEQ_SM_JOB_TAGS", allocationSize = 1)
    @Column(name = "SJT_TAG_ID", nullable = false)
    private Long tagId;

    @ManyToOne
    @JoinColumn(name = "SJ_JOB_ID")
    private Job job;

    @Column(name = "SJT_TAG_NAME", nullable = false)
    private String tagName;

    @Column(name = "SJT_RECORD_START_DATE", nullable = false)
    private Timestamp recordStartDate;

    @Column(name = "SJT_RECORD_END_DATE", nullable = false)
    private Timestamp recordEndDate;

    @Column(name = "SJT_CREATED_BY", nullable = false)
    private String createdBy;

    @Column(name = "SJT_UPDATED_BY")
    private String updatedBy;

    @Column(name = "SJT_DELETED_FLAG", nullable = false)
    private Boolean deletedFlag = false;
}