//package com.chat;
//
//import com.chat.domain.ChatUser;
//import com.chat.domain.FriendsList;
//import com.chat.repository.ChatUserRepo;
//import com.chat.repository.FriendsListRepo;
//import com.chat.service.DbService;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.ArrayList;
//
//@SpringBootTest
//@RunWith(SpringRunner.class)
//public class ChatDataBaseTestSuite {
//    @Autowired
//    DbService dbService;
//    @Autowired
//    ChatUserRepo chatUserRepo;
//    @Autowired
//    FriendsListRepo friendsListRepo;
//
//
//    @Test
//    public void testChatUserSaveToDB() {
//        //Given
//        ChatUser user = ChatUser.builder().name("Dagmara").surname("Kopaczyk").mail("dagmara@mail").password("haslo").city("poznan").build();
//        ChatUser user2 = ChatUser.builder().name("Dasas").surname("Kosask").mail("dagmara@mail").password("hhhhh").city("aaaa").build();
//
//        //When
//        dbService.saveChatUser(user);
//        dbService.saveChatUser(user2);
//
//        Long userId = user.getId();
//        Long user2Id = user2.getId();
//
//        //Then
//        Assert.assertNotNull(userId);
//        Assert.assertNotNull(user2Id);
//
//        //CleanUp
//        dbService.deleteChatUserById(userId);
//        dbService.deleteChatUserById(user2Id);
//    }
//
//    @Test
//    public void testFriendsListSaveToDb() {
//        //Given
//        ChatUser user = ChatUser.builder().name("Dagmara").surname("Kopaczyk").mail("dagmara@mail").password("haslo").city("poznan").build();
//        ChatUser user2 = ChatUser.builder().name("Dasas").surname("Kosask").mail("dagmara@mail").password("hhhhh").city("aaaa").build();
//        FriendsList friendsList = FriendsList.builder().friends(new ArrayList<>()).build();
//        friendsList.getFriends().add(user2);
//
//        //When
//        dbService.saveChatUser(user);
//        dbService.saveChatUser(user2);
//
//        dbService.saveFriendsList(friendsList);
//
//        Long userId = user.getId();
//        Long user2Id = user2.getId();
//        Long friendsListId = friendsList.getId();
//
//        int size = friendsList.getFriends().size();
//        //Then
//        Assert.assertEquals(1, size);
//
//        //CleanUp
//        dbService.deleteChatUserById(userId);
//        dbService.deleteChatUserById(user2Id);
//        dbService.deleteFriendsListById(friendsListId);
//    }
//
//}
