package com.example.restaurantbackend.security;

import com.example.restaurantbackend.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.Optional;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    final
    TokenService tokenService;
    final
    UserRepository userRepository;

    /**
     * Constructor for SecurityFilter
     * @param tokenService TokenService
     * @param userRepository UserRepository
     */
    public SecurityFilter(TokenService tokenService, UserRepository userRepository) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

    /**
     * This method is used to filter the requests and validate the token
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @param filterChain FilterChain
     * @throws ServletException ServletException
     * @throws IOException IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        var token = this.recoverToken(request);
        if(token != null && !token.isEmpty()) {
            var name = tokenService.validateToken(token);
            Optional<UserDetails> user = userRepository.findByName(name);
            if (user.isPresent()) {
                var authentication = new UsernamePasswordAuthenticationToken(user, null, user.get().getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                throw new UsernameNotFoundException("User not found with the given name");
            }
        }
        filterChain.doFilter(request, response);
    }

    /**
     * This method is used to recover the token from the request
     * @param  request HttpServletRequest
     * @return token
     */
    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) return null;
        return authHeader.replace("Bearer ", "");
    }

}
