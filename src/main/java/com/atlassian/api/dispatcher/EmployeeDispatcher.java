package com.atlassian.api.dispatcher;

import com.google.inject.Provider;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.RecordedRequest;
import org.jetbrains.annotations.NotNull;

public class EmployeeDispatcher implements Provider<Dispatcher> {

    @Override
    public Dispatcher get() {
        return new Dispatcher() {
            @NotNull
            @Override
            public MockResponse dispatch(@NotNull RecordedRequest recordedRequest) throws InterruptedException {
                switch(recordedRequest.getPath()) {
                    case "/api/v1/employees" :
                        return new MockResponse().setResponseCode(200).setBody("{\"status\": \"success\"," +
                                "    \"data\": [" +
                                "        {" +
                                "            \"id\": \"1\"," +
                                "            \"employee_name\": \"Tiger Nixon\"," +
                                "            \"employee_salary\": \"320800\"," +
                                "            \"employee_age\": \"61\"," +
                                "            \"profile_image\": \"\"" +
                                "        }" +
                                "]" +
                                "}");
                }
                return new MockResponse().setResponseCode(404);
            }
        };
    }


}
