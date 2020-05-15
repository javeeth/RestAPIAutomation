package com.atlassian.api.client.retrofit;

import com.atlassian.api.exceptions.InvalidEnvironmentException;
import com.atlassian.api.property.Environment;
import com.atlassian.api.property.SystemProperties;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import io.qameta.allure.okhttp3.AllureOkHttp3;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockWebServer;
import org.aeonbits.owner.ConfigFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {

    @Inject
    @Named("environment")
    Environment testEnvironment;

    private Retrofit build(){

        OkHttpClient okHttpClient = new OkHttpClient()
                .newBuilder()
                .addInterceptor(new AllureOkHttp3())
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HttpUrl.get(testEnvironment.endpoint()))
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

    public Retrofit getRetrofit() {
        return build();
    }
}
