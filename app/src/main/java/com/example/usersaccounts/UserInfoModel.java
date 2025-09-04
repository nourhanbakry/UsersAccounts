package com.example.usersaccounts;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public  class UserInfoModel  implements  Parcelable{
    Bitmap image;
    String name;
    String bio;
    String date;
    String posts;
    String followers;
    String followings;


    protected UserInfoModel(Parcel in) {
        image = in.readParcelable(Bitmap.class.getClassLoader());
        name = in.readString();
        bio = in.readString();
        date = in.readString();
        posts = in.readString();
        followers = in.readString();
        followings = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(image, flags);
        dest.writeString(name);
        dest.writeString(bio);
        dest.writeString(date);
        dest.writeString(posts);
        dest.writeString(followers);
        dest.writeString(followings);
    }

    @Override
    public int describeContents() {return 0;}

    public static final Creator<UserInfoModel> CREATOR = new Creator<UserInfoModel>() {
        @Override
        public UserInfoModel createFromParcel(Parcel in) {
            return new UserInfoModel(in);
        }

        @Override
        public UserInfoModel[] newArray(int size) {
            return new UserInfoModel[size];
        }
    };

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPosts() {
        return posts;
    }

    public void setPosts(String posts) {
        this.posts = posts;
    }

    public String getFollowers() {
        return followers;
    }

    public void setFollowers(String followers) {
        this.followers = followers;
    }

    public String getFollowings() {
        return followings;
    }

    public void setFollowings(String followings) {
        this.followings = followings;
    }

    public UserInfoModel(String followers, Bitmap image, String name, String bio, String date, String posts, String followings) {
        this.followers = followers;
        this.image = image;
        this.name = name;
        this.bio = bio;
        this.date = date;
        this.posts = posts;
        this.followings = followings;
    }
}
