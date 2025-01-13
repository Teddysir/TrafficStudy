package programming.study.traffictestproject.service.Interface;

import programming.study.traffictestproject.dto.post.request.RequestCreatePostDto;
import programming.study.traffictestproject.dto.post.response.ResponsePostListDto;

import java.util.List;

public interface PostService {

    void createPost_1(RequestCreatePostDto dto);

    ResponsePostListDto getPost_1();
}
