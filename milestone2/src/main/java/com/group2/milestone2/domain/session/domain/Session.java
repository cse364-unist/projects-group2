package com.group2.milestone2.domain.session.domain;

import com.group2.milestone2.domain.user.domain.TheUser;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;

    @OneToOne
    private TheUser user;

    public static Session create(String value, TheUser user){
        Session session = new Session();
        session.content = value;
        session.user = user;
        return session;
    }

}
