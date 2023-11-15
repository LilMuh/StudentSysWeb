package com.tomh.studentspringboot.common;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.tomh.studentspringboot.entity.Admin;
import com.tomh.studentspringboot.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
public class JwtTokenUtils {

    private static AdminService staticAdminService;
    private static final Logger log = LoggerFactory.getLogger(JwtTokenUtils.class);

    @Resource
    private AdminService adminService;

    @PostConstruct
    public void setUserService() {
        staticAdminService = adminService;
    }

    /**
     * generate token
     */
    public static String genToken(String adminId, String password) {
        return JWT.create().withAudience(adminId) // Save user id in the token as audience
                .withExpiresAt(DateUtil.offsetHour(new Date(), 2)) // token expires after 2 hours
                .sign(Algorithm.HMAC256(password)); // set user password as the key of token
    }

    /**
     * Get user's information
     */
    public static Admin getCurrentUser() {
        String token = null;
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            token = request.getHeader("token");
            if (StrUtil.isBlank(token)) {
                token = request.getParameter("token");
            }
            if (StrUtil.isBlank(token)) {
                log.error("Fail to load current token， token: {}", token);
                return null;
            }
            // 解析token，获取用户的id
            String adminId = JWT.decode(token).getAudience().get(0);
            return staticAdminService.findById(Integer.valueOf(adminId));
        } catch (Exception e) {
            log.error("Fail to get user's information, token={}", token,  e);
            return null;
        }
    }
}