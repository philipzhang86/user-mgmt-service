package com.jmalltech.security;


import com.auth0.jwt.interfaces.Claim;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import java.util.Map;

public class JwtTokenFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String authHeader = httpRequest.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            // 提取实际的token
            String token = authHeader.substring(7);
            System.out.println("Token: " + token);
            Map<String, Claim> claims = JwtUtil.validateToken(token);

            if (claims != null) {
                String role = claims.get("role").asString();
                System.out.println("Role: " + role);
                System.out.println("Original Request URI: " + httpRequest.getRequestURI());
                // 从请求URI中移除上下文路径
                String contextPath = httpRequest.getContextPath();
                String path = httpRequest.getRequestURI().substring(contextPath.length());
                System.out.println("New Path: " + path);
                // 根据角色和请求路径决定是否放行
                if (isAccessAllowed(role, path)) { // 使用修改后的path进行检查
                    chain.doFilter(request, response);
                } else {
                    ((HttpServletResponse) response).sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
                }
            } else {
                ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
            }
        } else {
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token is missing or malformed");
        }
    }

    private boolean isAccessAllowed(String role, String requestURI) {
        // 示例规则: Staff可以访问所有api，Client仅限访问/api/client/...
        if ("STAFF".equals(role)) {
            return true; // Staff有权访问所有路径
        } else return "CLIENT".equals(role) && requestURI.startsWith("/api/clients"); // Client仅能访问/api/client/下的路径
        //其它情况均不允许访问
    }
}
