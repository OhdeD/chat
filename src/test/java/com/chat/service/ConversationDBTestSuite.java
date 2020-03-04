package com.chat.service;

import com.chat.domain.ChatUser;
import com.chat.domain.Conversation;
import com.chat.domain.DTO.ChatUserDto;
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
    @Autowired
    ChatUserDbService chatUserDbService;
    @Autowired
    MessageDbService messageDbService;

    @Test
    public void testSave() {
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
    public void testFindById() throws ConversationNotFoundException {
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

    @Test
    public void testGetConversation(){
        //Given
        ChatUserDto user = ChatUserDto.builder()
                .name("Dagmara")
                .surname("KOpaczyk")
                .mail("dagmara@mail")
                .password("password")
                .city("Poznan").build();
        ChatUserDto user2 = ChatUserDto.builder()
                .name("Ania")
                .surname("Nowak")
                .mail("aniaa@mail")
                .password("password")
                .city("Poznan").build();
        Conversation conversation = new Conversation();
        //When
        ChatUser u =chatUserDbService.save(user);
        ChatUser u2 = chatUserDbService.save(user2);
        Long userId = u.getId();
        Long user2Id = u2.getId();
        String participants = userId + "-" + user2Id;
        conversation.setParticipants(participants);
        convDbService.save(conversation);
        Conversation x = convDbService.getConversation(userId, user2Id);
        //Then
        assertNotNull(x);
    }

    @Test
    public void testStartConversation(){
        //Given
        ChatUserDto user = ChatUserDto.builder()
                .name("Dagmara")
                .surname("KOpaczyk")
                .mail("dagmara@mail")
                .password("password")
                .city("Poznan").build();
        ChatUserDto user2 = ChatUserDto.builder()
                .name("Ania")
                .surname("Nowak")
                .mail("aniaa@mail")
                .password("password")
                .city("Poznan").build();
        //When
        Long userId = chatUserDbService.save(user).getId();
        Long user2Id = chatUserDbService.save(user2).getId();
        String participants = userId + "-" + user2Id;

        Conversation x = convDbService.startConversation(participants);
        Long convId = x.getId();
        //Then
        assertNotNull(x);
        assertNotNull(convId);
    }

}
