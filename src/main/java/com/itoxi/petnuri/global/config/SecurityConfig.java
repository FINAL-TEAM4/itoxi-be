package com.itoxi.petnuri.global.config;

import static com.itoxi.petnuri.global.common.exception.type.ErrorCode.FORBIDDEN;
import static com.itoxi.petnuri.global.common.exception.type.ErrorCode.UN_AUTHORIZED;

import com.itoxi.petnuri.global.common.exception.Exception401;
import com.itoxi.petnuri.global.common.exception.Exception403;
import com.itoxi.petnuri.global.common.response.FilterResponse;
import com.itoxi.petnuri.global.security.jwt.JwtAuthenticationFilter;
import com.itoxi.petnuri.global.security.jwt.JwtExceptionFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Slf4j
@Configuration
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final JwtExceptionFilter jwtExceptionFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.headers().frameOptions().sameOrigin();

        http.cors().configurationSource(configurationSource());

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.formLogin().disable();

        http.httpBasic().disable();

        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(jwtExceptionFilter, jwtAuthenticationFilter.getClass());

        http.exceptionHandling().authenticationEntryPoint((request, response, authException) -> {
            log.warn("인증되지 않은 사용자가 자원에 접근하려 합니다 : " + authException.getMessage());
            FilterResponse.unAuthorized(response, new Exception401(UN_AUTHORIZED));
        });

        http.exceptionHandling().accessDeniedHandler((request, response, accessDeniedException) -> {
            log.warn("권한이 없는 사용자가 자원에 접근하려 합니다 : " + accessDeniedException.getMessage());
            FilterResponse.forbidden(response, new Exception403(FORBIDDEN));
        });

//        http.authorizeRequests(
//                authorize -> authorize.antMatchers("/auth/**",  "/h2-console/**").permitAll()
//                        .antMatchers("/api/**").hasAnyAuthority("USER")
//                        .anyRequest().authenticated()
//        );

        http.authorizeRequests()
                .antMatchers("/**")
                .permitAll()
                .anyRequest()
                .authenticated();

        return http.build();
    }

    public CorsConfigurationSource configurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.addAllowedOriginPattern("*");
        config.setAllowCredentials(true);
        config.addExposedHeader("Authorization");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws
            Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}