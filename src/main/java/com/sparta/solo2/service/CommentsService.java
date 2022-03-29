package com.sparta.solo2.service;

import com.sparta.solo2.dto.CommentsRequestDto;
import com.sparta.solo2.dto.ContentsRequestDto;
import com.sparta.solo2.model.Comments;
import com.sparta.solo2.model.Contents;
import com.sparta.solo2.repository.CommentsRepository;
import com.sparta.solo2.repository.ContentsRepository;
import com.sparta.solo2.dto.CommentsRequestDto;
import com.sparta.solo2.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class CommentsService {

    private final CommentsRepository CommentsRepository;




        //댓글 수정
        @Transactional
        public Long update(Long id, CommentsRequestDto requestDto) {
            Comments comments = CommentsRepository.findById(id).orElseThrow(
                    () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
            );
            comments.update(requestDto);
            return comments.getCommentsId();
        }
}