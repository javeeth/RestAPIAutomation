package com.atlassian.api.modules;

import com.atlassian.api.client.IRestClient;
import com.atlassian.api.client.restassured.RestAssuredClient;
import com.atlassian.api.client.restassured.RestAssuredClientBase;
import com.atlassian.api.client.retrofit.RetrofitClientBase;
import com.atlassian.api.exceptions.InvalidEnvironmentException;
import com.atlassian.api.property.Environment;
import com.atlassian.api.property.SystemProperties;
import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.name.Names;
import org.aeonbits.owner.ConfigFactory;

public class RestClientModule extends AbstractModule {

    @Override
    public void configure(){

       Environment testEnvironment;
       ConfigFactory.setProperty("env", SystemProperties.ENV);
       testEnvironment = ConfigFactory.create(Environment.class);

       bind(Environment.class).annotatedWith(Names.named("environment")).toInstance(testEnvironment);
       bind(IRestClient.class).to(RetrofitClientBase.class);
       //bind(IRestClient.class).to(RestAssuredClient.class);
    }
}
