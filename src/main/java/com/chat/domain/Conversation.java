package com.chat.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "COVERSATIONS")
public class Conversation {
    @Id
    @GeneratedValue
    @Column(name = "CONVERSATION_ID")
    private Long id;
    @Column(name = "PARTICIPANTS")
    private String participants;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = Message.class)
    private List<Message> messages = new ArrayList<>();
}
