package com.example.Enterprise.AI.Code.Review.Assistant.service;

import lombok.RequiredArgsConstructor;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.Loader;

import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.Enterprise.AI.Code.Review.Assistant.entity.CompanyDocument;

import com.example.Enterprise.AI.Code.Review.Assistant.repo.CompanyDocumentRepository;


import java.io.IOException;

@Service
@RequiredArgsConstructor
public class DocumentService {

    private final CompanyDocumentRepository repository;

    

    public String uploadDocument(MultipartFile file) throws IOException {

        PDDocument document = Loader.loadPDF(file.getBytes());

        PDFTextStripper stripper = new PDFTextStripper();

        String extractedText = stripper.getText(document);

        document.close();

        CompanyDocument companyDocument = CompanyDocument.builder()
                .fileName(file.getOriginalFilename())
                .extractedText(extractedText)
                .build();

        CompanyDocument savedDocument = repository.save(companyDocument);

        // createChunks(savedDocument.getId(), extractedText);

        return "Document uploaded successfully";
    }

    
}