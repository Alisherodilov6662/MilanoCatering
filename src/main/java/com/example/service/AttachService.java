package com.example.service;

import com.example.dto.attach.AttachFullInfoDTO;
import com.example.entity.AttachEntity;
import com.example.exception.ItemNotFoundException;
import com.example.exception.OriginalFileNameNullException;
import com.example.repositiry.AttachRepository;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Optional;
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

            String extension = getExtension(file.getOriginalFilename());
            System.out.println("extension = " + extension);

            AttachEntity entity = new AttachEntity();
            entity.setName(file.getOriginalFilename());
            entity.setCreatedDate(LocalDateTime.now());
            entity.setExtension(extension);
            attachRepository.save(entity);


            byte[] bytes = file.getBytes();
            Path path = Paths.get(attachUploadFolder + entity.getId()+ "." + entity.getExtension());
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

    private AttachEntity get(Long id) {

        Optional<AttachEntity> optional = attachRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ItemNotFoundException("File Not Found");
        }
        return optional.get();
    }

    public byte[] open(Long id) {
        try {

            AttachEntity entity = get(id);
            Path file = Paths.get(attachUploadFolder + entity.getId() + "." + entity.getExtension());
            return Files.readAllBytes(file);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public Boolean delete(Long id) {
        try {
            AttachEntity entity = get(id);
            Path file = Paths.get(attachUploadFolder + entity.getId() + "." + entity.getExtension());

            Files.delete(file);
            attachRepository.deleteById(entity.getId());

            return true;
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }

    }

}
