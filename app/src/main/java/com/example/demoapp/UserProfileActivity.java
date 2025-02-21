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

import java.util.Objects;

public class UserProfileActivity extends AppCompatActivity {

    TextView userName;
    TextView userBio;
    TextView userLocation;
    ImageView userImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Objects.requireNonNull(getSupportActionBar()).setTitle("Profile");
        userName = findViewById(R.id.id_user_name);
        userBio = findViewById(R.id.id_user_bio);
        userLocation = findViewById(R.id.id_user_location);
        userImage = findViewById(R.id.id_profile_pic);

        String username = getIntent().getStringExtra("userName");
        String bio = getIntent().getStringExtra("bio");
        String location = getIntent().getStringExtra("location");
        String imgUrl = getIntent().getStringExtra("imgUrl");

        assert username != null;
        userName.setText(username.equals("null") ? "No username found" : username);
        assert bio != null;
        userBio.setText(bio.equals("null") ?  "No bio found" : bio);
        assert location != null;
        userLocation.setText(location.equals("null") ? "No location data found" : location);
        Picasso.get().load(imgUrl).into(userImage);

    }
}