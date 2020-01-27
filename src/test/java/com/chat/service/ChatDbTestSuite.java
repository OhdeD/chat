package com.chat.service;

import com.chat.domain.ChatUser;
import com.chat.domain.DTO.ChatUserDto;
import com.chat.exception.ChatUserNotFoundException;
import com.chat.repository.ChatUserRepo;
import com.chat.repository.FriendsListRepo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

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
    public void testChatUserSave() {
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
        //Then
        Assert.assertNotNull(userId);
        Assert.assertNotNull(user2Id);
    }

    @Test
    public void testChatUserDeleteById() throws ChatUserNotFoundException {
        //Given
        ChatUserDto user = ChatUserDto.builder()
                .name("Dagmara")
                .surname("KOpaczyk")
                .mail("dagmara@mail")
                .password("password")
                .city("Poznan").build();
        //When
        ChatUser u = chatUserDbService.save(user);
        Long userId = u.getId();
        chatUserDbService.delete(userId);
        ChatUser deletedUser1;
        try {
            deletedUser1 = chatUserDbService.findById(userId);
        } catch (ChatUserNotFoundException e) {
            deletedUser1 = null;
        }

        //Then
        Assert.assertNotNull(userId);
        Assert.assertNotEquals(u, deletedUser1);
    }

    @Test
    public void testChatUserFindById() throws ChatUserNotFoundException {
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
        ChatUser foundUser1 = chatUserDbService.findById(userId);
        ChatUser foundUser2 = chatUserDbService.findById(user2Id);

        //Then
        Assert.assertNotNull(foundUser1);
        Assert.assertNotNull(foundUser2);
    }

    @Test
    public void testChatUserFindByName() throws ChatUserNotFoundException {
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
        String name = u.getName();
        String name2  = u2.getName();
        List<ChatUser> foundUser1 = chatUserDbService.findAllByName(name);
        List<ChatUser> foundUser2 = chatUserDbService.findAllByName(name2);

        //Then
        Assert.assertNotNull(foundUser1.get(0));
        Assert.assertNotNull(foundUser2.get(0));
    }
}
