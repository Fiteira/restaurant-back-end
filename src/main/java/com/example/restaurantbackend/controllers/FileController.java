package com.example.restaurantbackend.controllers;


import com.example.restaurantbackend.service.FileUploadInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class FileController {

    private final FileUploadInterface fileUpload;

    public FileController(FileUploadInterface fileUpload) {this.fileUpload = fileUpload;}

    private static final long MAX_FILE_SIZE_BYTES = 10 * 1024 * 1024; // 10 MB


    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("image") MultipartFile multipartFile) throws IOException {

        if (multipartFile.getSize() > MAX_FILE_SIZE_BYTES) {
            return  ResponseEntity.badRequest().body("File size is too big (max 10MB)");
        }
        return ResponseEntity.ok(fileUpload.uploadFile(multipartFile));
    }

}
