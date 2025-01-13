package programming.study.traffictestproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import programming.study.traffictestproject.dto.RequestCreatePostDto;
import programming.study.traffictestproject.service.Interface.PostService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    @PostMapping("/create/v1")
    public ResponseEntity<String> createPost_1(@RequestBody RequestCreatePostDto dto) {
        postService.createPost_1(dto);
        return ResponseEntity.ok("v1 방식으로 게시물이 생성되었습니다. traffic 속도를 확인해주세요.");
    }

    @GetMapping("/api/v1")
    public ResponseEntity<String> getPost_1() {

        return ResponseEntity.ok("v1 방식으로 게시물이 호출되었습니다. traffic 속도를 확인해주세요.");
    }

}
