package pl.muskul.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.HandlerInterceptor;
import pl.muskul.service.authentication.AuthenticationConfig;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Date;


public class AuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    Environment env;
    private AuthenticationConfig config;

    public AuthenticationInterceptor(AuthenticationConfig config) {
        this.config = config;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            String token = getToken(request);
            DecodedJWT decodedToken = decodeToken(token);
            if (isTokenExpired(decodedToken)) {
                System.err.println("token expired");
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                return false;
            }

            request.getSession().setAttribute("userId", decodedToken.getSubject());
        } catch (NullPointerException | JWTVerificationException e) {
            System.err.println(e);
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        return true;
    }

    private String getToken(HttpServletRequest request) {
        final String TOKEN_HEADER = "token";
        return Arrays.stream(config.getEnv().getActiveProfiles()).anyMatch(profile -> profile.equals("dev")) ? config.getTestToken() : request.getHeader(TOKEN_HEADER);
    }

    private DecodedJWT decodeToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(config.getSecret());
        JWTVerifier verifier = JWT.require(algorithm)
                .build();
        return verifier.verify(token);
    }

    private boolean isTokenExpired(DecodedJWT decodedToken) {
        Date expiresAt = decodedToken.getExpiresAt();
        return expiresAt.before(new Date());
    }
}
