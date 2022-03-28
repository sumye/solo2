package com.sparta.solo2.service;

import com.sparta.solo2.dto.CommentsRequestDto;
import com.sparta.solo2.model.Comments;
import com.sparta.solo2.model.Contents;
import com.sparta.solo2.repository.CommentsRepository;
import com.sparta.solo2.repository.ContentsRepository;
import com.sparta.solo2.dto.CommentsRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class CommentsService {

    private final CommentsRepository CommentsRepository;

    @Transactional
    public Long update(Long id, CommentsRequestDto requestDto) {
        Comments Comments = CommentsRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        Comments.update(requestDto);
        return Comments.getId();
    }
}