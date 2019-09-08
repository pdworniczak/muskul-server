package pl.muskul.service.authentication;

import org.springframework.core.env.Environment;

public class AuthenticationConfig {

    private String tokenSecret;
    private String testToken;
    private Environment env;

    public AuthenticationConfig setToken(String testToken) {
        this.testToken = testToken;
        return this;
    }

    public AuthenticationConfig setSecret(String tokenSecret) {
        this.tokenSecret = tokenSecret;
        return this;
    }

    public AuthenticationConfig setToken(Environment env) {
        this.env = env;
        return this;
    }

    public String getTokenSecret() {
        return tokenSecret;
    }

    public String getTestToken() {
        return testToken;
    }

    public Environment getEnv() {
        return env;
    }
}
