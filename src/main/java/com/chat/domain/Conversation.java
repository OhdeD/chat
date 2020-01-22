package com.chat.domain;

import com.chat.exception.ChatUserNotFoundException;
import com.chat.observer.Observable;
import com.chat.observer.Observer;
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

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Message> messages = new ArrayList<>();


}
