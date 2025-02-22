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
        System.out.println("얍얍얍!");
        return postService.getPost_1();
    }

    // TODO : 처리량 / 응답시간을 개선해야한다.
    // TODO : 기존에 많이 생각 안하고 썻던 N+1 현상을 개선하기 - 1
    // TODO : 핫 게시물, RT 사용시 캐싱 처리를 통한 성능 개선 - 2
    // TODO : Put / Delete 요청 한개씩 할경우 몇개부터 Put 요청이 유의미 할까? - 3

}
