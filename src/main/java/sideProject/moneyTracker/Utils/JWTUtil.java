package sideProject.moneyTracker.Utils;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Service;
import sideProject.moneyTracker.Beans.UserDetails;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.sql.Date;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Service
public class JWTUtil {

    private String signatureAlgorithm = SignatureAlgorithm.HS256.getJcaName();
    private String encodedSecretKey = "this+is+my+key+it+must+be+at+least+256+bits+long";
    private Key decodedSecretKey = new SecretKeySpec(Base64.getDecoder().decode(encodedSecretKey), this.signatureAlgorithm);

    public String generateToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userDetails.getId());
        claims.put("userName", userDetails.getFirst_name());
        return createToken(claims, userDetails.getEmail());
    }

    private String createToken(Map<String, Object> claims, String email) {
        Instant now = Instant.now();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(30, ChronoUnit.MINUTES)))
                .signWith(this.decodedSecretKey)
                .compact();
    }

    public Claims extractAllClaims(String token) throws ExpiredJwtException {
        JwtParser jwtParser = Jwts.parserBuilder()
                .setSigningKey(this.decodedSecretKey)
                .build();
        return jwtParser.parseClaimsJws(token).getBody();
    }

    public String extractEmail (String token){
        return extractAllClaims(token).getSubject();
    }

    public java.util.Date extractExpirationDate (String token){
        return extractAllClaims(token).getExpiration();
    }

    public boolean isTokenExpired(String token){
        try{
            extractAllClaims(token);
            return false;
        } catch (ExpiredJwtException err){
            return true;
        }
    }

    public boolean validateToken(String token){
        //final String userEmail = extractEmail(token);(userEmail.equals(userDetails.getEmail()) && !isTokenExpired(token))
        return (!isTokenExpired(token));
    }
}
