package programming.study.traffictestproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import programming.study.traffictestproject.dto.post.request.RequestCreatePostDto;
import programming.study.traffictestproject.dto.post.response.ResponsePostDto;
import programming.study.traffictestproject.dto.post.response.ResponsePostListDto;
import programming.study.traffictestproject.service.Interface.PostService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    @PostMapping("/create/v1")
    public ResponseEntity<String> createPost_1(@RequestBody RequestCreatePostDto dto) {
        postService.createPost_1(dto);
        return ResponseEntity.ok("jpa.save & Record Dto 방식으로 게시물이 생성되었습니다. traffic 속도를 확인해주세요.");
    }

    @GetMapping("/get/api/v1")
    public ResponsePostListDto getPost_1() {
        ResponseEntity.ok("v1 방식으로 게시물이 호출되었습니다. traffic 속도를 확인해주세요.");
        return postService.getPost_1();
    }

}
