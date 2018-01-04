package com.hackernews.reader.news;


import android.support.test.espresso.idling.CountingIdlingResource;
import android.util.Log;

import com.hackernews.reader.data.news.NewsModel;
import com.hackernews.reader.data.news.NewsItem;

import java.util.ArrayList;

/**
 * Created by HassanUsman on 28/11/2017.
 *
 */

class NewsPresenter{

    private final NewsView view;
    private final NewsModel model;
    private final CountingIdlingResource idlingResource;
    private boolean toDecrease = false;

    NewsPresenter(NewsModel model, NewsView view, CountingIdlingResource idlingResource){
        this.model = model;
        this.view = view;
        this.idlingResource = idlingResource;
    }

    ArrayList<NewsItem> getData(){
        return model.getImmutableList();
    }

    void restoreState(ArrayList<NewsItem>  value){

        idlingResource.increment();
        toDecrease = true;

        model.fill(value);

        ArrayList<NewsItem> list = model.getImmutableList();
        for(NewsItem item : list){
            view.addToAdapter(item);
            if(toDecrease){
                idlingResource.decrement();
                toDecrease = false;
            }
        }
    }

    void loadNews(){

        view.showProgressBar();

        idlingResource.increment();
        toDecrease = true;

        model.getItems(new NewsModel.GetNewsItemCallback() {
            @Override
            public void onResponse(NewsItem newsItem) {
                Log.i("news item","news item :"+String.valueOf(newsItem.id));
                view.addToAdapter(newsItem);
                if(toDecrease){
                    idlingResource.decrement();
                    toDecrease = false;
                }
            }

            @Override
            public void onErrorResponse(Throwable throwable) {
                Log.i("news item","news item : error");
                view.showError(throwable);
                if(toDecrease){
                    idlingResource.decrement();
                    toDecrease = false;
                }
            }
        });
    }

    void refreshNews(){
        model.cancel();
        loadNews();
    }


}
