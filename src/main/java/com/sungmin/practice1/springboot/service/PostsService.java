package com.sungmin.practice1.springboot.service;

import com.sungmin.practice1.springboot.domain.posts.Posts;
import com.sungmin.practice1.springboot.domain.posts.PostsRepository;
import com.sungmin.practice1.springboot.web.dto.PostsResponseDto;
import com.sungmin.practice1.springboot.web.dto.PostsSaveRequestDto;
import com.sungmin.practice1.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }
    @Transactional
    public PostsResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No user. id="+id));
        return new PostsResponseDto(entity);

    }

}