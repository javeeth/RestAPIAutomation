package com.atlassian.api.property;

public class SystemProperties {
    public static final String ENV = System.getProperty("env", "staging");
}
