package com.hackernews.reader.news;

import com.hackernews.reader.data.news.NewsItem;

/**
 * Created by HassanUsman on 28/11/2017.
 *
 */

interface NewsView {
    void showProgressBar();
    void addToAdapter(NewsItem item);
    void showError(Throwable throwable);
}
