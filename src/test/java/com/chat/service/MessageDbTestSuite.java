package com.chat.service;

import com.chat.domain.ChatUser;
import com.chat.domain.DTO.ChatUserDto;
import com.chat.domain.Message;
import com.chat.exception.MessageNotFoundException;
import com.chat.repository.MessegeRepo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class MessageDbTestSuite {
    @Autowired
    MessegeRepo messegeRepo;
    @Autowired
    MessageDbService messageDbService;
    @Autowired
    ChatUserDbService chatUserDbService;
    @Autowired
    FriendsListDbService friendsListDbService;
    @Autowired
    ConvDbService convDbService;

    @Test
    public void testSaveMessageToDb() {
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
        ChatUser u =chatUserDbService.save(user);
        ChatUser u2 = chatUserDbService.save(user2);
        Long userId = u.getId();
        Long user2Id = u2.getId();

        String message = "Hello world!";
        Message savedM = messageDbService.save(userId, user2Id, message);
        Long messageId = savedM.getId();

        //Then
        Assert.assertNotNull(messageId);
    }

    @Test
    public void testDeleteMessageFromDb() throws MessageNotFoundException {
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
        ChatUser u = chatUserDbService.save(user);
        ChatUser u2 = chatUserDbService.save(user2);

        Long userId = u.getId();
        Long user2Id = u2.getId();

        String message = "Hello world!";

        Message savedM = messageDbService.save(userId, user2Id, message);
        Long messageId = savedM.getId();
        messageDbService.deleteById(messageId);
        Message deletedM;
        try {
            deletedM = messageDbService.findById(messageId);
        } catch (MessageNotFoundException e) {
            deletedM = null;
        }

        //Then
        Assert.assertNotEquals(message, deletedM);
    }

    @Test
    public void testFindByIdMessageFromDb() throws MessageNotFoundException {
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
        ChatUser u = chatUserDbService.save(user);
        ChatUser u2 = chatUserDbService.save(user2);
        Long userId = u.getId();
        Long user2Id = u2.getId();

        String message = "Hello world!";

        Message savedM = messageDbService.save(userId, user2Id, message);
        Long savedMId = savedM.getId();
        Long foundId = messageDbService.findById(savedMId).getId();

        //Then
        Assert.assertEquals(savedMId, foundId);
    }
}
