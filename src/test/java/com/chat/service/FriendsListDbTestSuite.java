package com.chat.service;

import com.chat.domain.ChatUser;
import com.chat.domain.FriendsList;
import com.chat.exception.FriendsListNotFoundException;
import com.chat.repository.ChatUserRepo;
import com.chat.repository.FriendsListRepo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
    public void testFriendsListSaveToDb() {
        //Given
        ChatUser user = new ChatUser("Dagmara", "Kopaczyk", "dagmara@mail", "haslo", "poznan", false);
        ChatUser user2 = new ChatUser("Dadsadd", "Kopasdasda", "dagsdara@mail", "haslo", "ddddznan", false);
        FriendsList friendsList = new FriendsList();
        friendsList.getFriends().add(user2);

        //When
        chatUserDbService.save(user2);
        chatUserDbService.save(user);
        friendsListDbService.saveFriendsList(friendsList);

        Long userId = user.getId();
        Long user2Id = user2.getId();
        Long friendsListId = friendsList.getId();

        int size = friendsList.getFriends().size();

        //Then
        Assert.assertEquals(1, size);

        //CleanUp
        friendsListDbService.deleteFriendsListById(friendsList.getId());
        chatUserDbService.deleteById(user.getId());
        chatUserDbService.deleteById(user2.getId());
    }

    @Test
    public void testFriendsListDelete() throws FriendsListNotFoundException {
        //Given
        ChatUser user = new ChatUser("Dagmara", "Kopaczyk", "dagmara@mail", "haslo", "poznan", false);
        ChatUser user2 = new ChatUser("Dadsadd", "Kopasdasda", "dagsdara@mail", "haslo", "ddddznan", false);
        FriendsList friendsList = new FriendsList();
        friendsList.getFriends().add(user2);

        //When
        chatUserDbService.save(user);
        chatUserDbService.save(user2);

        friendsListDbService.saveFriendsList(friendsList);

        Long userId = user.getId();
        Long user2Id = user2.getId();
        Long friendsListId = friendsList.getId();

        int size = friendsList.getFriends().size();

        friendsListDbService.deleteFriendsListById(friendsListId);
        FriendsList deletedFriendsList;
        try {
            deletedFriendsList = friendsListDbService.getFriendsListById(friendsListId);
        } catch (FriendsListNotFoundException e) {
            deletedFriendsList = null;
        }

        //Then
        Assert.assertNotEquals(friendsList, deletedFriendsList);

        //Clean-up
        chatUserDbService.deleteById(userId);
        chatUserDbService.deleteById(user2Id);
    }

    @Test
    public void testFriendsListFindById() throws FriendsListNotFoundException {
        //Given
        ChatUser user = new ChatUser("Dagmara", "Kopaczyk", "dagmara@mail", "haslo", "poznan", false);
        ChatUser user2 = new ChatUser("Dadsadd", "Kopasdasda", "dagsdara@mail", "haslo", "ddddznan", false);
        FriendsList friendsList = new FriendsList();
        friendsList.getFriends().add(user2);

        //When
        chatUserDbService.save(user);
        chatUserDbService.save(user2);

        friendsListDbService.saveFriendsList(friendsList);

        Long userId = user.getId();
        Long user2Id = user2.getId();
        Long friendsListId = friendsList.getId();

        int size = friendsList.getFriends().size();

        FriendsList foundedList = friendsListDbService.getFriendsListById(friendsListId);

        //Then
        Assert.assertNotNull(foundedList);

        //Clean-up
        friendsListDbService.deleteFriendsListById(friendsListId);
        chatUserDbService.deleteById(userId);
        chatUserDbService.deleteById(user2Id);
    }


}
