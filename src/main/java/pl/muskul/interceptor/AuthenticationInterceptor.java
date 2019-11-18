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

            if (decodedToken.getSubject() != null) {
                request.getSession().setAttribute("userId", decodedToken.getSubject());
            } else throw new Exception("Missing token subject");
        } catch (NullPointerException e) {
            System.err.println("Token is missing:");
            System.err.println(e);
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        } catch (JWTVerificationException e) {
            System.err.println("Can't decode token:");
            System.err.println(e);
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        return true;
    }

    private String getToken(HttpServletRequest request) {
        final String TOKEN_HEADER = "token";
        return config.isTestTokenPresent() ? config.getTestToken() : request.getHeader(TOKEN_HEADER);
    }

    private DecodedJWT decodeToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(config.getSecret());
        JWTVerifier verifier = JWT.require(algorithm)
                .build();
        return verifier.verify(token);
    }

    private boolean isTokenExpired(DecodedJWT decodedToken) {
        Date expiresAt = decodedToken.getExpiresAt();
        return expiresAt == null ? false : expiresAt.before(new Date());
    }
}
