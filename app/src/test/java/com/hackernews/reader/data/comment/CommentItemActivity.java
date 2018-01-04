package com.hackernews.reader.data.comment;

import android.os.Parcel;
import android.support.v7.app.AppCompatActivity;


public class CommentItemActivity extends AppCompatActivity {

    public CommentItem getFromParcel(CommentItem parcelable) {
        Parcel parcel = Parcel.obtain();
        parcelable.writeToParcel(parcel,parcelable.describeContents());
        parcel.setDataPosition(0);
        return CommentItem.CREATOR.createFromParcel(parcel);
    }

}
