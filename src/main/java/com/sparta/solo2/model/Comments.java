package com.sparta.solo2.model;

import com.sparta.solo2.dto.CommentsRequestDto;
import com.sparta.solo2.dto.ContentsRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity // DB 테이블 역할을 합니다.
public class Comments extends Timestamped {

    // ID가 자동으로 생성 및 증가합니다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    // nullable = false 반드시 값을 가지도록 합니다.


    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String comments;

    public Comments( String name, String contents) {

        this.name = name;
        this.comments = comments;
    }

    // 게시글 생성
    public Comments(CommentsRequestDto requestDto ) {

        this.name = requestDto.getName();
        this.comments = requestDto.getComments();
    }

    // 게시글 수정
    public void update(CommentsRequestDto requestDto) {

        this.name = requestDto.getName();
        this.comments = requestDto.getComments();
    }
}