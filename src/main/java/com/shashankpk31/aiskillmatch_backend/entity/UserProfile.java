package com.shashankpk31.aiskillmatch_backend.entity;

import java.sql.Timestamp;

import com.fasterxml.jackson.databind.JsonNode;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SM_USER_PROFILE")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SM_USER_PROFILE")
    @SequenceGenerator(name = "SEQ_SM_USER_PROFILE", sequenceName = "SEQ_SM_USER_PROFILE", allocationSize = 1)
    @Column(name = "SUP_USER_ID", nullable = false)
    private Long userProfileId;

    @ManyToOne
    @JoinColumn(name = "SUA_USER_ID", nullable = false)
    private UserAuth userAuth;

    @Column(name = "SUP_FULL_NAME", nullable = false)
    private String fullName;

    @Column(name = "SUP_HEADLINE")
    private String headline;

    @Column(name = "SUP_BIO")
    private String bio;

    @JdbcTypeCode(SqlTypes.ARRAY)
    @Column(name = "SUP_SKILLS", columnDefinition = "text[]")
    private String[] skills;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "SUP_EXPERIENCE", columnDefinition = "jsonb")
    private JsonNode experience;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "SUP_EDUCATION", columnDefinition = "jsonb")
    private JsonNode education;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "SUP_PROJECTS", columnDefinition = "jsonb")
    private JsonNode projects;

    @Column(name = "SUP_RECORD_START_DATE", nullable = false)
    private Timestamp recordStartDate;

    @Column(name = "SUP_RECORD_END_DATE", nullable = false)
    private Timestamp recordEndDate;

    @Column(name = "SUP_CREATED_BY", nullable = false)
    private String createdBy;

    @Column(name = "SUP_UPDATED_BY")
    private String updatedBy;

    @Column(name = "SUP_DELETED_FLAG", nullable = false)
    private Boolean deletedFlag = false;
}