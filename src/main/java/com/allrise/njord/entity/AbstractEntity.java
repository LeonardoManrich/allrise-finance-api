package com.allrise.njord.entity;

import jakarta.persistence.Column;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

public abstract class AbstractEntity {

    @CreationTimestamp
    @Column(name = "CREATED_AT", nullable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "UPDATED_AT", nullable = false)
    private Timestamp updatedAt;
}
