package com.hackernews.reader.comment;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hackernews.reader.data.comment.CommentItem;
import com.hackernews.reader.R;
import com.hackernews.reader.databinding.CommentItemBinding;
import com.hackernews.reader.util.Util;

import java.util.ArrayList;

/**
 * Created by HassanUsman on 13/11/2017.
 *
 */

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {

    interface Callback{
        void requestReply(int position);
    }


    private ArrayList<CommentItem> data;
    private Callback callback;

    CommentAdapter(ArrayList<CommentItem> comment, Callback callback){
        this.data = comment;
        this.callback = callback;
    }

    public void addItem(CommentItem item) {
        data.add(item);
        notifyItemInserted(data.size()-1);
    }

    public void setData(ArrayList<CommentItem> items){
        data = items;
        notifyDataSetChanged();
    }

    public CommentItem getItem(int i) {
        return data.get(i);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CommentItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.comment_item,parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        CommentItem commentItem = data.get(position);

        holder.itemView.findViewById(R.id.indicator).setVisibility(View.VISIBLE);

        holder.binding.by.setText(commentItem.by);
        holder.binding.time.setText(Util.getPrettyTime(commentItem.time));

        if(commentItem.text != null)
            holder.binding.comment.setHtml(commentItem.text.trim());
        else
            holder.binding.comment.setHtml("");

        if(commentItem.kids == null || commentItem.kids.length == 0){
            holder.binding.kid.setVisibility(View.GONE);
        }else{
            holder.binding.kid.setVisibility(View.VISIBLE);
            if(commentItem.kids.length > 1){
                holder.binding.kid.setText("View "+ commentItem.kids.length+" replies");
            }else{
                holder.binding.kid.setText("View 1 reply");
            }

            holder.binding.kid.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    callback.requestReply(holder.getAdapterPosition());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        final View itemView;
        CommentItemBinding binding;

        ViewHolder(CommentItemBinding binding){
            super(binding.getRoot());
            this.itemView = binding.getRoot();
            this.binding = binding;
        }
    }
}
