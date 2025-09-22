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
@Table(name = "SM_JOB_EMBEDDINGS")
public class JobEmbedding {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SM_JOB_EMBEDDINGS")
    @SequenceGenerator(name = "SEQ_SM_JOB_EMBEDDINGS", sequenceName = "SEQ_SM_JOB_EMBEDDINGS", allocationSize = 1)
    @Column(name = "SJE_ID", nullable = false)
    private Long embeddingId;

    @ManyToOne
    @JoinColumn(name = "SJ_JOB_ID")
    private Job job;

    @Column(name = "SJE_EMBEDDING", columnDefinition = "vector(1536)")
    private String embedding;

    @Column(name = "SJE_UPDATED_AT", nullable = false)
    private Timestamp updatedAt;

    @Column(name = "SJE_RECORD_START_DATE", nullable = false)
    private Timestamp recordStartDate;

    @Column(name = "SJE_RECORD_END_DATE", nullable = false)
    private Timestamp recordEndDate;

    @Column(name = "SJE_CREATED_BY", nullable = false)
    private String createdBy;

    @Column(name = "SJE_UPDATED_BY")
    private String updatedBy;

    @Column(name = "SJE_DELETED_FLAG", nullable = false)
    private Boolean deletedFlag = false;
}