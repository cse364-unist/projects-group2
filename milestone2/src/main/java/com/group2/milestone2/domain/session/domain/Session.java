package com.group2.milestone2.domain.session.domain;

import com.group2.milestone2.domain.user.domain.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import org.aspectj.apache.bcel.classfile.Module.Uses;

@Entity
public class Session {

    @Id
    private String value;

    @OneToOne
    private User user;

    public static Session create(String value, User user){
        Session session = new Session();
        session.value = value;
        session.user = user;
        return session;
    }

}
