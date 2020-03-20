package com.app.auth.controller;

import com.app.auth.domain.common.Status;
import com.app.auth.domain.registration.RegistrationRequest;
import com.app.auth.domain.registration.RegistrationResponse;
import com.app.auth.entity.User;
import com.app.auth.security.util.CookieUtil;
import com.app.auth.security.util.JwtUtil;
import com.app.auth.service.registration.RegistrationService;
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

@Controller
public class RegistrationController {

    private RegistrationService registrationService;

    private static final String jwtTokenCookieName = "JWT-TOKEN";
    private static final String signingKey = "signingKey";

    @Autowired
    public void setRegistrationService(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model, @RequestParam("redirect") String redirect) {
        model.addAttribute("redirect", redirect);
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public @ResponseBody
    RegistrationResponse token(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, RegistrationRequest req) {
        RegistrationResponse res = new RegistrationResponse();


        if (req == null) {
            prepareResponse(res, false, "Unexpected Error");
            return res;
        }

        User user = registrationService.registerUser(httpServletRequest, req);
        String token = JwtUtil.generateToken(signingKey, String.valueOf(user.getId()));
        CookieUtil.create(httpServletResponse, jwtTokenCookieName, token, false, -1, "localhost");

        if (req.getRedirectUrl() == null) {
            req.setRedirectUrl("http://localhost:4200/");
        }
        res.setRedirectUrl(req.getRedirectUrl());
        prepareResponse(res, true, "");

        return res;
    }

    private void prepareResponse(RegistrationResponse response, boolean success, String errorMessage) {
        response.setStatus(new Status(success, success ? "SUCCESS" : "FAILED", errorMessage));
    }
}
