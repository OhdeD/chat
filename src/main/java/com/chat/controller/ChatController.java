package com.chat.controller;

import com.chat.domain.DTO.ChatUserDto;
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
    public List<ChatUserDto> getFriendsList(@PathVariable("userId") Long userId) {
        return new ArrayList<>();
    }

    @GetMapping("/chat/{userId}/{userId2}")
    public List<String> getConversation(@PathVariable("userId") Long userId, @PathVariable("userId2") Long userId2) {
        return new ArrayList<>();
    }

    @GetMapping("/chat/userId/search")
    public List<ChatUserDto> getUserByName(@RequestParam String name) {
        return new ArrayList<>();
    }

    @PostMapping("/chat/{userId}/{userId2}")
    public List<String> sendPost(@PathVariable("userId") Long userId, @PathVariable("userId2") Long userId2) {
        return new ArrayList<>();
    }

    @PostMapping("/chat/new")
    public void createNewChatUser(@RequestBody ChatUserDto chatUserDto) {
        chatUserDbService.save(chatMapper.mapToNEWChatUser(chatUserDto));
    }

    @DeleteMapping("/chat/")
    public void deletePost(@RequestParam Long postId) {
    }
}
