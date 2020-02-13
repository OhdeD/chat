package com.chat.domain.DTO;

import lombok.*;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Component
public class ChatUserDto {
    private  Long id;
    private String name;
    private String surname;
    private String mail;
    private String password;
    private String city;
    private boolean logged;
    private Long friendsListIdDto;
}
