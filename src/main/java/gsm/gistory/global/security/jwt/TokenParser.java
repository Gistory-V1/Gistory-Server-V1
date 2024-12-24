package gsm.gistory.global.security.jwt;

import gsm.gistory.global.security.auth.CustomUserDetailsService;
import gsm.gistory.global.security.jwt.properties.JwtEnvironment;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import static gsm.gistory.global.security.jwt.TokenGenerator.getTokenBody;

@Component
@RequiredArgsConstructor
public class TokenParser {

    private final JwtEnvironment jwtEnv;

    private final CustomUserDetailsService customUserDetailsService;

    public UsernamePasswordAuthenticationToken authenticate(String accessToken) {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(getAccessTokenSubject(accessToken));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    private String getAccessTokenSubject(String accessToken) {
        return getTokenBody(accessToken, Keys.hmacShaKeyFor(jwtEnv.accessSecret().getBytes())).getSubject();
    }

    public String getEmailFromToken(String accessToken) {
        return getAccessTokenSubject(accessToken);
    }

    public Long getExpirationFromToken(String accessToken) {
        Claims claims = getTokenBody(accessToken, Keys.hmacShaKeyFor(jwtEnv.accessSecret().getBytes()));
        return claims.getExpiration().getTime();
    }

}
