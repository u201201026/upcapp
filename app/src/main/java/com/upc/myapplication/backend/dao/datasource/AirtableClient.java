package com.upc.myapplication.backend.dao.datasource;

import java.io.IOException;
import com.upc.myapplication.BuildConfig;
import org.apache.hc.client5.http.classic.methods.HttpDelete;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPatch;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.BasicHttpClientResponseHandler;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.io.entity.StringEntity;

public class AirtableClient {
    private final String apiKey;
    private final String databaseId;
    private final String url;
    private final String entityId;

    public AirtableClient(String entityId) throws IOException {
        this.url = BuildConfig.AIRTABLE_API_URL;
        this.apiKey = BuildConfig.AIRTABLE_API_KEY;
        this.databaseId = BuildConfig.AIRTABLE_BASE_ID;
        this.entityId = entityId;
    }

    public String post(String body) throws IOException {
        try(CloseableHttpClient httpClient = HttpClientBuilder.create().build()){
            HttpPost httpPost = new HttpPost(url + databaseId + "/" + entityId);
            httpPost.setHeader("Authorization", "Bearer " + apiKey);
            httpPost.setHeader("Content-Type", "application/json");

            httpPost.setEntity(new StringEntity(body));
            return httpClient.execute(httpPost, new BasicHttpClientResponseHandler());
        }
    }

    public String getById(String id) throws IOException {
        try(CloseableHttpClient httpClient = HttpClientBuilder.create().build()){
            String fullUrl = url + databaseId + "/" + entityId + "/" + id;
            HttpGet httpGet = new HttpGet(fullUrl);
            httpGet.setHeader("Authorization", "Bearer " + apiKey);

            return httpClient.execute(httpGet, new BasicHttpClientResponseHandler());
        }
    }

    public String searchBy(String encodedFilter, Integer maxRecords, String offset) throws IOException {
        try(CloseableHttpClient httpClient = HttpClientBuilder.create().build()){
            String fullUrl = url + databaseId + "/" + entityId;
            if(encodedFilter != null && !encodedFilter.isBlank()){
                fullUrl += "?filterByFormula=" + encodedFilter;
                if(maxRecords != null && maxRecords > 0){
                    fullUrl += "&maxRecords=" + maxRecords;
                }
                if(offset != null && !offset.isBlank()){
                    fullUrl += "&offset=" + offset;
                }
            }

            HttpGet httpGet = new HttpGet(fullUrl);
            httpGet.setHeader("Authorization", "Bearer " + apiKey);

            return httpClient.execute(httpGet, new BasicHttpClientResponseHandler());
        }
    }

    public void put(String id, String body) throws IOException {
        try(CloseableHttpClient httpClient = HttpClientBuilder.create().build()){
            HttpPatch httpPatch = new HttpPatch(url + databaseId + "/" + entityId + "/" + id);
            httpPatch.setHeader("Authorization", "Bearer " + apiKey);
            httpPatch.setHeader("Content-Type", "application/json");

            httpPatch.setEntity(new StringEntity(body));
            httpClient.execute(httpPatch, new BasicHttpClientResponseHandler());
        }
    }

    public void delete(String id) throws IOException {
        try(CloseableHttpClient httpClient = HttpClientBuilder.create().build()){
            HttpDelete httpDelete = new HttpDelete(url + databaseId + "/" + entityId + "/" + id);
            httpDelete.setHeader("Authorization", "Bearer " + apiKey);
            httpClient.execute(httpDelete, new BasicHttpClientResponseHandler());
        }
    }
}
