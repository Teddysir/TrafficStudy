package programming.study.traffictestproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    // Mapping Entity

    @ManyToOne()
    @JoinColumn(name = "user_id", unique = true, nullable = false)
    private UserEntity user;

    @ManyToOne()
    @JoinColumn(name = "post_id", unique = true, nullable = false)
    private PostEntity post;

}
