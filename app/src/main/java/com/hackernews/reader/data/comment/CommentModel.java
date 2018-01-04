package com.hackernews.reader.data.comment;

import com.hackernews.reader.data.BaseModel;

import java.util.ArrayList;

/**
 * Created by HassanUsman on 30/11/2017.
 */

@SuppressWarnings("ALL")
public interface CommentModel extends BaseModel {

    interface GetItemCallback{
        void onResponse(CommentItem item);
        void onErrorResponse(Throwable throwable);
    }

    ArrayList<CommentItem> getList();
    void fill(ArrayList<CommentItem> items);
    void fill(int[] commentIds);
    void getItems(final GetItemCallback callback);
}
