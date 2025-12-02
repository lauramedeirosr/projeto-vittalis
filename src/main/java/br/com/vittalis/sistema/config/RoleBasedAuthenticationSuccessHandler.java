package br.com.vittalis.sistema.config;

import java.io.IOException;
import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RoleBasedAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        boolean isAdmin = authorities.stream()
                .anyMatch(a -> {
                    String auth = a.getAuthority();
                    return "ADMINISTRADOR".equalsIgnoreCase(auth)
                            || "ADMIN".equalsIgnoreCase(auth)
                            || "ROLE_ADMIN".equalsIgnoreCase(auth);
                });

        if (isAdmin) {
            redirectStrategy.sendRedirect(request, response, "/," +
                    "/administrador");
        } else {
            redirectStrategy.sendRedirect(request, response, "/home");
        }
    }
}
