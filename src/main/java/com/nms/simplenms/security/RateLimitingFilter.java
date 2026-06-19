package com.nms.simplenms.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class RateLimitingFilter extends OncePerRequestFilter {

    private final RateLimiter rateLimiter;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String requestPath = request.getRequestURI();

        // Apply rate limiting only to protected endpoints
        if (requestPath.startsWith("/getStatus") || requestPath.startsWith("/getDeviceStatus")) {
            String clientId = getClientId(request);

            if (!rateLimiter.allowRequest(clientId)) {
                response.setStatus(429); // Too Many Requests
                response.setContentType("application/json");
                response.getWriter().write("{\"error\": \"Rate limit exceeded: 10 requests per minute\"}");
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    private String getClientId(HttpServletRequest request) {
        // Use client IP address as identifier
        String clientIp = request.getHeader("X-Forwarded-For");
        if (clientIp == null || clientIp.isEmpty()) {
            clientIp = request.getRemoteAddr();
        }
        return clientIp;
    }

}
