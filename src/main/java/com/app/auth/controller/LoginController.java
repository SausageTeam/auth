package com.app.auth.controller;

import com.app.auth.domain.common.GenericResponse;
import com.app.auth.domain.common.ServiceStatus;
import com.app.auth.domain.login.Login;
import com.app.auth.domain.login.LoginPostRequest;
import com.app.auth.domain.login.LoginPostResponse;
import com.app.auth.entity.User;
import com.app.auth.security.util.CookieUtil;
import com.app.auth.security.util.JwtUtil;
import com.app.auth.service.login.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

import static com.app.auth.constant.Constant.*;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

    private LoginService loginService;

    @Autowired
    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping
    public String getLogin(Model model, @RequestParam(required = false) String redirect) {
        model.addAttribute("redirect", redirect);
        return "login";
    }

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public @ResponseBody
    LoginPostResponse postLogin(HttpServletResponse httpServletResponse, LoginPostRequest loginPostRequest) {
        LoginPostResponse loginPostResponse = new LoginPostResponse();

        if (loginPostRequest == null) {
            prepareResponse(loginPostResponse, false, "Unexpected Error");
        }else {
            Login login = Login.builder()
                    .username(loginPostRequest.getUsername())
                    .password(loginPostRequest.getPassword())
                    .redirectUrl(loginPostRequest.getRedirectUrl())
                    .build();
            User user = loginService.loginUser(login);
            if (user == null) {
                prepareResponse(loginPostResponse, false, "Invalid username or password");
                return loginPostResponse;
            }
            String token = JwtUtil.generateToken(SIGNING_KEY, String.valueOf(user.getId()));
            CookieUtil.create(httpServletResponse, JWT_TOKEN_COOKIE_NAME, token, false, -1, "localhost");

            if (login.getRedirectUrl() == null || login.getRedirectUrl().length() == 0) {
                loginPostResponse.setRedirectUrl(DEFAULT_URL);
            } else {
                loginPostResponse.setRedirectUrl(login.getRedirectUrl());
            }
            prepareResponse(loginPostResponse, true, "");
        }
        return loginPostResponse;
    }

    private void prepareResponse(GenericResponse response, boolean success, String errorMessage) {
        response.setServiceStatus(new ServiceStatus(success ? "SUCCESS" : "FAILED", success, errorMessage));
    }
}
