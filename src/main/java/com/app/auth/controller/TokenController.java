package com.app.auth.controller;

import com.app.auth.domain.common.Status;
import com.app.auth.domain.token.TokenRequest;
import com.app.auth.domain.token.TokenResponse;
import com.app.auth.service.token.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class TokenController {

    private TokenService tokenService;

    @Autowired
    public void setTokenService (TokenService tokenService){
        this.tokenService = tokenService;
    }

    @RequestMapping(value = "/token", method = RequestMethod.GET)
    public String token(Model model, @RequestParam("redirect") String redirect) {
        model.addAttribute("redirect", redirect);
        return "token";
    }

    @RequestMapping(value = "/token", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public @ResponseBody TokenResponse token(HttpServletRequest httpServletRequest, TokenRequest req) {

        TokenResponse res = new TokenResponse();

        if (req == null) {
            prepareResponse(res, false, "Unexpected Error");
            return res;
        }

        if (!tokenService.isValidToken(req)){
            prepareResponse(res, false, "Invalid Token");
            return res;
        }

        HttpSession session = httpServletRequest.getSession();
        session.setAttribute("token", "valid");
        res.setToken(req.getToken());
        res.setRedirectUrl("/auth/registration?redirect=" + req.getRedirectUrl());
        prepareResponse(res, true, "");
        return res;
    }

    private void prepareResponse(TokenResponse response, boolean success, String errorMessage) {
        response.setStatus(new Status(success, success ? "SUCCESS" : "FAILED", errorMessage));
    }

}
