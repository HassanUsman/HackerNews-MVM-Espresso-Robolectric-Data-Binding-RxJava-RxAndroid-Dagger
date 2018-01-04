package com.hackernews.reader.data.news;

import android.os.Handler;
import android.os.Looper;

import com.hackernews.reader.data.FakeData;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by HassanUsman on 29/11/2017.
 *
 */

public class FakeNewsData implements NewsModel {

    Map<Integer, NewsItem> data;
    private boolean connect = true;
    private ArrayList<DataRunnable> dataRunnable;

    @Override
    public ArrayList<NewsItem> getImmutableList() {
        if(data == null) data = new LinkedHashMap<>();
        return new ArrayList<>(data.values());
    }

    @Override
    public void fill(ArrayList<NewsItem> value) {
        data = new LinkedHashMap<>();
        for(NewsItem item : value){
            data.put(item.id,item);
        }
    }

    @Override
    public void getItems(final GetNewsItemCallback callback) {

        if(connect){

            if(data == null){
                data = new LinkedHashMap<>();
            }

            dataRunnable = new ArrayList<>();
            for(final Map.Entry<Integer, NewsItem> item: FakeData.newsItems.entrySet()){
                DataRunnable run = new DataRunnable(data, item.getValue(), callback);
                dataRunnable.add(run);
                new Handler(Looper.getMainLooper()).postDelayed(run, 500);
            }

            return;
        }

        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                callback.onErrorResponse(new Throwable());
            }
        });

    }

    @Override
    public void cancel() {
        if(dataRunnable != null){
            for(DataRunnable run : dataRunnable){
                run.cancel();
            }
        }
    }

    @Override
    public void disconnect() {
        connect = false;
    }

    @Override
    public void connect() {
        connect = true;
    }

    static class DataRunnable implements Runnable{

        Map<Integer, NewsItem> data;
        NewsItem item;
        GetNewsItemCallback callback;
        boolean respond = true;

        DataRunnable(Map data, NewsItem item, GetNewsItemCallback callback){
            this.data = data;
            this.item = item;
            this.callback = callback;
        }

        @Override
        public void run() {
            data.put(item.id,item);
            if(respond) {
                callback.onResponse(item);
            }
        }

        public void cancel(){
            respond = false;
        }
    }
}
