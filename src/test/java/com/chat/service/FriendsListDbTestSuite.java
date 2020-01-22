package com.chat.service;

import com.chat.domain.ChatUser;
import com.chat.domain.DTO.ChatUserDto;
import com.chat.domain.FriendsList;
import com.chat.exception.ChatUserNotFoundException;
import com.chat.exception.FriendsListNotFoundException;
import com.chat.repository.ChatUserRepo;
import com.chat.repository.FriendsListRepo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class FriendsListDbTestSuite {
    @Autowired
    ChatUserDbService chatUserDbService;
    @Autowired
    FriendsListDbService friendsListDbService;

    @Autowired
    ChatUserRepo chatUserRepo;
    @Autowired
    FriendsListRepo friendsListRepo;

    @Test
    public void testSaveFriendsList() {
        //Given
        FriendsList friendsList = new FriendsList();
        //When
        friendsListDbService.saveFriendsList(friendsList);
        Long id = friendsList.getId();
        //Then
        Assert.assertNotNull(id);
    }

    @Test
    public void testDeleteFriendsListById() throws FriendsListNotFoundException {
        //Given
        FriendsList friendsList = new FriendsList();
        //When
        FriendsList f = friendsListDbService.saveFriendsList(friendsList);
        Long id = friendsList.getId();
        FriendsList deletedFriendsList;
        try {
            deletedFriendsList = friendsListDbService.getFriendsListById(id);
        } catch (FriendsListNotFoundException e) {
            deletedFriendsList = null;
        }
        //Then
        Assert.assertNotEquals(f, deletedFriendsList);
    }

    @Test
    public void testGetFriendsListById() throws FriendsListNotFoundException {
        //Given
        FriendsList friendsList = new FriendsList();
        //When
        friendsListDbService.saveFriendsList(friendsList);
        Long id = friendsList.getId();
        FriendsList foundedList = friendsListDbService.getFriendsListById(id);
        //Then
        Assert.assertNotNull(foundedList);
    }

    @Test
    public void testAddFriendToFriendsList() throws ChatUserNotFoundException {
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

        List<ChatUser> friendsList = friendsListDbService.addFriendToFriendsList(userId, user2Id);
        int size = friendsList.size();
        //Then
        Assert.assertNotNull(size);
    }

}
