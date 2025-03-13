package programming.study.traffictestproject.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "회원가입 성공 - 어노테이션에서 잡힙",content = @Content(mediaType = "application/json")),
//            @ApiResponse(responseCode = "401", description = "회원가입 실패 - 어노테이션에서 잡힘",content = @Content(mediaType = "application/json"))
//    })  -> 이런식으로 컨트롤러에서도 에러 처리 가능하다. 따로 에러 받는 클래스를 만들어줘야함 setResponse Method 대체메서드 필요
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
