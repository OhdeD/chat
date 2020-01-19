package com.chat.service;

import com.chat.domain.ChatUser;
import com.chat.domain.Conversation;
import com.chat.domain.Message;
import com.chat.exception.MessageNotFoundException;
import com.chat.repository.MessegeRepo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.verification.InOrderWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;

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
        ChatUser user = new ChatUser("Dagmara", "Kopaczyk", "dagmara@mail", "haslo", "poznan", false);
        ChatUser user2 = new ChatUser("Dadsadd", "Kopasdasda", "dagsdara@mail", "haslo", "ddddznan", false);
        Conversation conv = new Conversation();

        //When
        chatUserDbService.save(user);
        chatUserDbService.save(user2);
        convDbService.save(conv);
        Long convId = conv.getId();
        Long userId = user.getId();
        Long user2Id = user2.getId();

        Message message = new Message(userId, user2Id, "Hello world!", LocalDateTime.now(), convId);

        Message savedM = messageDbService.save(message);
        Long messageId = savedM.getId();

        //Then
        Assert.assertNotNull(messageId);

        //Clean-up
        messageDbService.deleteById(messageId);
        chatUserDbService.deleteById(userId);
        chatUserDbService.deleteById(user2Id);
    }

    @Test
    public void testDeleteMessageFromDb() throws MessageNotFoundException {
        //Given
        ChatUser user = new ChatUser("Dagmara", "Kopaczyk", "dagmara@mail", "haslo", "poznan", false);
        ChatUser user2 = new ChatUser("Dadsadd", "Kopasdasda", "dagsdara@mail", "haslo", "ddddznan", false);
        Conversation conv = new Conversation();

        //When
        chatUserDbService.save(user);
        chatUserDbService.save(user2);
        convDbService.save(conv);
        Long convId = conv.getId();
        Long userId = user.getId();
        Long user2Id = user2.getId();

        Message message = new Message(userId, user2Id, "Hello world!", LocalDateTime.now(), convId);
        Message savedM = messageDbService.save(message);
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

        //Clean-up
        chatUserDbService.deleteById(userId);
        chatUserDbService.deleteById(user2Id);
    }

    @Test
    public void testFindByIdMessageFromDb() throws MessageNotFoundException {
        //Given
        ChatUser user = new ChatUser("Dagmara", "Kopaczyk", "dagmara@mail", "haslo", "poznan", false);
        ChatUser user2 = new ChatUser("Dadsadd", "Kopasdasda", "dagsdara@mail", "haslo", "ddddznan", false);
        Conversation conv = new Conversation();

        //When
        chatUserDbService.save(user);
        chatUserDbService.save(user2);
        convDbService.save(conv);
        Long convId = conv.getId();
        Long userId = user.getId();
        Long user2Id = user2.getId();

        Message message = new Message(userId, user2Id, "Hello world!", LocalDateTime.now(), convId);
        Message savedM = messageDbService.save(message);
        Long savedMId = savedM.getId();
        Long foundId = messageDbService.findById(savedMId).getId();

        //Then
        Assert.assertEquals(savedMId, foundId);

        //Clean-up
        messageDbService.deleteById(savedMId);
        chatUserDbService.deleteById(userId);
        chatUserDbService.deleteById(user2Id);
    }
}
