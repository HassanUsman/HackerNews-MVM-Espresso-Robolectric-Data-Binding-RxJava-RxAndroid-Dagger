package com.hackernews.reader.data.news;

import com.hackernews.reader.data.HackerNewsApi;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by HassanUsman on 28/11/2017.
 *
 */

public class NewsData implements NewsModel {

    private Map<Integer, NewsItem> data = new LinkedHashMap<>();
    private HackerNewsApi api;
    private Disposable disposable;

    NewsData(HackerNewsApi api){
        this.api = api;
    }

    public ArrayList<NewsItem> getImmutableList(){
        return new ArrayList<>(data.values());
    }

    public void fill(ArrayList<NewsItem> value){
        data = new LinkedHashMap<>();
        for(NewsItem i : value){
            data.put(i.id,i);
        }
    }

    public void getItems(final GetNewsItemCallback callback){
        disposable = api.getTopStoriesId()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .concatMap(new Function<Integer[], ObservableSource<Integer>>() {
                @Override
                public ObservableSource<Integer> apply(Integer[] integers) throws Exception {
                    return Observable.fromArray(integers);
                }
            }).concatMap(new Function<Integer, ObservableSource<NewsItem>>() {
                @Override
                public ObservableSource<NewsItem> apply(Integer integer) throws Exception {
                    return api.getNews(integer).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
                }
            }).subscribe(new Consumer<NewsItem>() {
                    @Override
                    public void accept(NewsItem newsItem) throws Exception {
                            data.put(newsItem.id,newsItem);
                            callback.onResponse(newsItem);
                    }
                },
                new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        callback.onErrorResponse(throwable);
                    }
                }
            );
    }

    @Override
    public void cancel(){
        if(disposable!=null){
            disposable.dispose();
        }
    }

    @Override
    public void disconnect() {
        //do nothing
    }

    @Override
    public void connect() {
        //do nothing
    }
}
