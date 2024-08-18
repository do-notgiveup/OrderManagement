package vn.edu.likelion.OrderManagement.util;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class UploadImage {

    public String uploadImage(MultipartFile file) throws IOException {

//        Cloudinary cloudinary = new Cloudinary();

        Map config = new HashMap();
        config.put("cloud_name", "n07t21i7");
        config.put("api_key", "123456789012345");
        config.put("api_secret", "abcdeghijklmnopqrstuvwxyz12");        
        Cloudinary cloudinary = new Cloudinary(config);

        cloudinary.uploader().upload("my_picture.jpg", ObjectUtils.emptyMap());

        cloudinary.url().generate("abcfrmo8zul1mafopawefg.jpg");



        return "upload successful";
    }
}
