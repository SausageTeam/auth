package com.app.auth.config;

import com.app.auth.security.filter.RegistrationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<RegistrationFilter> jwtFilter() {
        final FilterRegistrationBean<RegistrationFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new RegistrationFilter());
        registrationBean.addUrlPatterns("/auth/registration");

        return registrationBean;
    }
}
