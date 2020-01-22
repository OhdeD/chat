package com.chat.service;

import com.chat.domain.Conversation;
import com.chat.domain.Message;
import com.chat.exception.ConversationNotFoundException;
import com.chat.repository.ConvRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConvDbService {
    @Autowired
    ConvRepo convRepo;
    private static final Logger LOGGER = LoggerFactory.getLogger (ConvDbService.class);

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
            LOGGER.info("Conversation found");
            return convRepo.findByParticipants(p).get();
        } else {
            return startConversation(p);
        }
    }
    public List<Message>getListOfMessagesFromConversation(Conversation c){
        for (Message m: c.getMessages()){
            m.setRead(true);
        }
        return c.getMessages();
    }

    public Conversation startConversation(String p) {
        LOGGER.info("Starting new conversation");
        Conversation c = new Conversation();
        c.setParticipants(p);
        return convRepo.save(c);
    }

    public void delete(Long id) {
        convRepo.deleteById(id);
    }
}
