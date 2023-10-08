package com.iongroup.documentprojectapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Check;

import java.util.List;

@Entity
@Getter
@Setter
public class Institution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "institution")
    private List<Project> projects;

    @OneToMany(mappedBy = "institution")
    private List<Document> documents;

    @Column(columnDefinition = "varchar(5)",
            nullable = false,
            unique = true)
    @Check(constraints = "code ~ '^[a-zA-Z0-9 ]+$'")
    private String code;

    @Column(nullable = false)
    @Check(constraints = "name ~ '^[a-zA-Z0-9 ]+$'")
    private String name;

    @Column(columnDefinition = "text")
    private String additionalInformation;
}
