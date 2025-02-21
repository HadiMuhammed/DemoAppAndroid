package com.example.demoapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PostsRVAdaptor extends RecyclerView.Adapter<PostsRVAdaptor.ViewHolder> {

    private final Context context;
    private final ArrayList<UserPostsModel> postsModalArrayList;

    // creating a constructor class.
    public PostsRVAdaptor(Context context, ArrayList<UserPostsModel> postsModalArrayList) {
        this.context = context;
        this.postsModalArrayList = postsModalArrayList;
    }

    @NonNull
    @Override
    public PostsRVAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // passing our layout file for displaying our card item
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.posts_rv_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PostsRVAdaptor.ViewHolder holder, int position) {
        // setting data to our text views from our modal class.
        UserPostsModel post = postsModalArrayList.get(position);
        holder.userName.setText(post.getUserName() == null ? "No username found" : post.getUserName());
        holder.description.setText(post.getDescription() == null ? "No description found" : post.getDescription());
        Picasso.get().load(post.getImgUrl()).into(holder.post);
        Picasso.get().load(post.getProfilePic()).into(holder.userImage); // Assuming both images use the same URL
    }

    @Override
    public int getItemCount() {
        return postsModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // creating variables for our text views and image views.
        private final TextView userName;
        private final TextView description;
        private final ImageView post;
        private final ImageView userImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our views.
            post = itemView.findViewById(R.id.idPostImage);
            description = itemView.findViewById(R.id.idDescription);
            userImage = itemView.findViewById(R.id.idUserImage);
            userName = itemView.findViewById(R.id.idUserName);

            // User image click
            userImage.setOnClickListener(v -> showProfile(getAdapterPosition()));

            // Username click
            userName.setOnClickListener(v -> showProfile(getAdapterPosition()));

            post.setOnClickListener(view -> showItem(getAdapterPosition()));

            description.setOnClickListener(view -> showItem(getAdapterPosition()));
        }
    }

    public void showProfile(int pos) {
        if (pos != RecyclerView.NO_POSITION) {
            try {
                UserPostsModel post = postsModalArrayList.get(pos);
                Intent intent = new Intent(context.getApplicationContext(), UserProfileActivity.class);
                intent.putExtra("userName", post.getUserName());
                intent.putExtra("bio", post.getUserBio());
                intent.putExtra("location", post.getUserLocation());
                intent.putExtra("imgUrl", post.getProfilePic());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.getApplicationContext().startActivity(intent);
            }
            catch (Exception e) {
                Toast.makeText(context.getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void showItem(int pos) {
        if(pos != RecyclerView.NO_POSITION) {
            try{
                UserPostsModel post = postsModalArrayList.get(pos);
                Intent intent = new Intent(context.getApplicationContext(), UserPostActivity.class);
                intent.putExtra("description", post.getDescription());
                intent.putExtra("imgUrl", post.getImgUrl());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.getApplicationContext().startActivity(intent);
            } catch (Exception e) {
                Toast.makeText(context.getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
