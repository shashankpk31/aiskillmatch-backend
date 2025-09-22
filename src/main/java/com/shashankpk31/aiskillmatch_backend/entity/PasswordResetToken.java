package com.shashankpk31.aiskillmatch_backend.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "sm_password_reset_tokens")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PasswordResetToken {
    @Id
    @SequenceGenerator(name = "SEQ_SM_SPRT_ID", sequenceName = "SEQ_SM_SPRT_ID", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SM_SPRT_ID")
    @Column(name = "sprt_id")
    private Long id;

    @Column(name = "sua_user_id", nullable = false)
    private Long userId;

    @Column(name = "sprt_token", nullable = false, unique = true)
    private String token;

    @Column(name = "sprt_expires_at", nullable = false)
    private LocalDateTime expiresAt;

    @Column(name = "sprt_used", nullable = false)
    private Boolean used = false;

    // === audit columns ===
    @Column(name = "sprt_record_start_date", nullable = false)
    private LocalDateTime recordStartDate = LocalDateTime.now();

    @Column(name = "sprt_record_end_date", nullable = false)
    private LocalDateTime recordEndDate = LocalDateTime.of(9999, 1, 1, 0, 0);

    @Column(name = "sprt_created_by", nullable = false)
    private String createdBy = "SYSTEM";

    @Column(name = "sprt_updated_by")
    private String updatedBy;

    @Column(name = "sprt_deleted_flag", nullable = false)
    private Boolean deletedFlag = false;
}
