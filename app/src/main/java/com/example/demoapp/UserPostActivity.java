package com.example.demoapp;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.squareup.picasso.Picasso;

public class UserPostActivity extends AppCompatActivity {

    ImageView postImage;
    TextView postDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_post);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        postImage = findViewById(R.id.id_post_image);
        postDescription = findViewById(R.id.id_post_description);

        String description = getIntent().getStringExtra("description");
        String imgUrl = getIntent().getStringExtra("imgUrl");

        assert description != null;
        postDescription.setText(description.equals("null") ? "No Description" : description);
        Picasso.get().load(imgUrl).into(postImage);
    }
}