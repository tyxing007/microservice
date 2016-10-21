//package com.petrovsky.ssta.security.exception;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.web.access.AccessDeniedHandler;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
//
//    @Value("${access.denied.page}")
//    private String accessDeniedPage;
//
//    @Override
//    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
//        System.out.println("++");
//        httpServletResponse.sendRedirect(accessDeniedPage);
//    }
//}
