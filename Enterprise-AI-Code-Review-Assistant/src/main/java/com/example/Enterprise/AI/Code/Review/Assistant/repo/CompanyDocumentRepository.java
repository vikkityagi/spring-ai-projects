package com.example.Enterprise.AI.Code.Review.Assistant.repo;




import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Enterprise.AI.Code.Review.Assistant.entity.CompanyDocument;

public interface CompanyDocumentRepository
        extends JpaRepository<CompanyDocument, Long> {
}
