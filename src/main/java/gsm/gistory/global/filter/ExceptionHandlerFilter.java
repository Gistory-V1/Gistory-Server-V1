package gsm.gistory.global.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import gsm.gistory.global.error.ErrorResponse;
import gsm.gistory.global.error.GlobalException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExceptionHandlerFilter {

    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException e) {
            exceptionToResponse("만료된 JWT 토큰 입니다.", HttpStatus.UNAUTHORIZED, response);
        } catch (JwtException e) {
            exceptionToResponse("유효하지 않은 JWT 토큰 입니다.", HttpStatus.UNAUTHORIZED, response);
        } catch (GlobalException e) {
            exceptionToResponse(e.getMessage(), e.getHttpStatus(), response);
        }
    }

    private void exceptionToResponse(String message, HttpStatus status, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setStatus(status.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(objectMapper.writeValueAsString(
                new ErrorResponse(message)
        ));

    }
}