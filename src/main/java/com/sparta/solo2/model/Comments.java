package com.sparta.solo2.model;

import com.sparta.solo2.dto.CommentsRequestDto;
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
    private Long commentsId;

    // nullable = false 반드시 값을 가지도록 합니다.


    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String comments;

    @Column(nullable = false)
    private Long contentsId;


    // 게시글 생성
    public Comments(CommentsRequestDto requestDto) {

        this.username = requestDto.getUsername();
        this.comments = requestDto.getComments();
        this.contentsId = requestDto.getContentsId();
    }

    public Comments(String username , String  comments, Long contentsId) {

        this.username = username;
        this.comments = comments;
        this.contentsId = contentsId;
    }

    // 게시글 수정
    public void update(CommentsRequestDto requestDto) {

//        this.username = requestDto.getUsername();
        this.comments = requestDto.getComments();
//        this.contentsId = requestDto.getContentsId();

    }
}