package com.example;

import java.io.IOException;
import java.util.UUID;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GetExample {
    private final OkHttpClient client = new OkHttpClient();

    String run(String url) throws IOException {

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
    public static void main(String[] args) throws IOException {
        GetExample example = new GetExample();
        String response = example.run("http://localhost:8080/demoServlet?a="+ UUID.randomUUID());
        System.out.println(response);
    }
}