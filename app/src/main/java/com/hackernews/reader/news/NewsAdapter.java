package com.hackernews.reader.news;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import com.hackernews.reader.R;
import com.hackernews.reader.data.news.NewsItem;
import com.hackernews.reader.databinding.NewsItemBinding;
import com.hackernews.reader.util.Util;


/**
 * Created by HassanUsman on 10/11/2017.
 *
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    NewsItem getItemAt(int i) {
        return data.get(i);
    }

    void addItem(NewsItem item) {
        data.add(item);
        notifyItemInserted(data.size()-1);
    }

    void clear() {
        data.clear();
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        View itemView;
        NewsItemBinding binding;

        ViewHolder(NewsItemBinding binding){
            super(binding.getRoot());
            itemView = binding.getRoot();
            this.binding = binding;
        }
    }

    private final ArrayList<NewsItem> data;
    private final Callback callback;

    public interface Callback{
        void onItemClick(int position);
    }

    NewsAdapter(ArrayList<NewsItem> data, Callback callback){
        this.data = data;
        this.callback = callback;
    }

    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        NewsItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.news_item, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final NewsAdapter.ViewHolder holder, int position) {

        NewsItem newsItem = data.get(position);

        holder.binding.no.setText(String.valueOf(position+1)+".");
        holder.binding.title.setText(newsItem.title);

        String host = getHostName(newsItem.url);
        if(host.equals("")){
            holder.binding.source.setVisibility(View.GONE);
        }else{
            holder.binding.source.setVisibility(View.VISIBLE);
            holder.binding.source.setText(" ("+host+")");
        }

        String points = "point";
        if(newsItem.score > 1){
            points += "s";
        }

        holder.binding.point.setText(newsItem.score+" "+points);
        holder.binding.author.setText(" by "+newsItem.by);

        CharSequence value = Util.getPrettyTime(newsItem.time);
        holder.binding.time.setText(" "+value);

        String comments;
        if(newsItem.kids == null){
            comments = "0 comment";
        }else if(newsItem.kids.length >1){
            comments = newsItem.kids.length + " comments";
        }else{
            comments = newsItem.kids.length + " comment";
        }

        holder.binding.comment.setText(" | "+comments);

        holder.itemView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                callback.onItemClick(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    private static String getHostName(String url) {
        return Util.getHostname(url);
    }
}
