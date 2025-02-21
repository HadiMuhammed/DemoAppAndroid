package com.example.demoapp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Call;
import okhttp3.Callback;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PostsRVAdaptor postsRVAdaptor;
    private ArrayList<UserPostsModel> userPostsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.idRVPosts);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        userPostsList = new ArrayList<>();
        postsRVAdaptor = new PostsRVAdaptor(getApplicationContext(), userPostsList);
        recyclerView.setAdapter(postsRVAdaptor);

        // API for getting User Posts
        String POSTS_URL = "https://api.unsplash.com/photos/?client_id=8634366274bd23efb9b023fb9b2c6502e67f7dd5d6a7962b3b49fbee170940f8";

        // Make API call using OkHttpClient
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(POSTS_URL)
                .build();

        client.newCall(request).enqueue(new Callback() {
            Handler handler = new Handler(Looper.getMainLooper());

            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("NetworkError2", e.toString());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "Network error", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseBody = response.body().string();
                    Log.e("RealData", responseBody);
                    try {
                        JSONArray data = new JSONArray(responseBody);
                        FillData(data);
                    } catch (Exception e) {
                        Log.e("NetworkError1", e.toString());
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this, "Network error", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                } else {
                    Log.e("NetworkError1", response.toString());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "Network error", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
    public void FillData(JSONArray data) {
        userPostsList.clear();
        for (int i = 0; i < data.length(); i++) {
            try {
                JSONObject post = data.getJSONObject(i);
                String imgUrl = post.getJSONObject("urls").getString("small");
                String description = post.has("description") ? post.getString("description") : "No description";
                String userName = post.getJSONObject("user").getString("username");
                String profilePic = post.getJSONObject("user").getJSONObject("profile_image").getString("small");
                String userBio = post.getJSONObject("user").has("bio") ? post.getJSONObject("user").getString("bio") : "No bio available";
                String userLocation = post.getJSONObject("user").has("location") ? post.getJSONObject("user").getString("location") : "No location available";
                userPostsList.add(new UserPostsModel(imgUrl, description, userName, profilePic, userBio, userLocation));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        runOnUiThread(() -> postsRVAdaptor.notifyDataSetChanged());
    }



}
