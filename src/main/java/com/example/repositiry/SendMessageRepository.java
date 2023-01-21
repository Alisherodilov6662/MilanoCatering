package com.example.repositiry;

import com.example.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SendMessageRepository extends JpaRepository<MessageEntity,Long> {
}
