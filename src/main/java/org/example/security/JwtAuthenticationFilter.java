package org.example.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import java.util.UUID;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.example.domain.dto.responses.ErrorResponse;
import org.example.exceptions.AccessDeniedException;
import org.example.services.AuthClientService;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    public static final String BEARER_PREFIX = "Bearer ";
    public static final String HEADER_NAME = "Authorization";
    private final AuthClientService authClientService;
    private final UserDetailServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws IOException {
        try {
            final String authHeader = request.getHeader(HEADER_NAME);
            if (authHeader == null || !StringUtils.startsWith(authHeader, BEARER_PREFIX)) {
                filterChain.doFilter(request, response);
                return;
            }

            String jwtToken = authHeader.substring(BEARER_PREFIX.length());
            if (StringUtils.isBlank(jwtToken) || !authClientService.validateToken()) {
                throw new AccessDeniedException("Provided token is invalid/expired or blank");
            }

            UserDetails userDetails = userDetailsService.loadUserByUsername(authHeader);
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()));
            filterChain.doFilter(request, response);
        } catch (Exception ex) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setContentType("application/json");
            ErrorResponse errorResponse = ErrorResponse.builder()
                    .id(UUID.randomUUID())
                    .message(ex.getMessage())
                    .build();
            String json = new ObjectMapper().writeValueAsString(errorResponse);
            response.getWriter().write(json);
        }
    }
}
