package com.atlassian.api.client;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class ApiClient {

    public HttpResponse getHttpResponse(String endpoint){
        try{
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpGet getRequest = new HttpGet(endpoint);
            return httpClient.execute(getRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public HttpResponse postHttpResponse(String body, String endpoint){
        try{
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpPost httpPost = new HttpPost(endpoint);
            StringEntity entity = new StringEntity(body,
                    ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);
            return httpClient.execute(httpPost);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getRequestBody(HttpResponse httpResponse) throws IOException {
        String content =  EntityUtils.toString(httpResponse.getEntity());
        return content;
    }

    public JsonElement getJsonElement(String responseBody){
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(responseBody);
        return element;
    }

}
