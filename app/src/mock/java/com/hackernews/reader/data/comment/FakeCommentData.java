package com.hackernews.reader.data.comment;

import android.os.Handler;

import com.hackernews.reader.data.FakeData;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by HassanUsman on 2/12/2017.
 */

@SuppressWarnings("ALL")
public class FakeCommentData implements CommentModel{

    private Map<Integer, CommentItem> data = new LinkedHashMap<>();
    private boolean connect = true;

    @Override
    public void disconnect() {
        connect = false;
    }

    @Override
    public void connect() {
        connect = true;
    }

    @Override
    public ArrayList<CommentItem> getList() {
        return new ArrayList<>(data.values());
    }

    @Override
    public void fill(ArrayList<CommentItem> items) {
        data = new LinkedHashMap<>();
        for(CommentItem i:items){
            data.put(i.id,i);
        }
    }

    @Override
    public void fill(int[] value) {
        data = new LinkedHashMap<>();

        for(int v: value){
            CommentItem item = new CommentItem();
            item.id = v;
            data.put(item.id,item);
        }
    }

    @Override
    public void getItems(final GetItemCallback callback) {
        if(connect){
            for(final Map.Entry<Integer, CommentItem> c : data.entrySet()){
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        data.put(c.getKey(), FakeData.commentItems.get(c.getKey()));
                        callback.onResponse(data.get(c.getKey()));
                    }
                },1000);
            }

            return;
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                callback.onErrorResponse(new Throwable());
            }
        },1000);
    }
}
