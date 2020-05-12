package com.atlassian.api.modules;

import com.atlassian.api.client.restassured.BaseClient;
import com.atlassian.api.client.restassured.IRestClient;
import com.google.inject.AbstractModule;

public class RestClientModule extends AbstractModule {

    @Override
    public void configure(){
       bind(IRestClient.class).to(BaseClient.class);
    }
}
