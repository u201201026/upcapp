package com.upc.myapplication.backend.dao.datasource;

import java.io.IOException;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.BasicHttpClientResponseHandler;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.io.entity.StringEntity;

public class ApiClient {
    public static String post(String url, String body) throws IOException {
        try(CloseableHttpClient httpClient = HttpClientBuilder.create().build()){
            HttpPost httpPost = new HttpPost(url);
            httpPost.setHeader("Content-Type", "application/json");

            httpPost.setEntity(new StringEntity(body));
            return httpClient.execute(httpPost, new BasicHttpClientResponseHandler());
        }
    }

    public static String get(String url) throws IOException {
        try(CloseableHttpClient httpClient = HttpClientBuilder.create().build()){
            HttpGet httpGet = new HttpGet(url);
            return httpClient.execute(httpGet, new BasicHttpClientResponseHandler());
        }
    }
}
