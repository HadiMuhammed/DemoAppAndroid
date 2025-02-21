package com.example.demoapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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

        // API for getting User Posts
        String POSTS_URL = "https://api.unsplash.com/photos/?client_id=8634366274bd23efb9b023fb9b2c6502e67f7dd5d6a7962b3b49fbee170940f8";
        Log.e("Data", "API call failed: 1234");
        // Make API call using OkHttpClient
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(POSTS_URL)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(MainActivity.this, "Network error", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseBody = response.body().string();
                    try {
                        JsonArray data = new JsonParser().parse(responseBody).getAsJsonArray();
                        FillData(data);
                    } catch (Exception e) {
                        // Handle parsing error
                        Toast.makeText(MainActivity.this, "Network error", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Network error", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public void FillData(JsonArray data) {
        userPostsList.clear();
        for (int i = 0; i < data.size(); i++) {
            JsonObject post = data.get(i).getAsJsonObject();
            String imgUrl = post.get("urls").getAsJsonObject().get("small").getAsString();
            String description = post.has("description") ? post.get("description").getAsString() : "No description";
            String userName = post.get("user").getAsJsonObject().get("username").getAsString();
            String profilePic = post.get("user").getAsJsonObject().get("profile_image").getAsJsonObject().get("small").getAsString();
            userPostsList.add(new UserPostsModel(imgUrl, description, userName, profilePic));
        }
        runOnUiThread(() -> postsRVAdaptor.notifyDataSetChanged());
    }

}