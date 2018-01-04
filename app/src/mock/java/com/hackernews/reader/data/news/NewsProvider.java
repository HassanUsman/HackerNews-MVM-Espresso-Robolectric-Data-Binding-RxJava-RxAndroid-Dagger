package com.hackernews.reader.data.news;

/**
 * Created by HassanUsman on 29/11/2017.
 *
 */

public class NewsProvider{

    private static NewsModel fakeNewsData;

    public static NewsModel getInstance() {
        if(fakeNewsData == null){
            fakeNewsData = new FakeNewsData();
        }

        return fakeNewsData;
    }
}
