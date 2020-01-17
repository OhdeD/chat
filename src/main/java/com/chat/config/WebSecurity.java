//package com.chat.config;
//
//        import com.chat.domain.ChatUser;
//        import org.springframework.security.core.Authentication;
//
//public class WebSecurity {
//
//    public boolean checkUserId(Authentication authentication, long id) {
//        ChatUser user = (ChatUser) authentication.getPrincipal();
//        Long userId = user.getId();
//        if (userId.equals(id)) {
//            return true;
//        } else return false;
//    }
//}
