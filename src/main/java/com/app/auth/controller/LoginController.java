package com.app.auth.controller;

import com.app.auth.domain.auth.AuthRequest;
import com.app.auth.domain.auth.AuthResponse;
import com.app.auth.domain.auth.LoginDTO;
import com.app.auth.domain.common.Status;
import com.app.auth.security.CookieUtil;
import com.app.auth.security.JwtUtil;
import com.app.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {

    private UserService userService;

    private static final String jwtTokenCookieName = "JWT-TOKEN";
    private static final String signingKey = "signingKey";
    private static final Map<String, String> credentials = new HashMap<>();

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public @ResponseBody AuthResponse
    login(HttpServletResponse httpServletResponse, AuthRequest req){

        AuthResponse res = new AuthResponse();

        if(req == null) {
            prepareResponse(res, false, "Unexpected Error.");
            return res;
        }

        String username = req.getUsername();
        String password = req.getPassword();
        String redirectUrl = req.getRedirectUrl();

        if (username == null || password == null){
            prepareResponse(res, false, "Invalid username or password!");
            return res;
        }

        if(redirectUrl == null) {
            prepareResponse(res, false, "Missing redirect Url!");
            return res;
        }

        if(!credentials.containsKey(username) || !credentials.get(username).equals(password)) {
            if(!userService.loginUser(new LoginDTO(username, password))) {
                prepareResponse(res, false, "Incorrect username / password!");
                return res;
            }
        }

        // find the match key-value pair for user, update credential
        if(!credentials.containsKey(username)) {
            credentials.put(username, password);
        }

        String token = JwtUtil.generateToken(signingKey, username);
        CookieUtil.create(httpServletResponse, jwtTokenCookieName, token, false, -1, "localhost");

        res.setRedirectUrl(redirectUrl);
        prepareResponse(res, true, "");

        return res;
    }

    private void prepareResponse(AuthResponse response, boolean success, String errorMessage) {
        response.setStatus(new Status(success, success ? "SUCCESS" : "FAILED", errorMessage));
    }
}
