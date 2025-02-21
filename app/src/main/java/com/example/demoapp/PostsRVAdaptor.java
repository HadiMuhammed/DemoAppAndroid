package com.example.demoapp;

import android.content.Context;
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

    private Context context;
    private ArrayList<UserPostsModel> postsModalArrayList;

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
        holder.userNameTV.setText(post.getUserName());
        holder.descriptionTV.setText(post.getDescription());
        Picasso.get().load(post.getImgUrl()).into(holder.postIV);
        Picasso.get().load(post.getImgUrl()).into(holder.userIV); // Assuming both images use the same URL
    }

    @Override
    public int getItemCount() {
        return postsModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // creating variables for our text views and image views.
        private final TextView userNameTV;
        private final TextView descriptionTV;
        private final ImageView postIV;
        private final ImageView userIV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our views.
            postIV = itemView.findViewById(R.id.idPostImage);
            descriptionTV = itemView.findViewById(R.id.idDescription);
            userIV = itemView.findViewById(R.id.idUserImage);
            userNameTV = itemView.findViewById(R.id.idUserName);

            //CardView itemClick listener
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    // item clicked
                    int a = getAdapterPosition();
                    UserPostsModel post = postsModalArrayList.get(a);
                    Toast.makeText(context, "#" + post.getUserName(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
