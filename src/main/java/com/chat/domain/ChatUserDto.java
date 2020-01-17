package com.chat.domain;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ChatUserDto {
    private  Long id;
    private String name;
    private String surname;
    private String mail;
    private String password;
    private String city;
    private boolean logged = false;

}
