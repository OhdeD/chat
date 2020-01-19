package com.chat.service;

import com.chat.domain.Conversation;
import com.chat.exception.ConversationNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ConversationDBTestSuite {
    @Autowired
    ConvDbService convDbService;

    @Test
    public void testSaveToConvDb() {
        //Given
        Conversation conv = new Conversation();
        conv.setParticipants("user1-user2");
        //When
        convDbService.save(conv);
        Long id = conv.getId();
        //Then
        assertNotNull(id);
    }

    @Test
    public void testFindByIdFromConvDB() throws ConversationNotFoundException {
        //Given
        Conversation conv = new Conversation();

        //When
        convDbService.save(conv);
        Conversation a;
        try {
            a = convDbService.findById(conv.getId());
        } catch (ConversationNotFoundException e) {
            a = null;
        }

        //Then
        assertNotNull(a);
    }

    @Test
    public void testDeleteByIdFromConvDB() throws ConversationNotFoundException {
        //Given
        Conversation conv = new Conversation();

        //When
        convDbService.save(conv);
        Long id = conv.getId();
        convDbService.delete(id);
        Conversation deleted;
        try {
            deleted = convDbService.findById(id);
        }catch (ConversationNotFoundException e){
            deleted = null;
        }

        //Then
        assertNull(deleted);
    }
}
