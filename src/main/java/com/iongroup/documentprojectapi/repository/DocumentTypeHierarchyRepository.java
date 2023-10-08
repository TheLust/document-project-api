package com.iongroup.documentprojectapi.repository;

import com.iongroup.documentprojectapi.embeddable.DocumentTypeHierarchyPrimaryKey;
import com.iongroup.documentprojectapi.entity.DocumentTypeHierarchy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentTypeHierarchyRepository extends JpaRepository<DocumentTypeHierarchy, DocumentTypeHierarchyPrimaryKey> {
}
