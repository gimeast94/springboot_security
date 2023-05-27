package com.springboot.security.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfiguration {

    private final JwtTokenProvider jwtTokenProvider;

    public SecurityConfiguration(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                //UI 비활성화
                .httpBasic().disable()
                //Rest API에서는 CSRF 보안이 필요없어서 비활성화
                .csrf().disable()

                //JWT 토큰으로 인증을 처리하며, 세션은 사용하지 않기 때문에 STATELESS 설정
                .sessionManagement()
                .sessionCreationPolicy(
                        SessionCreationPolicy.STATELESS
                )

                .and()
                //들어오는 요청에대해 사용권한을 체크한다
                .authorizeHttpRequests()
                //아래 경로에 대해서는 모두에게 허용한다
                .antMatchers("/sign-api/sign-in", "/sign-api/sign-up", "/sign-api/exception").permitAll()
                //product로 시작하는 경로의 GET 요청은 모두 허용한다
                .antMatchers(HttpMethod.GET, "/product/**").permitAll()
                //exception 단어가 들어간 모든 경로를 허용한다
                .antMatchers("**exception**").permitAll()
                .anyRequest().hasRole("ADMIN")

                .and()
                //권한을 확인하는 과정에서 통과하지 못하는 예외가 발생할 경우 예외를 전달한다
                .exceptionHandling().accessDeniedHandler(new CustomAccessDeniedHandler())

                .and()
                //인증과정에서 예외가 발생할 경우 예외를 전달한다
                .exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint())

                .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider),
                        UsernamePasswordAuthenticationFilter.class)
                .build();

    }

    @Bean
    public WebSecurityCustomizer configure() { //인증과 인가가 모두 적용되기전에 동작하는 설정이다
        return web -> web.ignoring()
                .antMatchers("/api-docs/**","/swagger-ui/**" ,"/sign-api/exception");//인증, 인가를 무시하는 경로를 설정한다
    }





}
