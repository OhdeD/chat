package com.chat.fasada;

import com.chat.domain.DTO.ChatUserDto;
import com.chat.domain.DTO.MessageDto;
import com.chat.exception.ChatUserNotFoundException;
import com.chat.mapper.ChatMapper;
import com.chat.service.ChatUserDbService;
import com.chat.service.ConvDbService;
import com.chat.service.FriendsListDbService;
import com.chat.service.MessageDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class Fasada {
    @Autowired
    ChatMapper chatMapper;
    @Autowired
    ChatUserDbService chatUserDbService;
    @Autowired
    FriendsListDbService friendsListDbService;
    @Autowired
    ConvDbService convDbService;
    @Autowired
    MessageDbService messageDbService;

    public void saveUser(ChatUserDto chatUserDto) {
        chatUserDbService.save(chatUserDto);
    }
    public List<ChatUserDto> getFriendsList(Long userId) throws ChatUserNotFoundException {
        return chatMapper.mapToChatUserDtoList(chatUserDbService.findById(userId).getFriendsList().getFriends());
    }
    public List<ChatUserDto> addFriendToFriendsList(Long userId,Long user2Id) throws ChatUserNotFoundException {
        return chatMapper.mapToChatUserDtoList(friendsListDbService.addFriendToFriendsList(userId, user2Id));
    }
    public void deleteFriendFromFriendsList(Long userId, Long user2Id) throws ChatUserNotFoundException {
        friendsListDbService.deleteFriendsListById(user2Id);
    }
    public List<MessageDto> getConversation(Long userId, Long userId2) {
        return chatMapper.mapToMessageDtoList(convDbService.getListOfMessagesFromConversation(convDbService.getConversation(userId, userId2)));
    }
    public List<ChatUserDto> getUserByName(Long userId,String name) throws ChatUserNotFoundException {
        return chatMapper.mapToChatUserDtoList(chatUserDbService.findAllByName(name));
    }
    public void sendPost( Long userId,Long userId2,String message) throws ChatUserNotFoundException {
        messageDbService.save(userId, userId2, message);
    }
    public void deletePost(Long userId, Long messgeId) {
        messageDbService.deleteById(messgeId);
    }
}
