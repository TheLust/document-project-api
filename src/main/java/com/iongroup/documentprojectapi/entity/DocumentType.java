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
public class DocumentType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "type")
    private List<Document> documents;

    @Column(columnDefinition = "varchar(5)",
            nullable = false,
            unique = true)
    @Check(constraints = "code ~ '^[a-zA-Z0-9 ]+$'")
    private String code;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "text")
    private String description;

    @Column(nullable = false)
    private Boolean isMacro;

    @Column(nullable = false)
    private Boolean isDateGrouped;
}
