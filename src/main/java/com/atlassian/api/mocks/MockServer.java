package com.atlassian.api.mocks;

import com.atlassian.api.dispatcher.EmployeeDispatcher;
import com.atlassian.api.property.Environment;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import okhttp3.mockwebserver.MockWebServer;

import java.net.URI;
import java.net.URISyntaxException;

public class MockServer{

    @Inject
    EmployeeDispatcher employeeDispatcher;

    @Inject @Named("environment")
    Environment testEnvironment;

    public MockWebServer build(){
        String host;
        MockWebServer mockWebserver = new MockWebServer();
        try{
            mockWebserver.start(getPortNumber());
            mockWebserver.setDispatcher(employeeDispatcher.get());

            host = mockWebserver.getHostName();
        }
        catch (Exception e){

        }
        return mockWebserver;

    }

    private int getPortNumber() throws URISyntaxException {
        URI uri = new URI(testEnvironment.endpoint());
        return uri.getPort();
    }




}
