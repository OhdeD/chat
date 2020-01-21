package com.chat.service;

import com.chat.domain.ChatUser;
import com.chat.domain.FriendsList;
import com.chat.exception.ChatUserNotFoundException;
import com.chat.exception.FriendsListNotFoundException;
import com.chat.repository.FriendsListRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendsListDbService {

    @Autowired
    FriendsListRepo friendsListRepo;
    @Autowired
    ChatUserDbService chatUserDbService;

    public FriendsList saveFriendsList(final FriendsList friendsList) {
        return friendsListRepo.save(friendsList);
    }

    public List<ChatUser> addFriendToFriendsList(Long id, Long id2) throws ChatUserNotFoundException {
        ChatUser u2 = chatUserDbService.findById(id2);
        FriendsList f = chatUserDbService.findById(id).getFriendsList();
        f.getFriends().add(u2);
        friendsListRepo.save(f);
        return f.getFriends();
    }

    public FriendsList getFriendsListById(Long id) throws FriendsListNotFoundException {
        return friendsListRepo.findById(id).orElseThrow(FriendsListNotFoundException::new);
    }

    public void deleteFriendsListById(Long id) {
        friendsListRepo.deleteById(id);
    }

}
