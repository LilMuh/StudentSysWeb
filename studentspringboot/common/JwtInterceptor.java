package com.tomh.studentspringboot.common;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.tomh.studentspringboot.entity.Admin;
import com.tomh.studentspringboot.exception.CustomException;
import com.tomh.studentspringboot.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * JWT interceptor
 */
@Component
public class JwtInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(JwtInterceptor.class);

    @Resource
    private AdminService adminService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 1. get token through headers from http request
        String token = request.getHeader("token");
        if (StrUtil.isBlank(token)) {
            // if no token in headers, try parameters  /api/admin?token=xxxxx
            token = request.getParameter("token");
        }
        // 2. start authorization
        if (StrUtil.isBlank(token)) {
            throw new CustomException("Empty token, please log in");
        }
        // get userID in token
        String userId;
        Admin admin;
        try {
            userId = JWT.decode(token).getAudience().get(0);
            // search user in database by userid from token
            admin = adminService.findById(Integer.parseInt(userId));
        } catch (Exception e) {
            String errMsg = "Fail to authorize token, please log in";
            log.error(errMsg + ", token=" + token, e);
            throw new CustomException(errMsg);
        }
        if (admin == null) {
            throw new CustomException("Unable to find user, please log in");
        }
        try {
            // authorize token by username and password
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(admin.getPassword())).build();
            jwtVerifier.verify(token); // authorize token
        } catch (JWTVerificationException e) {
            throw new CustomException("Fail to authorize token, please log in");
        }
        return true;
    }
}