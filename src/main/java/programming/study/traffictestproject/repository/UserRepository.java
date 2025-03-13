package programming.study.traffictestproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import programming.study.traffictestproject.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    boolean existsByEmail(String email);

    boolean existsByUserId(String userId);
}
