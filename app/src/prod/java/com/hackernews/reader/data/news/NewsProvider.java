package com.hackernews.reader.data.news;

import com.hackernews.reader.data.HttpClient;

/**
 * Created by HassanUsman on 29/11/2017.
 */

public class NewsProvider {

    private static NewsData newsData;

    public static NewsData getInstance() {
        if(newsData == null){
            newsData = new NewsData(HttpClient.getInstance().getHackerNewsApi());
        }

        return newsData;
    }
}
