package vn.edu.likelion.OrderManagement.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileUpload {
    String uploadFile(MultipartFile file) throws IOException;
}
