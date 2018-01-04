package com.hackernews.reader.data;

import com.hackernews.reader.data.comment.CommentItem;
import com.hackernews.reader.data.news.NewsItem;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by HassanUsman on 3/12/2017.
 */

@SuppressWarnings("ALL")
public class FakeData {

    static public Map<Integer, NewsItem> newsItems;
    static public Map<Integer, CommentItem> commentItems;

    static{

        commentItems = new LinkedHashMap<>();
        for(int i=0;i<10;i++){
            CommentItem item = new CommentItem();
            item.id = i;
            item.by = "by"+i;
            item.kids = new int[]{0};
            item.parent = -1;
            item.text =  "this is comment text of id "+i;
            item.time = new Date().getTime()/1000;
            item.type = "comment";
            commentItems.put(item.id,item);
        }

        newsItems = new LinkedHashMap<>();
        for(int i=0;i<400;i++){
            NewsItem newsItem = new NewsItem();
            newsItem.id = i;

            newsItem.by = "by"+newsItem.id;

            int descendantCount = (new Random()).nextInt(9)+1;
            newsItem.descendants = descendantCount;
            newsItem.kids = new int[descendantCount];
            for(int j=0;j<descendantCount;j++){
                newsItem.kids[j] = j;
            }

            newsItem.score = 111;
            newsItem.time = new Date().getTime()/1000;
            newsItem.title = "title "+newsItem.id;
            newsItem.type = "story";
            newsItem.url = "http://www.google.com";

            newsItems.put(newsItem.id, newsItem);
        }
    }

}
