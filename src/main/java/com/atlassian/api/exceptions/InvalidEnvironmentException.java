package com.atlassian.api.exceptions;

public class InvalidEnvironmentException extends RuntimeException {
    public InvalidEnvironmentException(String env) {
        super(String.format("Cannot load env %s. Supported envs are qa", env));
    }
}
