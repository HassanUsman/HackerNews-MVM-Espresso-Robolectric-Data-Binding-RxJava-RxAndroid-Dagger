package com.hackernews.reader.data.news;

import com.hackernews.reader.data.BaseModel;

import java.util.ArrayList;

/**
 * Created by HassanUsman on 29/11/2017.
 */

@SuppressWarnings("ALL")
public interface NewsModel extends BaseModel {

    interface GetIdsCallback{
        void onResponse(ArrayList<NewsItem> newsItems);
        void onErrorResponse(Throwable throwable);
    }

    interface GetNewsItemCallback{
        void onResponse(NewsItem newsItem);
        void onErrorResponse(Throwable throwable);
    }

    ArrayList<NewsItem> getImmutableList();
    void fill(ArrayList<NewsItem> value);
    void getItems(GetNewsItemCallback callback);
    void cancel();
}
