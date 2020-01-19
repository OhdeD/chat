package com.chat.service;

import com.chat.domain.Conversation;
import com.chat.exception.ConversationNotFoundException;
import com.chat.repository.ConvRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConvDbService {
    @Autowired
    ConvRepo convRepo;

    public Conversation save(Conversation conv){
        return convRepo.save(conv);
    }

    public Conversation findById(Long id)throws ConversationNotFoundException {
        return convRepo.findById(id).orElseThrow(ConversationNotFoundException::new);
    }

    public void delete(Long id){
        convRepo.deleteById(id);
    }
}
