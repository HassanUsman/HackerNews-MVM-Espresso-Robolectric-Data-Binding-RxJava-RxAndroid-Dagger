package com.hackernews.reader.news;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hackernews.reader.R;
import com.hackernews.reader.news.NewsAdapter;
import com.hackernews.reader.data.news.NewsItem;

import java.util.ArrayList;

public class NewsAdapterActivity extends AppCompatActivity implements NewsAdapter.Callback{

    public NewsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_adapter);
    }

    public void setNewsItem(ArrayList<NewsItem> newsItem){
        adapter = new NewsAdapter(newsItem,this);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.newsList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(int position) {

    }
}
