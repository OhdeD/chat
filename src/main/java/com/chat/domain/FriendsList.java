package com.chat.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "FRIENDS_LIST")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FriendsList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "FRIENDSLIST_ID")
    private long id;

    @OneToMany
    private List<ChatUser> friends = new ArrayList<>();
}
