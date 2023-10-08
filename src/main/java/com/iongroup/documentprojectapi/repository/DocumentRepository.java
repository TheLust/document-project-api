package com.iongroup.documentprojectapi.repository;

import com.iongroup.documentprojectapi.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
}
