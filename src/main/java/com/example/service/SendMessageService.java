package com.example.service;

import com.example.dto.MessageDTO;
import com.example.entity.MessageEntity;
import com.example.repositiry.SendMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
@RequiredArgsConstructor
public class SendMessageService {


    private final SendMessageRepository sendMessageRepository;
    public MessageDTO send(MessageDTO dto) {
//        MessageEntity entity=new MessageEntity();
//        entity.setName(dto.getName());
//        entity.setSurname(dto.getSurname());
//        entity.setPhone(dto.getPhone());
//        sendMessageRepository.save(entity);

        String message = """
                name : %s
                surname : %s
                phone : %s
                message : %s
                """;
        String msg = String.format(message, dto.getName(),dto.getSurname(), dto.getPhone(), dto.getMessage());

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getForEntity("https://api.telegram.org/bot5807548287:AAHvvq0YhxutTZRKX2TsZ7oYsGuvqM4o1cY/sendMessage?chat_id=575079444&text="+msg, String.class);
//        dto.setId(entity.getId());
        return dto;
    }
}
