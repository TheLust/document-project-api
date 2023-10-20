package com.iongroup.documentprojectapi.repository;

import com.iongroup.documentprojectapi.entity.Document;
import com.iongroup.documentprojectapi.entity.Institution;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
    Optional<Document> findByPath(String path);
    List<Document> findAllByInstitution(@NotNull Institution institution);
}
