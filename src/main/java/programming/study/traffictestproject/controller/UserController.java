package programming.study.traffictestproject.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import programming.study.traffictestproject.dto.user.request.RequestLoginDto;
import programming.study.traffictestproject.dto.user.request.RequestSignUpDto;
import programming.study.traffictestproject.enums.error.ErrorCode;
import programming.study.traffictestproject.exception.UnAuthorizationException;
import programming.study.traffictestproject.service.Interface.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(HttpServletResponse response, @RequestBody RequestSignUpDto dto) {
        userService.signUp(response, dto);
        return ResponseEntity.ok().body("회원가입 성공!");
    }

    @GetMapping("/login")
    public ResponseEntity<String> login(HttpServletResponse response, @RequestBody RequestLoginDto dto) {
        try {
            userService.login(response, dto);
            return ResponseEntity.ok().body("로그인 성공!");
        } catch (Exception e) {
            throw new UnAuthorizationException("로그인에 실패하였습니다.", ErrorCode.ACCESS_DENIED_EXCEPTION);
        }
    }
}
