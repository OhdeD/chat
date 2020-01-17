package com.chat.service;

import com.chat.domain.ChatUser;
import com.chat.domain.FriendsList;
import com.chat.exception.FriendsListNotFoundException;
import com.chat.repository.ChatUserRepo;
import com.chat.repository.FriendsListRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class DbService {

    @Autowired
    ChatUserRepo chatUserRepo;

    @Autowired
    FriendsListRepo  friendsListRepo;

    public ChatUser saveChatUser(final ChatUser chatUser){
        return chatUserRepo.save(chatUser);
    }

    public void deleteChatUserById(Long id){
        chatUserRepo.deleteById(id);
    }

    public FriendsList saveFriendsList(final FriendsList friendsList){
        return friendsListRepo.save(friendsList);
    }

    public Optional<FriendsList> getFriendsListById(Long id) {
        return friendsListRepo.findById(id);
    }

    public void deleteFriendsListById(Long id){
        friendsListRepo.deleteById(id);
    }
}
