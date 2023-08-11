package com.example.restaurantbackend.repository;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileUploadInterface {
    String uploadFile(MultipartFile multipartFile) throws IOException;
}
