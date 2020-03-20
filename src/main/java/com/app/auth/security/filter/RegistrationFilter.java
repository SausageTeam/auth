package com.app.auth.security.filter;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RegistrationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        HttpSession session = httpServletRequest.getSession();
        Object tokenValid = session.getAttribute("tokenValid");
        if (tokenValid == null) {
            httpServletResponse.sendRedirect("login");
        } else {
            String val = tokenValid.toString();

            if (!val.equals("valid")) {
                System.out.println(val);
                httpServletResponse.sendRedirect("login");
            } else {
                filterChain.doFilter(httpServletRequest, httpServletResponse);
            }
        }
    }

}
