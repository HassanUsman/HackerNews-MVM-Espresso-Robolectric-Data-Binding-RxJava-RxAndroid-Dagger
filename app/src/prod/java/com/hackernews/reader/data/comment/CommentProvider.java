package com.hackernews.reader.data.comment;

import com.hackernews.reader.data.HttpClient;

/**
 * Created by HassanUsman on 30/11/2017.
 */

public class CommentProvider {

    private static CommentModel commentData;

    public static CommentModel getInstance() {
        if(commentData == null){
            commentData = new CommentData(HttpClient.getInstance().getHackerNewsApi());
        }

        return commentData;
    }

    public static CommentModel newInstance(){
        return new CommentData(HttpClient.getInstance().getHackerNewsApi());
    }
}
