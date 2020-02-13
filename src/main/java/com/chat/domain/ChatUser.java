package com.chat.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatUser  {
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
    @Column(length = 100)
    @NotNull
    private String password;
    @Column
    @NotNull
    private String city;
    @Column
    private boolean logged;
    private Long friendsListId;

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
