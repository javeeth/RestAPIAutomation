package com.atlassian.api.property;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:envs/${env}.properties"
})

public interface Environment extends Config {
    String endpoint();
    String timeout();
}