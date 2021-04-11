package com.sungmin.practice1.springboot.domain.posts;
import com.sungmin.practice1.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter //클래스내 Getter메소드 자동생성
@NoArgsConstructor //롬복 어노테이션 기본 생성자 자동추가
@Entity //JPA의 어노테이션 테이블과 링크될 클래스임을 나타냄
public class Posts extends BaseTimeEntity {
    @Id //해당 테이블의 PK필드
    @GeneratedValue(strategy = GenerationType.IDENTITY) //PK의 생성규칙
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
