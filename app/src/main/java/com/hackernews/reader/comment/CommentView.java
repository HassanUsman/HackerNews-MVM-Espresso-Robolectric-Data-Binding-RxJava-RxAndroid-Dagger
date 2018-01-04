package com.hackernews.reader.comment;

import com.hackernews.reader.data.comment.CommentItem;

import java.util.ArrayList;

/**
 * Created by HassanUsman on 30/11/2017.
 */

@SuppressWarnings("ALL")
interface CommentView {
    void fillAdapter(ArrayList<CommentItem> item);
    void showError(Throwable throwable);
    void addToAdapter(CommentItem item);
}
