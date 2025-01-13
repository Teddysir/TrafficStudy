package programming.study.traffictestproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import programming.study.traffictestproject.dto.RequestCreatePostDto;
import programming.study.traffictestproject.entity.PostEntity;
import programming.study.traffictestproject.repository.PostRepository;
import programming.study.traffictestproject.service.Interface.PostService;

@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    public void createPost_1(RequestCreatePostDto dto) {

        PostEntity post = PostEntity.builder()
                .title(dto.title())
                .content(dto.content())
                .build();

        postRepository.save(post);

    }

}
