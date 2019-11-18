package pl.muskul.service.authentication;

import org.springframework.core.env.Environment;

public class AuthenticationConfig {

    private String secret;
    private String testToken;
    private Environment env;



    public AuthenticationConfig setSecret(String secret) {
        this.secret = secret;
        return this;
    }

    public String getSecret() {
        return secret;
    }

    public AuthenticationConfig setEnv(Environment env) {
        this.env = env;
        return this;
    }

    public Environment getEnv() {
        return env;
    }

    public AuthenticationConfig setTestToken(String testToken) {
        this.testToken = testToken;
        return this;
    }

    public String getTestToken() {
        return testToken;
    }

    public boolean isTestTokenPresent() {
        return this.testToken != null;
    }
}
