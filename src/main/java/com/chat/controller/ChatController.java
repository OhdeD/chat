package com.chat.controller;

import com.chat.domain.DTO.ChatUserDto;
import com.chat.domain.DTO.MessageDto;
import com.chat.exception.ChatUserNotFoundException;
import com.chat.fasada.Fasada;
import com.chat.mapper.ChatMapper;
import com.chat.service.ChatUserDbService;
import com.chat.service.ConvDbService;
import com.chat.service.FriendsListDbService;
import com.chat.service.MessageDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
@CrossOrigin("*")
public class ChatController {
    @Autowired
    Fasada fasada;

    @PostMapping("/chat/new")
    public void createNewChatUser(@RequestBody ChatUserDto chatUserDto) {
        fasada.saveUser(chatUserDto);
    }

    @GetMapping("/chat/{userId}/friends")
    public List<ChatUserDto> getFriendsList(@PathVariable("userId") Long userId) throws ChatUserNotFoundException {
        return fasada.getFriendsList(userId);
    }

    @PutMapping("/chat/{userId}/friends")
    public List<ChatUserDto> addFriendToFriendsList(@PathVariable("userId") Long userId, @RequestParam Long user2Id) throws ChatUserNotFoundException {
        return fasada.addFriendToFriendsList(userId, user2Id);
    }

    @DeleteMapping("/chat/{userId}/friends")
    public void deleteFriendFromFriendsList(@PathVariable("userId") Long userId, @RequestParam Long user2Id) throws ChatUserNotFoundException {
        fasada.deleteFriendFromFriendsList(userId, user2Id);
    }

    @GetMapping("/chat/{userId}/{userId2}")
    public List<MessageDto> getConversation(@PathVariable("userId") Long userId, @PathVariable("userId2") Long userId2) {
        return fasada.getConversation(userId, userId2);
    }

    @GetMapping("/chat/{userId}/search")
    public List<ChatUserDto> getUserByName(@PathVariable("userId") Long userId, @RequestParam String name) throws ChatUserNotFoundException {
        return fasada.getUserByName(userId, name);
    }

    @PostMapping("/chat/{userId}/{userId2}")
    public void sendPost(@PathVariable("userId") Long userId, @PathVariable("userId2") Long userId2, @RequestParam String message) throws ChatUserNotFoundException {
        fasada.sendPost(userId, userId2, message);
    }

    @DeleteMapping("/chat/{userId}")
    public void deletePost(@PathVariable("userId") Long userId, @RequestParam Long messgeId) {
        fasada.deletePost(userId, messgeId);
    }
}
