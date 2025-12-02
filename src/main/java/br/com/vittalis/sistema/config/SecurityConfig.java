package br.com.vittalis.sistema.config;

import br.com.vittalis.sistema.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
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
                        // Endpoints públicos
                        .requestMatchers("/", "/home/**", "/login/**", "/cliente/cadastro/**", "/cliente/salvar" ).permitAll()
                        // Acesso do Administrador
                        .requestMatchers("/administrador", "/administrador/**").hasAuthority("ADMINISTRADOR")
                        .requestMatchers("/pacote/**", "/navio/**").hasAuthority("ADMINISTRADOR")
                        // Qualquer outra requisição precisa de autenticação
                        .anyRequest().authenticated()
                )
                .userDetailsService(userService)
                .formLogin((form) -> form
                        .loginPage("/login")
                        .successHandler(new RedirectOnLoginSuccessHandler())
                        .permitAll()
                )
                .rememberMe(rememberMe -> rememberMe
                        .key("vittalis-secret-key")
                        .tokenValiditySeconds(86400)
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

    @Bean
    public AuthenticationSuccessHandler roleBasedAuthenticationSuccessHandler() {
        return new RoleBasedAuthenticationSuccessHandler();
    }
}
