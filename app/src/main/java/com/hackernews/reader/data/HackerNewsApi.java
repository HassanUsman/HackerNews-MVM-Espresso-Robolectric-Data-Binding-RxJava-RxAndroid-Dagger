package com.hackernews.reader.data;

import com.hackernews.reader.data.comment.CommentItem;
import com.hackernews.reader.data.news.NewsItem;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by HassanUsman on 13/12/2017.
 */

@SuppressWarnings("ALL")
public interface HackerNewsApi {

    @GET("/v0/topstories.json")
    Observable<Integer[]> getTopStoriesId();

    @GET("/v0/item/{newsId}.json")
    Observable<NewsItem> getNews(@Path("newsId") int newsId);

    @GET("/v0/item/{commentId}.json")
    Observable<CommentItem> getCommentItem(@Path("commentId") int commentId);
}
