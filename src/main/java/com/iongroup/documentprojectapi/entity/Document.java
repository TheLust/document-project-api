package com.iongroup.documentprojectapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Check;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    private Institution institution;

    @NotNull
    @ManyToOne
    private User user;

    @NotNull
    @ManyToOne
    private DocumentType type;

    @NotNull
    @ManyToOne
    private Project project;

    @Column(columnDefinition = "varchar(260)",
            nullable = false)
    @Check(constraints = "name ~ '^[a-zA-Z0-9 ]+$'")
    private String name;

    @Column(nullable = false)
    private String path;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate uploadDate;

    @Column(columnDefinition = "text",
            nullable = false)
    @Check(constraints = "name ~ '^[a-zA-Z0-9]+$'")
    private String additionalInformation;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate groupingDate;
}
