package vn.edu.likelion.OrderManagement.controller;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.edu.likelion.OrderManagement.service.FileUpload;

@RestController
@RequestMapping("/api/v1/auth/image")
@CrossOrigin(origins = {"http://192.168.18.81:5173", "http://localhost:9999", "jdbc:mysql://viaduct.proxy.rlwy.net:37349/railway"}, allowCredentials = "true")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ImageController {

    @Autowired
    FileUpload fileUpload;

    @PostMapping
    public String uploadImage(@RequestPart MultipartFile file) throws Exception {
        return fileUpload.uploadFile(file);
    }

}
