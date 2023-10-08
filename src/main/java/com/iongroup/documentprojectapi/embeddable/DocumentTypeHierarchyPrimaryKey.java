package com.iongroup.documentprojectapi.embeddable;

import com.iongroup.documentprojectapi.entity.DocumentType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;

@Embeddable
public class DocumentTypeHierarchyPrimaryKey implements Serializable {

    @ManyToOne
    private DocumentType macro;

    @ManyToOne
    private DocumentType micro;
}
