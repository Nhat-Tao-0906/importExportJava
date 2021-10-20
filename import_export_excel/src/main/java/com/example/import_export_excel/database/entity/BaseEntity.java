package com.example.import_export_excel.database.entity;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@MappedSuperclass
@Data
public class BaseEntity {
    private static final long serialVersionUID = 1L;

    @CreationTimestamp
    @Basic(
        optional = true
    )
    @Column(
        name = "created_at",
        nullable = true,
        updatable = false
    )

    private Date created;

    @UpdateTimestamp
    @Basic(
        optional = true
    )
    @Column(
        name = "updated_at",
        nullable = true
    )

    private Date updated;
}
