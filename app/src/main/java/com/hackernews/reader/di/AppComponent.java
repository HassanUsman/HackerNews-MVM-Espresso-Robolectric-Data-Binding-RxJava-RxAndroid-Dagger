package com.hackernews.reader.di;

import com.hackernews.reader.comment.CommentActivity;
import com.hackernews.reader.data.ProviderModule;
import com.hackernews.reader.news.NewsActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by HassanUsman on 23/12/2017.
 */
@Singleton
@Component(modules={ProviderModule.class})
public interface AppComponent{
    void inject(NewsActivity activity);
    void inject(CommentActivity activity);
}
