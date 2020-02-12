package com.chat.service;

import com.chat.domain.ChatUser;
import com.chat.domain.Roles;
import com.chat.exception.ChatUserNotFoundException;
import com.chat.exception.RolesNotFoundException;
import com.chat.repository.RolesRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolesDBService {
    @Autowired
    RolesRepo rolesRepo;

    @Autowired
    ChatUserDbService chatUserDbService;

    private static final Logger LOGGER = LoggerFactory.getLogger(RolesDBService.class);

    public Roles save(Roles r){
        return rolesRepo.save(r);
    }

    public Roles findById(Long id) throws RolesNotFoundException{
        return rolesRepo.findById(id).orElseThrow(RolesNotFoundException::new);
    }

    public Roles assignNewRole(Long userId,String newRole) {
       try {
           Roles r = rolesRepo.findByChatUser(chatUserDbService.findById(userId)).orElseThrow(ChatUserNotFoundException::new);
           r.setRole(newRole);
           return rolesRepo.save(r);
       }catch (ChatUserNotFoundException e){
           LOGGER.warn("User Not found. Couldn't change role");
           e.getMessage();
           return new Roles();
       }
    }
    public Roles findBYChatUser(ChatUser u ){
       try {
          return rolesRepo.findByChatUser(u).orElseThrow(RolesNotFoundException::new);
       }catch (RolesNotFoundException e){
           LOGGER.warn("Role not found");
           return  new Roles();
       }
    }
    public void delete(Roles r){
        rolesRepo.delete(r);
    }

    public Roles create(ChatUser user){
        Roles r = new Roles();
        r.setChatUser(user);
        if (user.getId() == 2) {
            r.setRole("ADMIN");
            LOGGER.info("role \"ADMIN\" has been assigned");
        } else {
            r.setRole("USER");
            LOGGER.info("role \"USER\" has been assigned");
        }
        save(r);
        return r;
    }
}
