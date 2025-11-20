package com.example.schedule_develop.config.filter;

import com.example.schedule_develop.config.enums.ErrorCode;
import com.example.schedule_develop.config.exception.LoginFailException;
import com.example.schedule_develop.config.exception.UnauthorizedException;
import com.example.schedule_develop.dto.common.GlobalResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LoginCheckFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);
        ///api/users
        if (req.getMethod().equals("GET")) {
            chain.doFilter(req, response);
        } else {
            //true, false
            //blackList List<>
            boolean scheduleLoginCheck = req.getRequestURI().contains("api/schedules");
            boolean userLoginCheck = req.getRequestURI().contains("api/users/logout");
            //스케쥴 경로로 들어왔는데, 여기서 session 이 null이면 감지할거야.
            try {
                if (scheduleLoginCheck || userLoginCheck) {
                    if ((session == null || session.getAttribute("LOGIN_USER") == null)) {
                        throw new UnauthorizedException(ErrorCode.LOGIN_REQUIRED);
                        //LoginFailException(ErrorCode.LOGIN_REQUIRED); //LOGIN
                    }
                }
            } catch (UnauthorizedException e) {

                res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                res.setCharacterEncoding("UTF-8");
                res.setContentType("application/json");

                GlobalResponse<Void> json = GlobalResponse.fail(e.getStatus(), e.getMessage());

                ObjectMapper mapper = new ObjectMapper();
                String body = mapper.writeValueAsString(json);
                res.getWriter().write(body);
                res.getWriter().flush();
                return;
            }
            chain.doFilter(req, response);
        }
    }
}
