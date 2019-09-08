package pl.muskul.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;
import pl.muskul.service.authentication.AuthenticationConfig;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Date;


public class AuthenticateInterceptor implements HandlerInterceptor {

    @Autowired
    Environment env;
    private AuthenticationConfig config;

    public AuthenticateInterceptor(AuthenticationConfig config) {
        this.config = config;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            String token = Arrays.stream(config.getEnv().getActiveProfiles()).anyMatch(profile -> profile.equals("dev")) ? config.getTestToken() : request.getHeader("token");
            Algorithm algorithm = Algorithm.HMAC256(config.getTokenSecret());
            JWTVerifier verifier = JWT.require(algorithm)
                    .build();
            DecodedJWT decodedToken = verifier.verify(token);

            Date expiresAt = decodedToken.getExpiresAt();

            if (expiresAt.before(new Date())) {
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
}
