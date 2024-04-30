package com.group2.milestone2.domain.line_tag.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter
@Entity
public class LineTag {

    @Id
    private String content;

    public void setContent(String content){
        this.content = content;
    }

    public static LineTag create(
        String content
    ){
        LineTag lineTag = new LineTag();
        lineTag.content = content;
        return lineTag;
    }
}
