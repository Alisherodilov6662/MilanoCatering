package com.example.service;

import com.example.dto.MessageDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class SendMessageService {


    public MessageDTO send(MessageDTO dto) {
        String message = """
                name : %s
                phone : %s
                message : %s
                """;
        String msg = String.format(message, dto.getName(), dto.getPhone(), dto.getMessage());

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getForEntity("https://api.telegram.org/bot5807548287:AAHvvq0YhxutTZRKX2TsZ7oYsGuvqM4o1cY/sendMessage?chat_id=575079444&text="+msg, String.class);
        return dto;
    }
}
