package vn.edu.likelion.OrderManagement.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vn.edu.likelion.OrderManagement.service.FileUpload;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileUploadImpl implements FileUpload {

    @Autowired
    private final Cloudinary cloudinary;

    @Override
    public String uploadFile(MultipartFile multipartFile) throws IOException {

        Map r = this.cloudinary.uploader().upload(multipartFile.getBytes(),
                ObjectUtils.asMap("resource_type", "auto"));


//    return cloudinary.uploader().upload(multipartFile.getBytes(),
//            Map.of("public_id", UUID.randomUUID().toString()))
//            .get("url")
//            .toString();


        String img = (String) r.get("secure_url");

        return img;
    }
}
