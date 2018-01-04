package com.hackernews.reader.data.news;

import android.os.Parcel;
import android.support.v7.app.AppCompatActivity;

import com.hackernews.reader.data.news.NewsItem;


public class NewsItemActivity extends AppCompatActivity {


    public NewsItem getFromParcel(NewsItem parcelable) {
        Parcel parcel = Parcel.obtain();
        parcelable.writeToParcel(parcel,parcelable.describeContents());
        parcel.setDataPosition(0);
       return NewsItem.CREATOR.createFromParcel(parcel);
    }
}
