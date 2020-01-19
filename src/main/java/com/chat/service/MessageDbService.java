package com.chat.service;

import com.chat.domain.Message;
import com.chat.exception.ChatUserNotFoundException;
import com.chat.exception.MessageNotFoundException;
import com.chat.repository.MessegeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageDbService {
    @Autowired
    MessegeRepo messegeRepo;

    public Message save(Message message){
        return messegeRepo.save(message);
    }

    public Message findById(Long id)throws MessageNotFoundException {
        return messegeRepo.findById(id).orElseThrow(MessageNotFoundException::new);
    }

    public void deleteById(Long id){
        messegeRepo.deleteById(id);
    }
}
