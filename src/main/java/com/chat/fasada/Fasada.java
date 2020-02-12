package com.chat.fasada;

import com.chat.domain.DTO.ChatUserDto;
import com.chat.domain.DTO.MessageDto;
import com.chat.domain.DTO.RolesDto;
import com.chat.exception.ChatUserNotFoundException;
import com.chat.mapper.ChatMapper;
import com.chat.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    @Autowired
    RolesDBService rolesDBService;

    public ChatUserDto saveUser(ChatUserDto chatUserDto) {
        return chatMapper.mapToChatUserDto(chatUserDbService.save(chatUserDto));
    }

    public void updateUser(Long userId, ChatUserDto chatUserDto) {
    chatUserDbService.updateUser(userId, chatUserDto);
    }

    public List<ChatUserDto> getFriendsList(Long userId) throws ChatUserNotFoundException {
        return chatMapper.mapToChatUserDtoList(chatUserDbService.findById(userId).getFriendsList().getFriends());
    }

    public List<ChatUserDto> addFriendToFriendsList(Long userId, Long user2Id) throws ChatUserNotFoundException {
        return chatMapper.mapToChatUserDtoList(friendsListDbService.addFriendToFriendsList(userId, user2Id));
    }

    public void deleteFriendFromFriendsList(Long userId, Long user2Id) throws ChatUserNotFoundException {
       friendsListDbService.deleteFriendsFromFriendslist(userId, user2Id);
    }

    public List<MessageDto> getConversation(Long userId, Long userId2) {
        return chatMapper.mapToMessageDtoList(convDbService.getListOfMessagesFromConversation(convDbService.getConversation(userId, userId2)));
    }

    public List<ChatUserDto> getUserByName(Long userId, String name) throws ChatUserNotFoundException {
        return chatMapper.mapToChatUserDtoList(chatUserDbService.findAllByNameOrSurname(name));
    }

    public String sendPost(Long userId, Long userId2, String message) throws ChatUserNotFoundException {
        return messageDbService.save(userId, userId2, message).getMessage();
    }

    public void deletePost(Long userId, Long messgeId) {
        messageDbService.deleteById(messgeId);
    }

    public List<ChatUserDto> getAllUsers() {
        return chatMapper.mapToChatUserDtoList(chatUserDbService.getAllUsers());
    }

    public void deleteUser(Long userId) {
        chatUserDbService.delete(userId);
    }

    public RolesDto setRole(Long userId, String role) {
        return chatMapper.mapToRolesDto(rolesDBService.assignNewRole(userId, role));
    }

    public RolesDto getRole(Long userId) {
        return chatMapper.mapToRolesDto(rolesDBService.findBYChatUser(chatUserDbService.findById(userId)));
    }

    public ChatUserDto login(String mail, String password) {
        return chatMapper.mapToChatUserDto(chatUserDbService.login(mail, password));
    }

    public void logout(ChatUserDto chatUserDto) {
        chatUserDbService.logout(chatUserDto);

    }

    public ChatUserDto getUserById(Long userId) {
        return chatMapper.mapToChatUserDto(chatUserDbService.findById(userId));
    }
}
