//package com.petrovsky.ssta.security;
//
//import com.petrovsky.ssta.model.Authority;
//import com.petrovsky.ssta.model.User;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDateTime;
//import java.time.ZoneId;
//import java.util.Date;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Component
//public class JwtTokenUtil {
//
//    @Value("${token.secret}")
//    private String secret;
//
//    @Value("${token.expiration-time}")
//    private Long expiration;
//
//    @Value ("${token.time.zoneId}")
//    private String timeZoneId;
//
//    public String generateToken(User user) {
//        Claims claims = Jwts.claims().setSubject(user.getEmail());
//        claims.put("ID", String.valueOf(user.getId()));
//        claims.put("Authorities", user.getAuthorities());
//
//        Long nowMillis = LocalDateTime.now().atZone(ZoneId.of(timeZoneId)).toInstant().toEpochMilli();
//        Date expirationDate = new Date(nowMillis + expiration);
//
//        return Jwts.builder()
//                .setHeaderParam("typ", "JWT")
//                .setHeaderParam("alg", "HS25")
//                .setClaims(claims)
//                .setExpiration(expirationDate)
//                .signWith(SignatureAlgorithm.HS512, secret)
//                .compact();
//    }
//
//    public User parseToken(String token) {
//
//        //todo: remove Exception
//        try {
//            Claims body = Jwts.parser()
//                    .setSigningKey(secret)
//                    .parseClaimsJws(token)
//                    .getBody();
//
//            User user = new User();
//            user.setName(body.getSubject());
//            user.setId(Long.parseLong((String) body.get("ID")));
//            user.setAuthorities(((List<Authority>) body.get("Authorities")).stream().collect(Collectors.toSet()));
//
//            return user;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    public boolean isTokenValid(String token) {
//
//        try {
//            Claims body = Jwts.parser()
//                    .setSigningKey(secret)
//                    .parseClaimsJws(token)
//                    .getBody();
//            return body.getExpiration().after(new Date(LocalDateTime.now().atZone(ZoneId.of(timeZoneId)).toInstant().toEpochMilli()));
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//}
