package refrigerator.back.authentication.adapter.in.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import refrigerator.back.authentication.adapter.in.dto.TokenDTO;
import refrigerator.back.authentication.application.port.in.IssueTemporaryAccessTokenUseCase;
import refrigerator.back.authentication.application.port.in.LogoutUseCase;
import refrigerator.back.authentication.application.port.in.TokenReissueUseCase;
import refrigerator.back.authentication.exception.AuthenticationExceptionType;
import refrigerator.back.global.exception.BusinessException;
import refrigerator.back.authentication.adapter.in.dto.IssueTemporaryAccessTokenRequestDTO;
import refrigerator.back.authentication.adapter.in.dto.TemporaryAccessTokenResponseDTO;
import refrigerator.back.member.exception.MemberExceptionType;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static refrigerator.back.global.exception.ValidationExceptionHandler.check;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthenticationController {

    private final TokenReissueUseCase tokenReissueUseCase;
    private final LogoutUseCase logoutUseCase;
    private final IssueTemporaryAccessTokenUseCase issueTemporaryAccessToken;

    @Value("${jwt.type}")
    private final String grantType;

    @GetMapping("/api/auth/logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void logout(HttpServletRequest request, HttpServletResponse response){
        String accessToken = request.getHeader(HttpHeaders.AUTHORIZATION).split(" ")[1];
        logoutUseCase.logout(accessToken);
        // TODO: 인증/인가 쿠키 관련 로직 캡슐화하기

        Cookie cookie = new Cookie("Refresh-Token", null);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(0);
        cookie.setPath("/api/auth/reissue");

        response.addCookie(cookie);

    }

    @PostMapping("/api/auth/reissue")
    @ResponseStatus(HttpStatus.CREATED)
    public TokenDTO reissue(HttpServletRequest request, HttpServletResponse response){
        Cookie[] cookies = request.getCookies();
        try{
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("Refresh-Token")){
                    return tokenReissueUseCase.reissue(cookie.getValue());
                }
            }
        }catch (NullPointerException e){
            throw new BusinessException(AuthenticationExceptionType.NOT_FOUND_COOKIE);
        }
        throw new BusinessException(AuthenticationExceptionType.NOT_FOUND_TOKEN);
    }

    @PostMapping("/api/auth/issue/temporary-token")
    public TemporaryAccessTokenResponseDTO findPassword(
            @RequestBody @Valid IssueTemporaryAccessTokenRequestDTO request,
            BindingResult result){
        check(result, MemberExceptionType.EMPTY_INPUT_DATA);
        String authToken = issueTemporaryAccessToken.issueTemporaryAccessToken(request.getEmail());
        return new TemporaryAccessTokenResponseDTO(grantType, authToken);
    }

}
