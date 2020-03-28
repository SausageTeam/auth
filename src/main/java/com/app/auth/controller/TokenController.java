package com.app.auth.controller;

import com.app.auth.domain.common.GenericResponse;
import com.app.auth.domain.common.ServiceStatus;
import com.app.auth.domain.token.Token;
import com.app.auth.domain.token.TokenPostRequest;
import com.app.auth.domain.token.TokenPostResponse;
import com.app.auth.service.token.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/token")
public class TokenController {

    private TokenService tokenService;

    @Autowired
    public void setTokenService(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @GetMapping
    public String getToken(Model model, @RequestParam("redirect") String redirect) {
        model.addAttribute("redirect", redirect);
        return "token";
    }

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public @ResponseBody
    TokenPostResponse postToken(HttpServletRequest httpServletRequest, TokenPostRequest tokenPostRequest) {

        TokenPostResponse tokenPostResponse = new TokenPostResponse();

        if (tokenPostRequest == null) {
            prepareResponse(tokenPostResponse, false, "Unexpected Error");
        } else {
            Token token = Token.builder()
                    .aesToken(tokenPostRequest.getAesToken())
                    .email(tokenPostRequest.getEmail())
                    .redirectUrl(tokenPostRequest.getRedirectUrl())
                    .build();
            if (!tokenService.isValidToken(token)) {
                prepareResponse(tokenPostResponse, false, "Invalid Token");
            } else {
                HttpSession session = httpServletRequest.getSession();
                session.setAttribute("aesToken", token.getAesToken());
                tokenPostResponse.setRedirectUrl("/auth/registration?redirect=" + token.getRedirectUrl());
                prepareResponse(tokenPostResponse, true, "");
            }
        }
        return tokenPostResponse;
    }

    private void prepareResponse(GenericResponse response, boolean success, String errorMessage) {
        response.setServiceStatus(new ServiceStatus(success ? "SUCCESS" : "FAILED", success, errorMessage));
    }

}
