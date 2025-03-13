package programming.study.traffictestproject.service.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import programming.study.traffictestproject.dto.user.request.RequestLoginDto;
import programming.study.traffictestproject.dto.user.request.RequestSignUpDto;
import programming.study.traffictestproject.entity.UserEntity;
import programming.study.traffictestproject.enums.error.ErrorCode;
import programming.study.traffictestproject.exception.UnAuthorizationException;
import programming.study.traffictestproject.repository.UserRepository;
import programming.study.traffictestproject.service.Interface.UserService;

@RequiredArgsConstructor
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void signUp(HttpServletResponse response, RequestSignUpDto dto) {

        if(userRepository.existsByEmail(dto.email())) {
            throw new UnAuthorizationException("invalid email", ErrorCode.INTERNAL_SERVER_EXCEPTION);
        }

        if(userRepository.existsByUserId(dto.userId())) {
            throw new UnAuthorizationException("invalid id", ErrorCode.INTERNAL_SERVER_EXCEPTION);
        }

        UserEntity newUser = UserEntity.builder()
                .userId(dto.userId())
                .email(dto.email())
                .password(passwordEncoder.encode(dto.password()))
                .build();

        userRepository.save(newUser);

    }

    @Override
    public void login(HttpServletResponse response, RequestLoginDto dto) {

    }
}
