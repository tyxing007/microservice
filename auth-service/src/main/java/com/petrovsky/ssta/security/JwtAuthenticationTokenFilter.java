//package com.petrovsky.ssta.security;
//
//import com.petrovsky.ssta.model.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.web.filter.GenericFilterBean;
//import org.springframework.web.util.WebUtils;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//import java.util.Optional;
//
//public class JwtAuthenticationTokenFilter extends GenericFilterBean{
//
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    @Autowired
//    private JwtTokenUtil jwtTokenUtil;
//
//    @Value("${token.key}")
//    private String tokenKey;
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
//
//        Optional.ofNullable(WebUtils.getCookie(httpRequest, tokenKey)).ifPresent(cookie -> {
//            String authToken = cookie.getValue();
//            Optional<User> user = Optional.ofNullable(jwtTokenUtil.parseToken(authToken));
//
//            if (user.isPresent()) {
//                UserDetails userDetails = this.userDetailsService.loadUserByUsername(user.get().getEmail());
//
//                if (jwtTokenUtil.isTokenValid(authToken)) {
//                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
//                    SecurityContextHolder.getContext().setAuthentication(authentication);
//                }
//            }
//        });
//
//        filterChain.doFilter(servletRequest, servletResponse);
//    }
//}
