package com.example;

import java.io.IOException;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.utils.Color;

import com.utils.JsonFormat;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;


public final class AsyncGetExample {
    private static final OkHttpClient client = new OkHttpClient();

    public void run(String url, String token) throws Exception {
        Request request = new Request.Builder()
                .header("Authorization", "Bearer " + token)
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override public void onResponse(Call call, Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    if (!response.isSuccessful()) {
                        throw new IOException("Unexpected code " + response);
                    }

                    Headers responseHeaders = response.headers();

/*                    for (int i = 0, size = responseHeaders.size(); i < size; i++) {
                        System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
                    }*/

                    // 请求头是否为 json 类型
                    String contentType = responseHeaders.get("Content-Type");

                    if(contentType.contains("json")) { // 格式化 json
                        JSONObject jsonObject = JSON.parseObject(responseBody.string());
/*                        Map map = jsonObject.toJavaObject(Map.class);
                        System.out.println(map);*/
                        JSONObject jsonData = jsonObject.getJSONObject("data");
                        JSONObject equipment = jsonData.getJSONObject("equipment");
                        JSONArray video = equipment.getJSONArray("video");

                        //遍历JSONArray
                        for (Object object : video) {
/*                            JSONObject jsonObjectone = (JSONObject) object;
                            String vid = jsonObjectone.getString("vid");
                            String name = jsonObjectone.getString("name");
                            Integer studentAge = jsonObjectone.getInteger("studentAge");
                            System.out.println("vid:  " + vid + "   videoName:  " + name);*/

                            JSONObject jsonObjectone = (JSONObject) object;
                            Map<String,Object> one = jsonObjectone.toJavaObject(Map.class);
                            for (Map.Entry<String, Object> entry : one.entrySet()) {
                                System.out.println("Key= " + entry.getKey() + " Value= " + entry.getValue());
                            }

                        }

                        System.out.println(JsonFormat.formatJson(jsonObject.toString()));
                    }else{
                        System.out.println(responseBody.string());
                    }

                }
                System.exit(0);
            }
        });
    }

    public static void main(String... args) throws Exception {
        // Async Get Requests
        System.out.println(Color.BLACK_BOLD + "Start Running..." + Color.RESET);

//        String url = "http://localhost:8080/demoServlet";
        String url = "http://192.168.5.110:4000/api/player/no/10026/videos";
        new AsyncGetExample().run(url, "49fe83fe68ca567287f33f77403f572a");

        System.out.println(Color.RED_BOLD + "Finished..." + Color.RESET);
    }
}
