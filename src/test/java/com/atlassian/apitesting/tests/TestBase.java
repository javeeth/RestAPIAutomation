package com.atlassian.apitesting.tests;

import com.atlassian.api.mocks.MockServer;
import com.atlassian.api.modules.RestClientModule;
import com.atlassian.api.property.SystemProperties;
import com.google.inject.Inject;
import okhttp3.mockwebserver.MockWebServer;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Guice;

import java.io.IOException;

public class TestBase {

    @Inject
    MockServer mockServer;
    MockWebServer mockWebServer;

    @BeforeSuite
    void createRetrofitInstance() {
        if(SystemProperties.ENV.equals("mock")){
            mockWebServer = mockServer.build();
        }
    }

    @AfterSuite
    void tearDown() throws IOException {
        if(mockWebServer!=null){
            mockWebServer.shutdown();
        }
    }
}
