package com.example.service;

import com.example.dto.attach.AttachFullInfoDTO;
import com.example.entity.AttachEntity;
import com.example.exception.OriginalFileNameNullException;
import com.example.repositiry.AttachRepository;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class AttachService {

    private final AttachRepository attachRepository;

    public AttachService(AttachRepository attachRepository) {
        this.attachRepository = attachRepository;
    }

    @Value("${attach.upload.folder}")
    private String attachUploadFolder;

    public String getExtension(String fileName) {
        // mp3/jpg/npg/mp4.....
        if (fileName == null) {
            throw new OriginalFileNameNullException("File name maybe null");
        }
        int lastIndex = fileName.lastIndexOf(".");
        return fileName.substring(lastIndex + 1);
    }



    public AttachFullInfoDTO saveToSystem(MultipartFile file) {

        try {
            File folder = new File(attachUploadFolder); // attaches/
            if (!folder.exists()) folder.mkdirs();

            AttachEntity entity = new AttachEntity();
            entity.setName(file.getOriginalFilename());
            entity.setCreatedDate(LocalDateTime.now());
            attachRepository.save(entity);

            String extension = getExtension(file.getOriginalFilename());

            byte[] bytes = file.getBytes();
            Path path = Paths.get(attachUploadFolder + entity.getId() + "." + extension);
            Files.write(path, bytes);


            AttachFullInfoDTO dto = new AttachFullInfoDTO();
            dto.setId(entity.getId());
            dto.setCreatedDate(entity.getCreatedDate());
            dto.setName(entity.getName());
            return dto;
        }
        catch (IOException e){
            System.out.println(e.getMessage());

        }

        return null;
    }
}
