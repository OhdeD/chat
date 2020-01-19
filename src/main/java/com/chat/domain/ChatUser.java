package com.chat.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatUser {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID", unique = true)
    private Long id;

    @Column
    @NotNull
    private String name;

    @Column
    @NotNull
    private String surname;

    @Column
    @NotNull
    private String mail;

    @Column
    @NotNull
    private String password;

    @Column
    @NotNull
    private String city;

    @Column
    private boolean logged;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "FRIENDS_LIST_ID")
    private FriendsList friendsList;

    public ChatUser(String name, String surname, String mail, String password, String city, boolean logged) {
        this.name = name;
        this.surname = surname;
        this.mail = mail;
        this.password = password;
        this.city = city;
        this.logged = logged;
    }

    public ChatUser(Long id, String name, String surname, String mail, String password, String city, boolean logged) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.mail = mail;
        this.password = password;
        this.city = city;
        this.logged = logged;
    }
}
