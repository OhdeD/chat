package com.chat.service;

import com.chat.domain.ChatUser;
import com.chat.domain.FriendsList;
import com.chat.exception.ChatUserNotFoundException;
import com.chat.exception.FriendsListNotFoundException;
import com.chat.repository.FriendsListRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FriendsListDbService {

    @Autowired
    FriendsListRepo friendsListRepo;
    @Autowired
    ChatUserDbService chatUserDbService;
    private static final Logger LOGGER = LoggerFactory.getLogger (FriendsListDbService.class);

    public FriendsList saveFriendsList(final FriendsList friendsList) {
        return friendsListRepo.save(friendsList);
    }

    public List<ChatUser> addFriendToFriendsList(Long id, Long id2) throws ChatUserNotFoundException {
        ChatUser u2 = chatUserDbService.findById(id2);
        FriendsList f = friendsListRepo.findById(chatUserDbService.findById(id).getFriendsListId()).get();
        f.getFriends().add(u2);
        friendsListRepo.save(f);
        LOGGER.info("Friend added to " + chatUserDbService.findById(id).getName() + "'s friends list");
        return f.getFriends();
    }

    public FriendsList getFriendsListById(Long id) throws FriendsListNotFoundException {
        return friendsListRepo.findById(id).orElseThrow(FriendsListNotFoundException::new);
    }

    public void deleteFriendsListById(Long id) {
        friendsListRepo.deleteById(id);
    }

    public void deleteFriendsFromFriendslist(Long userId, Long user2Id) throws ChatUserNotFoundException {
        FriendsList a = friendsListRepo.findById(chatUserDbService.findById(userId).getFriendsListId()).get();
        List<ChatUser> friendToDeleting = friendsListRepo.findById(chatUserDbService.findById(userId).getFriendsListId()).get().getFriends().stream()
                .filter(e -> e.getId().equals(user2Id))
                .collect(Collectors.toList());
        if (friendToDeleting.size() > 0) {
            a.getFriends().removeAll(friendToDeleting);
            friendsListRepo.save(a);
            LOGGER.info("Friend: " + friendToDeleting.get(0).getName() + " deleted from friends list.");
        }
        LOGGER.warn("\"Delete\" button was used. No friend deleted");
    }
}
