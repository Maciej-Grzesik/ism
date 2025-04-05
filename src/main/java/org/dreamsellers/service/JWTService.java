//package org.dreamsellers.service;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//
//import org.dreamsellers.model.AuthEntity;
//import org.dreamsellers.types.Role;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import javax.crypto.SecretKey;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.function.Function;
//
//@Service
//public class JWTService {
//
//    @Value("${jwt.token.key}")
//    private String jwtSignInKey;
//
////    public String generateToken(AuthEntity userDetail) {
////        return generateToken(new HashMap<>(), userDetail);
////    }
//
//    public boolean isTokenValid(String token) {
//        try {
//            return !isTokenExpired(token);
//        } catch (Exception e) {
//            return false;
//        }
//    }
//
//    public String extractUsername(String token) {
//        return extractClaim(token, Claims::getSubject);
//    }
//
//    private boolean isTokenExpired(String token) {
//        return extractExpiration(token).before(new Date());
//    }
//
//    private Date extractExpiration(String token) {
//        return extractClaim(token, Claims::getExpiration);
//    }
//
//    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
//        final Claims claims = extractAllClaims(token);
//        return claimsResolver.apply(claims);
//    }
//
//    public Role extractRole(String token) {
//        String roleString = extractClaim(token, (claims -> claims.get("role", String.class)));
//        return Role.valueOf(roleString);
//    }
//
//    private Claims extractAllClaims(String token){
//        return Jwts
//                .parser()
//                .verifyWith(getSigningKey())
//                .build()
//                .parseSignedClaims(token)
//                .getPayload();
//    }
//
////    private String generateToken(Map<String, Object> extraClaims, AuthEntity userDetails) {
////        extraClaims.put("role", userDetails.getRole());
////        // 24 hours
////        long tokenLifeTime = 1000 * 60 * 24;
////        return Jwts
////                .builder()
////                .claims(extraClaims)
////                .subject(userDetails.getUsername())
////                .issuedAt(new Date(System.currentTimeMillis()))
////                .expiration(new Date(System.currentTimeMillis() + tokenLifeTime))
////                .signWith(getSigningKey())
////                .compact();
////    }
//
//    private SecretKey getSigningKey() {
//        byte[] keyBytes = Decoders.BASE64.decode(jwtSignInKey);
//        return Keys.hmacShaKeyFor(keyBytes);
//    }
//}