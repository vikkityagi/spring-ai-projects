package com.example.Enterprise.AI.Code.Review.Assistant.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.Enterprise.AI.Code.Review.Assistant.service.DocumentService;

@RestController
@RequestMapping("/api/documents")
@RequiredArgsConstructor
public class DocumentController {

    private final DocumentService documentService;

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file)
            throws Exception {

        return documentService.uploadDocument(file);
    }
}