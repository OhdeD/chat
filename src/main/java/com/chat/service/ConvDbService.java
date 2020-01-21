package com.chat.service;

import com.chat.domain.Conversation;
import com.chat.domain.Message;
import com.chat.exception.ConversationNotFoundException;
import com.chat.repository.ConvRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConvDbService {
    @Autowired
    ConvRepo convRepo;

    public Conversation save(Conversation conv) {
        return convRepo.save(conv);
    }

    public Conversation findById(Long id) throws ConversationNotFoundException {
        return convRepo.findById(id).orElseThrow(ConversationNotFoundException::new);
    }

    public Conversation getConversation(Long id, Long id2) {
        String p;
        if (id < id2) {
            p = id + "-" + id2;
        } else {
            p = id2 + "-" + id;
        }
        if (convRepo.findByParticipants(p).isPresent()) {
            return convRepo.findByParticipants(p).get();
        } else {
            return startConversation(p);
        }
    }
    public List<Message>getListOfMessagesFromConversation(Conversation c){
        return c.getMessages();
    }

    private Conversation startConversation(String p) {
        Conversation c = new Conversation();
        c.setParticipants(p);
        return convRepo.save(c);
    }

    public void delete(Long id) {
        convRepo.deleteById(id);
    }
}
