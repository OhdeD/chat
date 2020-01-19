package com.chat.service;

import com.chat.domain.ChatUser;
import com.chat.domain.FriendsList;
import com.chat.exception.ChatUserNotFoundException;
import com.chat.repository.ChatUserRepo;
import com.chat.repository.FriendsListRepo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ChatDbTestSuite {
    @Autowired
    ChatUserDbService chatUserDbService;
    @Autowired
    FriendsListDbService friendsListDbService;

    @Autowired
    ChatUserRepo chatUserRepo;
    @Autowired
    FriendsListRepo friendsListRepo;


    @Test
    public void testChatUserSaveToDB() {
        //Given
        ChatUser user = new ChatUser("Dagmara", "Kopaczyk", "dagmara@mail", "haslo", "poznan", false);
        ChatUser user2 = new ChatUser("Dadsadd", "Kopasdasda", "dagsdara@mail", "haslo", "ddddznan", false);

        //When
        chatUserDbService.save(user);
        chatUserDbService.save(user2);

        Long userId = user.getId();
        Long user2Id = user2.getId();

        //Then
        Assert.assertNotNull(userId);
        Assert.assertNotNull(user2Id);

        //CleanUp
        chatUserDbService.deleteById(userId);
        chatUserDbService.deleteById(user2Id);
    }

    @Test
    public void testChatUserDeleteFromDB() throws ChatUserNotFoundException {
        //Given
        ChatUser user = new ChatUser("Dagmara", "Kopaczyk", "dagmara@mail", "haslo", "poznan", false);
        ChatUser user2 = new ChatUser("Dadsadd", "Kopasdasda", "dagsdara@mail", "haslo", "ddddznan", false);

        //When
        chatUserDbService.save(user);
        chatUserDbService.save(user2);

        Long userId = user.getId();
        Long user2Id = user2.getId();

        chatUserDbService.deleteById(userId);
        chatUserDbService.deleteById(user2Id);
        ChatUser deletedUser1;
        ChatUser deletedUser2;
        try {
            deletedUser1 = chatUserDbService.findById(userId);
        } catch (ChatUserNotFoundException e) {
            deletedUser1 = null;
        }
        try {
            deletedUser2 = chatUserDbService.findById(user2Id);

        } catch (ChatUserNotFoundException e) {
            deletedUser2 = null;
        }

        //Then
        Assert.assertNotNull(userId);
        Assert.assertNotNull(user2Id);
        Assert.assertNotEquals(user, deletedUser1);
        Assert.assertNotEquals(user2, deletedUser2);
    }

    @Test
    public void testChatUserFindById() throws ChatUserNotFoundException {
        //Given
        ChatUser user = new ChatUser("Dagmara", "Kopaczyk", "dagmara@mail", "haslo", "poznan", false);
        ChatUser user2 = new ChatUser("Dadsadd", "Kopasdasda", "dagsdara@mail", "haslo", "ddddznan", false);

        //When
        chatUserDbService.save(user);
        chatUserDbService.save(user2);

        Long userId = user.getId();
        Long user2Id = user2.getId();

        ChatUser foundUser1 = chatUserDbService.findById(userId);
        ChatUser foundUser2 = chatUserDbService.findById(user2Id);

        //Then
        Assert.assertNotNull(foundUser1);
        Assert.assertNotNull(foundUser2);

        //Clean-up
        chatUserDbService.deleteById(userId);
        chatUserDbService.deleteById(user2Id);
    }

}
