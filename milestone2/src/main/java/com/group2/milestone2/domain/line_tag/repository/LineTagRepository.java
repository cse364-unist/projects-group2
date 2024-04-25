package com.group2.milestone2.domain.line_tag.repository;

import com.group2.milestone2.domain.line_tag.domain.LineTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LineTagRepository extends JpaRepository<LineTag, String> {

}
