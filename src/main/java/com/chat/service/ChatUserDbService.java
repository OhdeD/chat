package com.chat.service;

import com.chat.domain.ChatUser;
import com.chat.domain.DTO.ChatUserDto;
import com.chat.domain.FriendsList;
import com.chat.domain.Roles;
import com.chat.exception.ChatUserNotFoundException;
import com.chat.mapper.ChatMapper;
import com.chat.repository.ChatUserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Optional.ofNullable;

@Service
public class ChatUserDbService {
    @Autowired
    ChatUserRepo chatUserRepo;
    @Autowired
    FriendsListDbService friendsListDbService;
    @Autowired
    ChatMapper chatMapper;
    @Autowired
    RolesDBService rolesDBService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ChatUserDbService.class);

    public ChatUser save(ChatUserDto chatUserDto) {
        LOGGER.info("Creating empty FriendsList and assigning it to " + chatUserDto.getName());
        FriendsList friendsList = new FriendsList();
        friendsListDbService.saveFriendsList(friendsList);
        ChatUser user = chatMapper.mapToNEWChatUser(chatUserDto);
        user.setFriendsList(friendsList);
        ChatUser createdUser = chatUserRepo.save(user);
        LOGGER.info("User added to DB");
        rolesDBService.create(createdUser);
        return createdUser;
    }


    public ChatUser findById(Long id) throws ChatUserNotFoundException {
        return chatUserRepo.findById(id).orElseThrow(ChatUserNotFoundException::new);
    }

    public List<ChatUser> findAllByName(String name) throws ChatUserNotFoundException {
        return chatUserRepo.findAllByName(name).orElseThrow(ChatUserNotFoundException::new);
    }

    public List<ChatUser> getAllUsers() {
        return chatUserRepo.getAllUsers();
    }

    public void delete(Long userId) {
        try {
            ChatUser u = findById(userId);
            Long id = u.getFriendsList().getId();
            Roles r = rolesDBService.findBYChatUser(u);
            rolesDBService.delete(r);
            chatUserRepo.deleteById(userId);
            LOGGER.info("User deleted.");
            friendsListDbService.deleteFriendsListById(id);
            LOGGER.info("User's friends list deleted.");
        } catch (ChatUserNotFoundException e) {
            LOGGER.warn("There's no such user");
        }
    }

    public String findCity(Long userId) {
        ChatUser u = chatUserRepo.findById(userId).get();
        if (u.getCity() != null) {
            return u.getCity();
        } else return "Warszawa";
    }

    public String getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        } else {
            return principal.toString();
        }

    }

    public ChatUser findByMail(String mail) {
        return ofNullable(chatUserRepo.findByMail(mail)).orElse(new ChatUser());
    }
}
