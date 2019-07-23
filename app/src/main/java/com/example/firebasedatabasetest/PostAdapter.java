package com.example.firebasedatabasetest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyHolder>{
    List<Post> posts;

    public PostAdapter(List<Post> posts) {
        this.posts=posts;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_list,parent,false);

        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }


    public void onBindViewHolder(MyHolder holder, int position) {
        Post post = posts.get(position);
        holder.titleTxt.setText(post.getTitle());
        holder.addressTxt.setText(post.getAddress());
        holder.phoneTxt.setText(post.getMobileNumber());
        holder.rentTxt.setText(post.getRent());
        holder.availableDateTxt.setText(post.getAvailableDate());
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }


    class MyHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.post_title)
        TextView titleTxt;
        @BindView(R.id.post_address)
        TextView addressTxt;
        @BindView(R.id.post_phone)
        TextView phoneTxt;
        @BindView(R.id.post_rent)
        TextView rentTxt;
        @BindView(R.id.post_available_date)
        TextView availableDateTxt;


        public MyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

        }
    }

}
