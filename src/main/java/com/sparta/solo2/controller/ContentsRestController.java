package com.sparta.solo2.controller;


import com.sparta.solo2.model.Contents;
import com.sparta.solo2.repository.ContentsRepository;
import com.sparta.solo2.dto.ContentsRequestDto;
import com.sparta.solo2.security.UserDetailsImpl;
import com.sparta.solo2.service.ContentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ContentsRestController {

    private final ContentsRepository ContentsRepository;
    private final ContentsService ContentsService;

    // 게시글 전체 조회
    @GetMapping("/api/contents")
    public List<Contents> getContents() {
        return ContentsRepository.findAllByOrderByModifiedAtDesc();
    }

    // 게시글 특정 조회
    @GetMapping("/api/contents/{id}")
    public Contents getContents(@PathVariable Long id) {
        Contents contents = ContentsRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("contentsId가 존재하지 않습니다."));
        return contents;
    }

    // 게시글 생성
    @PostMapping("/api/contents")
    public Contents createContents(@RequestBody ContentsRequestDto requestDto , @AuthenticationPrincipal UserDetailsImpl userDetails) {
        requestDto.setName(userDetails.getUsername()); ///로그인한 유저로 바꾸기위해서 추가

        Contents Contents = new Contents(requestDto);

        return ContentsRepository.save(Contents);
    }

    // 게시글 수정
    @PutMapping("/api/contents/{id}")
    public Long updateContents(@PathVariable Long id, @RequestBody ContentsRequestDto requestDto) {
        ContentsService.update(id, requestDto);
        return id;
    }

    @DeleteMapping("/api/contents/{id}")
    public Long deleteContents(@PathVariable Long id) {
        ContentsRepository.deleteById(id);
        return id;
    }

}