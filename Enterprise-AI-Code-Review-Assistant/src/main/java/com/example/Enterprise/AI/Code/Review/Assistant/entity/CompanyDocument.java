package com.example.Enterprise.AI.Code.Review.Assistant.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "company_documents")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;

    @Column(columnDefinition = "TEXT")
    private String extractedText;
}