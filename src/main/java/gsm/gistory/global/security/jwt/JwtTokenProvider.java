package gsm.gistory.global.security.jwt;

import gsm.gistory.global.security.jwt.exception.CustomException;
import gsm.gistory.global.security.jwt.exception.ErrorCode;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final SecretKey secretKey = Keys.hmacShaKeyFor(
            getKeyBytes(System.getenv("JWT_SECRET_KEY"))
    );

    private final long accessTokenValidity = Long.parseLong(System.getenv("JWT_ACCESS_TOKEN_VALIDITY"));
    private final long refreshTokenValidity = Long.parseLong(System.getenv("JWT_REFRESH_TOKEN_VALIDITY"));

    private static byte[] getKeyBytes(String key) {
        if (key == null || key.length() < 32) {
            throw new IllegalArgumentException("JWT secret key must be at least 32 characters long.");
        }
        return key.getBytes();
    }

    public String generateAccessToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + accessTokenValidity))
                .signWith(secretKey)
                .compact();
    }

    public String generateRefreshToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + refreshTokenValidity))
                .signWith(secretKey)
                .compact();
    }

    public String getAccessTokenExpiration(String token) {
        Date expiration = extractExpiration(token);
        return String.valueOf(expiration.getTime());
    }

    public String getRefreshTokenExpiration(String token) {
        Date expiration = extractExpiration(token);
        return String.valueOf(expiration.getTime());
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);
            System.out.println("토큰 검증 성공: " + token);
            return true;
        } catch (ExpiredJwtException e) {
            System.out.println("토큰 만료: " + e.getMessage());
            throw new CustomException(ErrorCode.TOKEN_EXPIRED, "토큰이 만료되었습니다.");
        } catch (JwtException e) {
            System.out.println("유효하지 않은 토큰: " + e.getMessage());
            throw new CustomException(ErrorCode.INVALID_TOKEN, "유효하지 않은 토큰입니다.");
        }
    }

    public String getEmailFromToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (ExpiredJwtException e) {
            throw new CustomException(ErrorCode.TOKEN_EXPIRED, "토큰이 만료되었습니다.");
        } catch (JwtException e) {
            throw new CustomException(ErrorCode.INVALID_TOKEN, "유효하지 않은 토큰입니다.");
        }
    }

    private Date extractExpiration(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getExpiration();
        } catch (JwtException e) {
            throw new CustomException(ErrorCode.INVALID_TOKEN, "유효하지 않은 토큰입니다.");
        }
    }
}
