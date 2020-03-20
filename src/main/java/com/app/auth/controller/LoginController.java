package com.app.auth.controller;

import com.app.auth.domain.login.LoginRequest;
import com.app.auth.domain.login.LoginResponse;
import com.app.auth.domain.common.Status;
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
import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {

    private LoginService loginService;

    private static final String jwtTokenCookieName = "JWT-TOKEN";
    private static final String signingKey = "signingKey";

    @Autowired
    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, @RequestParam(required = false) String redirect) {
        model.addAttribute("redirect", redirect);
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public @ResponseBody
    LoginResponse login(HttpServletResponse httpServletResponse, LoginRequest req) {
        LoginResponse res = new LoginResponse();

        if (req == null) {
            prepareResponse(res, false, "Unexpected Error");
            return res;
        }

        User user = loginService.loginUser(req);
        if (user == null) {
            prepareResponse(res, false, "Invalid username or password");
            return res;
        }

        String token = JwtUtil.generateToken(signingKey, String.valueOf(user.getId()));
        CookieUtil.create(httpServletResponse, jwtTokenCookieName, token, false, -1, "localhost");

        if (req.getRedirectUrl() == null) {
            req.setRedirectUrl("http://localhost:4200/");
        }
        res.setRedirectUrl(req.getRedirectUrl());
        prepareResponse(res, true, "");

        return res;
    }

    private void prepareResponse(LoginResponse response, boolean success, String errorMessage) {
        response.setStatus(new Status(success, success ? "SUCCESS" : "FAILED", errorMessage));
    }
}
