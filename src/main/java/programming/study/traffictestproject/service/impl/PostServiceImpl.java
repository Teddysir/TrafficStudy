package programming.study.traffictestproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import programming.study.traffictestproject.dto.post.request.RequestCreatePostDto;
import programming.study.traffictestproject.dto.post.response.ResponsePostDto;
import programming.study.traffictestproject.dto.post.response.ResponsePostListDto;
import programming.study.traffictestproject.entity.PostEntity;
import programming.study.traffictestproject.repository.PostRepository;
import programming.study.traffictestproject.service.Interface.PostService;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    public void createPost_1(RequestCreatePostDto dto) {

        PostEntity post = PostEntity.builder() // 함수형
                .title(dto.title())
                .content(dto.content())
                .build();

        postRepository.save(post); // -> repository intellij < - > database 다리?

//        PostEntity newPost = new PostEntity(3, "test Title", "testContent");

    }

    @Override
    public ResponsePostListDto getPost_1() {

        List<PostEntity> postEntities = postRepository.findAll();

        List<ResponsePostDto> postDtoList = postEntities.stream()
                .map(post -> ResponsePostDto.builder()
                        .id(post.getId())
                        .title(post.getTitle())
                        .content(post.getContent())
                        .build())
                .toList();

        return ResponsePostListDto.builder()
                .posts(postDtoList)
                .build();

    }

}
