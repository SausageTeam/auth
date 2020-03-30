package com.app.auth.controller;

import com.app.auth.domain.common.GenericResponse;
import com.app.auth.domain.common.ServiceStatus;
import com.app.auth.domain.registration.Registration;
import com.app.auth.domain.registration.RegistrationPostRequest;
import com.app.auth.domain.registration.RegistrationPostResponse;
import com.app.auth.entity.User;
import com.app.auth.security.util.CookieUtil;
import com.app.auth.security.util.JwtUtil;
import com.app.auth.service.registration.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.app.auth.constant.Constant.*;

@Controller
@RequestMapping(value = "/registration")
public class RegistrationController {

    private RegistrationService registrationService;

    @Autowired
    public void setRegistrationService(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping
    public String registration(Model model, @RequestParam("redirect") String redirect) {
        model.addAttribute("redirect", redirect);
        return "registration";
    }

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public @ResponseBody
    RegistrationPostResponse registration(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, RegistrationPostRequest registrationPostRequest) {
        RegistrationPostResponse registrationPostResponse = new RegistrationPostResponse();

        if (registrationPostRequest == null) {
            prepareResponse(registrationPostResponse, false, "Unexpected Error");
        } else {
            Registration registration = Registration.builder()
                    .username(registrationPostRequest.getUsername())
                    .password(registrationPostRequest.getPassword())
                    .redirectUrl(registrationPostRequest.getRedirectUrl())
                    .build();
            HttpSession session = httpServletRequest.getSession();
            String aesToken = String.valueOf(session.getAttribute("aesToken"));
            User user = registrationService.registerUser(aesToken, registration);

            if (user == null) {
                prepareResponse(registrationPostResponse, false, "Invalid token");
            } else {
                String jwtToken = JwtUtil.generateToken(SIGNING_KEY, String.valueOf(user.getId()));
                CookieUtil.create(httpServletResponse, JWT_TOKEN_COOKIE_NAME, jwtToken, false, -1, "localhost");

                if (registration.getRedirectUrl() == null || registration.getRedirectUrl().length() == 0) {
                    registrationPostResponse.setRedirectUrl(DEFAULT_URL);
                } else {
                    registrationPostResponse.setRedirectUrl(registration.getRedirectUrl());
                }
                prepareResponse(registrationPostResponse, true, "");
            }
        }
        return registrationPostResponse;
    }

    private void prepareResponse(GenericResponse response, boolean success, String errorMessage) {
        response.setServiceStatus(new ServiceStatus(success ? "SUCCESS" : "FAILED", success, errorMessage));
    }

}
