package com.chat.service;

import com.chat.domain.ChatUser;
import com.chat.domain.DTO.ChatUserDto;
import com.chat.domain.FriendsList;
import com.chat.exception.ChatUserNotFoundException;
import com.chat.mapper.ChatMapper;
import com.chat.repository.ChatUserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatUserDbService {
    @Autowired
    ChatUserRepo chatUserRepo;
    @Autowired
    FriendsListDbService friendsListDbService;
    @Autowired
    ChatMapper chatMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger (ChatUserDbService.class);

    public ChatUser save(ChatUserDto chatUserDto) {
        LOGGER.info("Creating empty FriendsList");
        FriendsList friendsList = new FriendsList();
        friendsListDbService.saveFriendsList(friendsList);
        ChatUser user = chatMapper.mapToNEWChatUser(chatUserDto);
        user.setFriendsList(friendsList);
        return chatUserRepo.save(user);
    }

    public void deleteById(Long id) {
        chatUserRepo.deleteById(id);
    }

    public ChatUser findById(Long id) throws ChatUserNotFoundException {
        return chatUserRepo.findById(id).orElseThrow(ChatUserNotFoundException::new);
    }

    public List<ChatUser> findAllByName(String name) throws ChatUserNotFoundException {
        return chatUserRepo.findAllByName(name).orElseThrow(ChatUserNotFoundException::new);
    }
}
