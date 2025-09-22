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
@Table(name = "SM_USER_EMBEDDINGS")
public class UserEmbedding {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SM_USER_EMBEDDINGS")
    @SequenceGenerator(name = "SEQ_SM_USER_EMBEDDINGS", sequenceName = "SEQ_SM_USER_EMBEDDINGS", allocationSize = 1)
    @Column(name = "SUE_ID", nullable = false)
    private Long embeddingId;

    @ManyToOne
    @JoinColumn(name = "SUA_USER_ID")
    private UserAuth userAuth;

    @Column(name = "SUE_EMBEDDING",columnDefinition = "vector(1536)", nullable = false)
    private String embedding; // Adjust type as needed for VECTOR(1536)

    @Column(name = "SUE_UPDATED_AT", nullable = false)
    private Timestamp updatedAt;

    @Column(name = "SUE_RECORD_START_DATE", nullable = false)
    private Timestamp recordStartDate;

    @Column(name = "SUE_RECORD_END_DATE", nullable = false)
    private Timestamp recordEndDate;

    @Column(name = "SUE_CREATED_BY", nullable = false)
    private String createdBy;

    @Column(name = "SUE_UPDATED_BY")
    private String updatedBy;

    @Column(name = "SUE_DELETED_FLAG", nullable = false)
    private Boolean deletedFlag = false;
}