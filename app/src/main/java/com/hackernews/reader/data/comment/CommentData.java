package com.hackernews.reader.data.comment;

import com.hackernews.reader.data.HackerNewsApi;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by HassanUsman on 30/11/2017.
 */

@SuppressWarnings("ALL")
public class CommentData implements CommentModel {

    private HackerNewsApi api;
    private Map<Integer, CommentItem> data = new LinkedHashMap<>();

    CommentData(HackerNewsApi api){
        this.api = api;
    }

    public void fill(ArrayList<CommentItem> items){
        data = new LinkedHashMap<>();
        for(CommentItem i:items){
            data.put(i.id,i);
        }
    }

    public void fill(int[] commentIds){
        data = new LinkedHashMap<>();
        for(int id : commentIds){
            CommentItem item = new CommentItem();
            item.id = id;
            data.put(item.id,item);
        }
    }

    @Override
    public void disconnect() {

    }

    @Override
    public void connect() {

    }

    public ArrayList<CommentItem> getList() {
        return new ArrayList<>(data.values());
    }

    public void getItems(final GetItemCallback callback){

        Observable<Integer> observable = Observable.fromArray(data.keySet().toArray(new Integer[data.keySet().size()]));

        observable.concatMap(new Function<Integer, ObservableSource<CommentItem>>() {
            @Override
            public ObservableSource<CommentItem> apply(Integer integer) throws Exception {
                return api.getCommentItem(integer)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        }).filter(new Predicate<CommentItem>() {
            @Override
            public boolean test(CommentItem commentItem) throws Exception {
                return commentItem.by != null && !commentItem.by.equals("");
            }
        }).subscribe(new Consumer<CommentItem>() {
            @Override
            public void accept(CommentItem commentItem) throws Exception {
                data.put(commentItem.id, commentItem);
                callback.onResponse(commentItem);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                callback.onErrorResponse(throwable);
            }
        });
    }
}
