package com.chat.service;

import com.chat.domain.ChatUser;
import com.chat.exception.ChatUserNotFoundException;
import com.chat.repository.ChatUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ChatUserDbService {

    @Autowired
    ChatUserRepo chatUserRepo;

    public ChatUser save(final ChatUser chatUser){
        return chatUserRepo.save(chatUser);
    }

    public void deleteById(Long id){
        chatUserRepo.deleteById(id);
    }

    public ChatUser findById(Long id)throws ChatUserNotFoundException {
        return chatUserRepo.findById(id).orElseThrow(ChatUserNotFoundException::new);
    }

}
