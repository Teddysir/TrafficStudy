package programming.study.traffictestproject.dto.post.response;

import lombok.Builder;

@Builder
public record ResponsePostDto(Long id, String title, String content) {
}
