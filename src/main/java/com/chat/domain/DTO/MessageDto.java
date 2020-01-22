package com.chat.domain.DTO;

import com.chat.domain.Conversation;
import lombok.*;

import javax.persistence.Column;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class MessageDto {
    private Long id;
    private Long senderId;
    private Long recieverId;
    private String message;
    private LocalDateTime sendingDate;
    private Long conversationId;
    private boolean read;
}
