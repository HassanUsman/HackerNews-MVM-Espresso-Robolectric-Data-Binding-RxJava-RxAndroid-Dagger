package com.hackernews.reader.data.news;

import android.os.Parcel;
import android.os.Parcelable;

import com.hackernews.reader.data.Item;

/**
 * Created by HassanUsman on 11/11/2017.
 *
 */

public class NewsItem extends Item implements Parcelable {
    public String by;
    public int descendants;
    public int id;
    public int[] kids;
    public int score;
    public long time;
    public String title;
    public String type;
    public String url;

    public NewsItem(){
    }

    private NewsItem(Parcel in) {
        by = in.readString();
        descendants = in.readInt();
        id = in.readInt();
        kids = in.createIntArray();
        score = in.readInt();
        time = in.readLong();
        title = in.readString();
        type = in.readString();
        url = in.readString();
    }

    public static final Creator<NewsItem> CREATOR = new Creator<NewsItem>() {
        @Override
        public NewsItem createFromParcel(Parcel in) {
            return new NewsItem(in);
        }

        @Override
        public NewsItem[] newArray(int size) {
            return new NewsItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(by);
        parcel.writeInt(descendants);
        parcel.writeInt(id);
        parcel.writeIntArray(kids);
        parcel.writeInt(score);
        parcel.writeLong(time);
        parcel.writeString(title);
        parcel.writeString(type);
        parcel.writeString(url);
    }
}
