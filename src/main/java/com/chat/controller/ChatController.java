package com.chat.controller;

import com.chat.domain.DTO.ChatUserDto;
import com.chat.domain.DTO.MessageDto;
import com.chat.domain.FriendsList;
import com.chat.exception.ChatUserNotFoundException;
import com.chat.mapper.ChatMapper;
import com.chat.service.ChatUserDbService;
import com.chat.service.ConvDbService;
import com.chat.service.FriendsListDbService;
import com.chat.service.MessageDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1")
@CrossOrigin("*")
public class ChatController {
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

    @GetMapping("/chat/{userId}/friends")
    public List<ChatUserDto> getFriendsList(@PathVariable("userId") Long userId) throws ChatUserNotFoundException {
        return chatMapper.mapToChatUserDtoList(chatUserDbService.findById(userId).getFriendsList().getFriends());
    }

    @PutMapping("/chat/{userId}/friends")
    public List<ChatUserDto> addFriendToFriendsList(@PathVariable("userId") Long userId, @RequestParam Long user2Id) throws ChatUserNotFoundException {
        return chatMapper.mapToChatUserDtoList(friendsListDbService.addFriendToFriendsList(userId,user2Id));
    }

    @DeleteMapping("/chat/{userId}/friends")
    public void deleteFriendFromFriendsList(@PathVariable("userId") Long userId, @RequestParam Long user2Id) throws ChatUserNotFoundException {

    }


    @GetMapping("/chat/{userId}/{userId2}")
    public List<MessageDto> getConversation(@PathVariable("userId") Long userId, @PathVariable("userId2") Long userId2) {
        return chatMapper.mapToMessageDtoList(convDbService.getListOfMessagesFromConversation(convDbService.getConversation(userId, userId2)));
    }

    @GetMapping("/chat/{userId}/search")
    public List<ChatUserDto> getUserByName(@PathVariable("userId") Long userId, @RequestParam String name) throws ChatUserNotFoundException {
    return chatMapper.mapToChatUserDtoList(chatUserDbService.findAllByName(name));
    }

    @PostMapping("/chat/{userId}/{userId2}")
    public void sendPost(@PathVariable("userId") Long userId, @PathVariable("userId2") Long userId2, @RequestParam String message) {
        messageDbService.save(userId,userId2,message);
    }

    @PostMapping("/chat/new")
    public void createNewChatUser(@RequestBody ChatUserDto chatUserDto) {
        chatUserDbService.save(chatUserDto);
    }

    @DeleteMapping("/chat/userId")
    public void deletePost(@RequestParam MessageDto messageDto) {
    }
}
