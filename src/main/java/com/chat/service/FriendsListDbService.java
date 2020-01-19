package com.chat.service;

import com.chat.domain.FriendsList;
import com.chat.exception.FriendsListNotFoundException;
import com.chat.repository.FriendsListRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FriendsListDbService {

    @Autowired
    FriendsListRepo friendsListRepo;

    public FriendsList saveFriendsList(final FriendsList friendsList){
        return friendsListRepo.save(friendsList);
    }

    public FriendsList getFriendsListById(Long id) throws FriendsListNotFoundException {
        return friendsListRepo.findById(id).orElseThrow(FriendsListNotFoundException::new);
    }

    public void deleteFriendsListById(Long id){
        friendsListRepo.deleteById(id);
    }

}
