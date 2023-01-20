package com.example.controller;

import com.example.dto.MessageDTO;
import com.example.service.SendMessageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/send")
public class SendMessageController {

    private final SendMessageService sendMessageService;

    @PostMapping("")
    HttpEntity<?> sendMessage(@RequestBody @Valid MessageDTO dto){
        MessageDTO result=sendMessageService.send(dto);
        return ResponseEntity.ok(result);
    }
}
