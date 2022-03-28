package com.sparta.solo2.controller;

import com.sparta.solo2.dto.CommentsRequestDto;
import com.sparta.solo2.model.Comments;
import com.sparta.solo2.model.Comments;
import com.sparta.solo2.repository.CommentsRepository;
import com.sparta.solo2.repository.CommentsRepository;
import com.sparta.solo2.dto.CommentsRequestDto;
import com.sparta.solo2.security.UserDetailsImpl;
import com.sparta.solo2.service.CommentsService;
import com.sparta.solo2.service.CommentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CommentsRestController {

    private final CommentsRepository CommentsRepository;
    private final CommentsService CommentsService;

    // 게시글 전체 조회
    @GetMapping("/api/comments")
    public List<Comments> getComments() {
        return CommentsRepository.findAllByOrderByModifiedAtDesc();
    }

    // 게시글 특정 조회


    // 게시글 생성
    @PostMapping("/api/comments")
    public Comments createComments(@RequestBody CommentsRequestDto requestDto , @AuthenticationPrincipal UserDetailsImpl userDetails) {
        requestDto.setName(userDetails.getUsername()); ///로그인한 유저로 바꾸기위해서 추가

        Comments Comments = new Comments(requestDto);

        return CommentsRepository.save(Comments);
    }

    // 게시글 수정
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