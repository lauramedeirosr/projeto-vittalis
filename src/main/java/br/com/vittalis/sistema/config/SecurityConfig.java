package br.com.vittalis.sistema.config;


import br.com.vittalis.sistema.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserService userService;

    public SecurityConfig(UserService userService) {
        this.userService = userService;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                //.requestMatchers(new AntPathRequestMatcher("/**"))
                //.requestMatchers(new AntPathRequestMatcher("/usuario/**"))
                .requestMatchers(new AntPathRequestMatcher("/css/**"))
                .requestMatchers(new AntPathRequestMatcher("/js/**"))
                .requestMatchers(new AntPathRequestMatcher("/fonts/**"))
                .requestMatchers(new AntPathRequestMatcher("/img/**"))
                .requestMatchers(new AntPathRequestMatcher("/Source/**"));

    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .authorizeHttpRequests((requests) -> requests
                        // public endpoints
                        .requestMatchers("/login/**", "/home/**", "/cliente/cadastro/**").permitAll()
                        // ensure both the exact path /administrador and any subpaths are restricted to ADMINISTRADOR
                        .requestMatchers("/administrador", "/administrador/**").hasAuthority("ADMINISTRADOR")
                        // other admin-only areas
                        .requestMatchers("/pacote/**", "/navio/**").hasAuthority("ADMINISTRADOR")

                        // any authenticated user may access other routes; additionally allow ADMINISTRADOR to access everything
                        .anyRequest().authenticated()
                )
                .userDetailsService(userService)
                .formLogin((form) -> form
                        .loginPage("/login")
                        .successHandler(new RedirectOnLoginSuccessHandler())
                        .permitAll()
                )
                .exceptionHandling((exceptions) -> exceptions
                        .accessDeniedPage("/acesso-negado")
                )
                .logout((logout) -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                );

        return httpSecurity.build();
    }
}