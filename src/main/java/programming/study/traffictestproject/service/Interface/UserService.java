package programming.study.traffictestproject.service.Interface;

import jakarta.servlet.http.HttpServletResponse;
import programming.study.traffictestproject.dto.user.request.RequestLoginDto;
import programming.study.traffictestproject.dto.user.request.RequestSignUpDto;

public interface UserService {

    void signUp(HttpServletResponse response, RequestSignUpDto dto);

    void login(HttpServletResponse response, RequestLoginDto dto);

}
