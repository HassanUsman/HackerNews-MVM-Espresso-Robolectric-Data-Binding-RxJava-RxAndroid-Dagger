package com.hackernews.reader.data;

import com.hackernews.reader.data.comment.CommentModel;
import com.hackernews.reader.data.comment.CommentProvider;
import com.hackernews.reader.data.news.NewsModel;
import com.hackernews.reader.data.news.NewsProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by HassanUsman on 23/12/2017.
 */

@Module
public class ProviderModule {

    @Provides
    @Singleton
    public NewsModel provideNewsModel(){
        return NewsProvider.getInstance();
    }

    @Provides
    public CommentModel provideCommentModel(){
        return CommentProvider.newInstance();
    }
}
