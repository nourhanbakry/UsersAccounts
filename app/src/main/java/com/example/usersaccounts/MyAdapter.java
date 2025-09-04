package com.example.usersaccounts;

import static com.example.usersaccounts.R.layout.user_profile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.zip.Inflater;

public class MyAdapter extends BaseAdapter {
    Context context;
    ArrayList<UserInfoModel> userInfoModels;

    public MyAdapter(Context context, ArrayList<UserInfoModel> userInfoModels) {
        this.context = context;
        this.userInfoModels = userInfoModels;
    }

    @Override
    public int getCount() {
        return userInfoModels.size();
    }

    @Override
    public Object getItem(int position) {
        return userInfoModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    public void addItem(UserInfoModel item){userInfoModels.add(item);}

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(user_profile, null, false);
        }
        ImageView image = convertView.findViewById(R.id.final_user_profile_photo);
        TextView name = convertView.findViewById(R.id.user_name_value);
        TextView bio = convertView.findViewById(R.id.user_bio_value);
        TextView followers = convertView.findViewById(R.id.user_followers_value);
        TextView followings = convertView.findViewById(R.id.user_following_value);
        TextView posts = convertView.findViewById(R.id.user_posts_value);
        TextView date = convertView.findViewById(R.id.user_date_value);
        Button followBTN = convertView.findViewById(R.id.follow_btn);
        UserInfoModel currentItem = (UserInfoModel) getItem(position);
        image.setImageBitmap(currentItem.image);
        name.setText(currentItem.name);
        bio.setText(currentItem.bio);
        followers.setText(currentItem.followers);
        followings.setText(currentItem.followings);
        posts.setText(currentItem.posts);
        date.setText(currentItem.date);
        followBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (followBTN.getText() == "Follow"){
                    followBTN.setText("Followed");
                } else{
                    followBTN.setText("Follow");
                }
            }
        });

        return convertView;
    }
}
