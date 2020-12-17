package com.rkuzmych.library.Service;

import com.rkuzmych.library.domain.Book;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class BookService {
    @Value("${upload.path}")
    private String uploadPath;

    public void saveFile(Book book, MultipartFile file, String key) throws IOException {
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            if (key == "photo") {
                file.transferTo(new File(uploadPath + "/img/" + resultFilename));
                book.setFileName(resultFilename);
            } else if(key == "pdf") {
                file.transferTo(new File(uploadPath + "/pdf/" + resultFilename));
                book.setPdfName(resultFilename);
            }
        }
    }

    public String getUploadPath() {
        return uploadPath;
    }

    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }


}
