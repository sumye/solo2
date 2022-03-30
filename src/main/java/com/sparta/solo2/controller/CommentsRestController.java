package com.sparta.solo2.controller;

import com.sparta.solo2.dto.CommentsRequestDto;
import com.sparta.solo2.dto.ContentsRequestDto;
import com.sparta.solo2.model.Comments;
import com.sparta.solo2.model.Comments;
import com.sparta.solo2.model.Contents;
import com.sparta.solo2.repository.CommentsRepository;
import com.sparta.solo2.repository.CommentsRepository;
import com.sparta.solo2.dto.CommentsRequestDto;
import com.sparta.solo2.repository.ContentsRepository;
import com.sparta.solo2.security.UserDetailsImpl;
import com.sparta.solo2.service.CommentsService;
import com.sparta.solo2.service.CommentsService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Getter
@RequiredArgsConstructor
@RestController
public class CommentsRestController {

    private final CommentsRepository CommentsRepository;
    private final CommentsService CommentsService;

    //     댓글 전체 조회
    @GetMapping("/api/comments/{contentsId}")
    public List<Comments> getComments(@PathVariable Long contentsId ) {

        return CommentsRepository.findAllByContentsIdOrderByCreatedAtDesc(contentsId);
    }



    // 댓글 특정 조회
//    @GetMapping("/api/comments/{id}")
//    public Comments getComments(@PathVariable Long id) {
//        Comments comments = CommentsRepository.findById(id).orElseThrow(
//                () -> new IllegalArgumentException("contentsId가 존재하지 않습니다."));
//        return comments;
//    }

    // 댓글 생성
    @PostMapping("/api/comments")
    public Comments createComments(@RequestBody CommentsRequestDto requestDto , @AuthenticationPrincipal UserDetailsImpl userDetails) {


        Comments Comments = new Comments(requestDto , userDetails.getUser().getUsername());


        return CommentsRepository.save(Comments);
    }

    // 댓글 수정
    @PutMapping("/api/comments/{id}")
    public Long updateComments(@PathVariable Long id, @RequestBody CommentsRequestDto requestDto) {
        CommentsService.update(id, requestDto);
        return id;
    }

    @DeleteMapping("/api/comments/{id}")
    public Long deleteComments(@PathVariable Long id) {
        CommentsRepository.deleteById(id);
        return id;
    }

}