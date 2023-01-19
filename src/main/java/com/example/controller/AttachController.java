package com.example.controller;

import com.example.dto.attach.AttachFullInfoDTO;
import com.example.service.AttachService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/attach")
public class AttachController {

    private final AttachService attachService;

    public AttachController(AttachService attachService) {
        this.attachService = attachService;
    }

    @Operation(summary = "Method for upload", description = "This method used to  upload file")
    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) {
        AttachFullInfoDTO fileName = attachService.saveToSystem(file);
        return ResponseEntity.ok().body(fileName);
    }


    @Operation(summary = "Method for open", description = "This method used to open file")
    @GetMapping(value = "/open/{id}", produces = MediaType.ALL_VALUE)
    public byte[] open(@PathVariable("id") Long id) {
        return attachService.open(id);
    }

    @Operation(summary = "Method for delete", description = "This method used to  delete file")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        Boolean result = attachService.delete(id);
        return ResponseEntity.ok(result);
    }

}
