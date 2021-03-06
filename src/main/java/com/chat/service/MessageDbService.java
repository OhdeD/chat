package com.chat.service;

import com.chat.domain.Conversation;
import com.chat.domain.Message;
import com.chat.exception.ConversationNotFoundException;
import com.chat.exception.MessageNotFoundException;
import com.chat.repository.MessegeRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MessageDbService  {
    @Autowired
    MessegeRepo messegeRepo;
    @Autowired
    ConvDbService convDbService;
    @Autowired
    ChatUserDbService chatUserDbService;

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageDbService.class);

    public Message save(Long id, Long id2, String message)  {
        Message m = new Message();
        m.setMessage(message);
        m.setSenderId(id);
        m.setReceiverId(id2);
        m.setSendingDate(LocalDateTime.now());
        Long conversationId = convDbService.getConversation(id, id2).getId();
        m.setConversationId(conversationId);
        convDbService.getConversation(id, id2).getMessages().add(m);
        return messegeRepo.save(m);
    }

    public Message findById(Long id) throws MessageNotFoundException {
        return messegeRepo.findById(id).orElseThrow(MessageNotFoundException::new);
    }

    public void deleteById(Long id) {
        try {
            Conversation c = convDbService.findById( messegeRepo.findById(id).get().getConversationId());
             c.getMessages().removeIf(a->a.getId().equals(id));
            convDbService.save(c);
        } catch (ConversationNotFoundException e) {
            LOGGER.warn(e.getMessage(), e);
        }
        messegeRepo.deleteById(id);
    }
}
