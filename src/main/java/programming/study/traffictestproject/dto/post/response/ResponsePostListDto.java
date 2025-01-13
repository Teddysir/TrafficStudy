package programming.study.traffictestproject.dto.post.response;

import lombok.Builder;

import java.util.List;

@Builder
public record ResponsePostListDto(List<ResponsePostDto> posts) {
}
