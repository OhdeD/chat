package com.chat.domain.DTO;

import com.chat.domain.ChatUser;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RolesDto {
    private Long id;
    private String role;
    private ChatUser chatUser;
}
