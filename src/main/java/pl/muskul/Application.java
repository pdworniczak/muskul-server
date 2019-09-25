package pl.muskul;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pl.muskul.interceptor.AuthenticationInterceptor;
import pl.muskul.service.authentication.AuthenticationConfig;

import java.util.Optional;

@Configuration
@SpringBootApplication
public class Application implements WebMvcConfigurer {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.test-token:#{null}}")
    private Optional<String> testToken;

    @Autowired
    private Environment env;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthenticationInterceptor(prepareAuthenticationConfiguration()));
    }

    private AuthenticationConfig prepareAuthenticationConfiguration() {
        AuthenticationConfig authConfig = new AuthenticationConfig()
                .setSecret(secret)
                .setEnv(env);
        if (testToken.isPresent()) {
            authConfig.setTestToken(testToken.get());
        }

        return authConfig;
    }
}