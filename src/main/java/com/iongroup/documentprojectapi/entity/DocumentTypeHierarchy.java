package com.iongroup.documentprojectapi.entity;

import com.iongroup.documentprojectapi.embeddable.DocumentTypeHierarchyPrimaryKey;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class DocumentTypeHierarchy {

    @EmbeddedId
    private DocumentTypeHierarchyPrimaryKey id;
}
