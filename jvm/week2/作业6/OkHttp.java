package http;

import okhttp3.*;

import java.io.IOException;

/**
 * @author baiyumei
 * @title: OkHttpClient
 * @projectName java-journey
 * @description: TODO
 * @date 2021/11/148:07 下午
 */
public class OkHttp {
    OkHttpClient client = new OkHttpClient  ();
    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");



    String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }


    String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public static void main(String[] args) throws IOException {
        OkHttp okHttp= new OkHttp();
        System.out.println(okHttp.run("http://localhost:8801"));
    }
}
