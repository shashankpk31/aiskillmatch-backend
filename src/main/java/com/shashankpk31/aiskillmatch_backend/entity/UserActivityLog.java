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
@Table(name = "SM_USER_ACTIVITY_LOG")
public class UserActivityLog {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SM_USER_ACTIVITY_LOG")
    @SequenceGenerator(name = "SEQ_SM_USER_ACTIVITY_LOG", sequenceName = "SEQ_SM_USER_ACTIVITY_LOG", allocationSize = 1)
    @Column(name = "SUAL_ACTIVITY_ID", nullable = false)
    private Long activityId;

    @ManyToOne
    @JoinColumn(name = "SUA_USER_ID")
    private UserAuth userAuth;

    @Column(name = "SUAL_ACTION", nullable = false)
    private String action;

    @Column(name = "SUAL_TIMESTAMP", nullable = false)
    private Timestamp timestamp;

     @Column(name = "SUAL_METADATA", columnDefinition = "jsonb") // Specify jsonb type
    private String metadata;

    @Column(name = "SUAL_RECORD_START_DATE", nullable = false)
    private Timestamp recordStartDate;

    @Column(name = "SUAL_RECORD_END_DATE", nullable = false)
    private Timestamp recordEndDate;

    @Column(name = "SUAL_CREATED_BY", nullable = false)
    private String createdBy;

    @Column(name = "SUAL_UPDATED_BY")
    private String updatedBy;

    @Column(name = "SUAL_DELETED_FLAG", nullable = false)
    private Boolean deletedFlag = false;
}