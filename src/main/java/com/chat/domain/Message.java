package com.chat.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Message   {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Long senderId;

    @Column
    private Long receiverId;

    @Column(length = 1800)
    private String message;

    @Column
    private LocalDateTime sendingDate;

    @Column
    private Long conversationId;

    @Column
    private boolean read;

    public Message(Long senderId, Long receiverId, String message, LocalDateTime sendingDate, Long conversationId) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.message = message;
        this.sendingDate = sendingDate;
        this.conversationId = conversationId;
    }

}
