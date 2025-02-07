package com.example.internportfoliotask2.util.security;

import com.example.internportfoliotask2.globalException.exceptions.Bearer_Token;
import com.example.internportfoliotask2.model.entity.postgre.User;
import com.example.internportfoliotask2.repo.postgre.UserRepo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static com.example.internportfoliotask2.constants.TokenConstants.EMAIL_KEY;
import static org.hibernate.query.sqm.tree.SqmNode.log;

@Service
@RequiredArgsConstructor
public class JwtTokenUtil {

    private final String SECRET_KEY = "secret";
    private final UserRepo userRepo;

//    @Value("${secret.key}")
//    private String SECRET_KEY;

    // Bu metod, tokenden userin adını cixarmaq ucun istifade olunur
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Bu metod, tokendan son istifade etmek tarixini cixarmaq ucun istifade olunur
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // Bu metod, tokenden belirli (claim) cixarmaq ucun istifade olunur
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Bu metod, tokenin içindeki butun telebleri cixarmaq ucun istifade olunur
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    // Bu metod, tokenin vaxtinin dolub dolmadiqini yoxlayir
    public Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Bu metod, userin detaylarina dayali olaraq token generate edir
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userName", userDetails.getUsername());
        return createToken(claims, userDetails.getUsername());
    }

    // Bu metod, verilen teleblere uyqun olaraq bir token oluşturur
    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // Token geçerliliği 10 saat
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    // Bu metod, tokenin geçerli olub olmadığını yoxlayir
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }
    public String extractTokenFromHeader(String header) {
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return null;
    }

    public Claims read(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(getSigningKey())
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception ex) {
            log.error("ActionLog error  " + ex.getMessage());
            throw new Bearer_Token("Bearer token");
        }
        if (claims != null) {
            String typeOfToken = claims.get("type", String.class);
            if ("ACCESS_TOKEN".equals(typeOfToken)) {
                return claims;
            }
        }
        return null;
    }

    public Long getId(String token) {
        if (read(token) != null) {
            return read(token).get("id", Long.class);
        } else {
            return null;
        }
    }
    public String getUserByEmail(String tokenValue) {
        String token = tokenValue.substring(7);
        return getEmail(token);
    }

    public User getUserById(String token) {
        token = token.substring(7);
        Long id = getId(token);
        log.info("Action id" + id);
        return userRepo.findUserById(id).orElseThrow();
    }

    public String getEmail(String token) {
        if (read(token) != null) {
            return read(token).get(EMAIL_KEY, String.class);
        } else {
            return null;
        }

    }
}
