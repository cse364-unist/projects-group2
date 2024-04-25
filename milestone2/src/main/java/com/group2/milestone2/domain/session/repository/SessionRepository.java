package com.group2.milestone2.domain.session.repository;

import com.group2.milestone2.domain.session.domain.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping
public interface SessionRepository extends JpaRepository<Session, String> {

}
