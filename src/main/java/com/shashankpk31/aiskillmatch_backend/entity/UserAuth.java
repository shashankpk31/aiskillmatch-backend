package com.shashankpk31.aiskillmatch_backend.entity;

import lombok.*;
import java.sql.Timestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "SM_USER_AUTH")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAuth {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SM_USER_AUTH")
    @SequenceGenerator(name = "SEQ_SM_USER_AUTH", sequenceName = "SEQ_SM_USER_AUTH", allocationSize = 1)
    @Column(name = "SUA_USER_ID")
    private Long userId;

    @Column(name = "SUA_USERNAME", nullable = false, unique = true)
    private String username;

    @Column(name = "SUA_EMAIL", nullable = false, unique = true)
    private String email;

    @Column(name = "SUA_PASSWORD", nullable = false)
    private String password;

    @Column(name = "SUA_ROLE", nullable = false)
    private String role;

    @Column(name = "SUA_IS_ACTIVE")
    private Boolean isActive = true;

    @Column(name = "SUA_RECORD_START_DATE", nullable = false)
    private Timestamp recordStartDate;

    @Column(name = "SUA_RECORD_END_DATE", nullable = false)
    private Timestamp recordEndDate;

    @Column(name = "SUA_CREATED_BY", nullable = false)
    private String createdBy;

    @Column(name = "SUA_UPDATED_BY")
    private String updatedBy;

    @Column(name = "SUA_DELETED_FLAG")
    private Boolean deletedFlag = false;
}
