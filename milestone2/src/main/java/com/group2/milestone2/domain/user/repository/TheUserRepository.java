package com.group2.milestone2.domain.user.repository;

import com.group2.milestone2.domain.user.domain.TheUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheUserRepository extends JpaRepository<TheUser, String> {

}
