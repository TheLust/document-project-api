package com.iongroup.documentprojectapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Check;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    private Institution institution;

    @NotNull
    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "project")
    private List<Document> documents;

    @Column(nullable = false)
    @Check(constraints = "name ~ '^[a-zA-Z0-9 ]+$'")
    private String name;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate startDate;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate finishDate;

    @Column(columnDefinition = "varchar(1000)",
            nullable = false)
    @Check(constraints = "name ~ '^[a-zA-Z0-9 ]+$'")
    private String additionalInformation;

    @Column(nullable = false)
    private Boolean isActive;
}
