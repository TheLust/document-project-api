package com.iongroup.documentprojectapi.repository;

import com.iongroup.documentprojectapi.entity.Institution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InstitutionRepository extends JpaRepository<Institution, Long> {
    Optional<Institution> findByCode(String code);
    Optional<Institution> findByName(String name);
}
