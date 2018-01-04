package com.hackernews.reader.news;

import com.hackernews.reader.data.news.NewsItem;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import java.util.ArrayList;
import java.util.Date;

import static junit.framework.Assert.assertEquals;

/**
 * Created by HassanUsman on 17/11/2017.
 */

@SuppressWarnings("ALL")
@RunWith(RobolectricTestRunner.class)
public class NewsAdapterTest {

    @Test
    public void testNewsAdapter(){
        NewsAdapterActivity newsAdapterActivity = Robolectric.setupActivity(NewsAdapterActivity.class);

        ArrayList<NewsItem> item = new ArrayList<>();
        NewsItem newsItem1 =  new NewsItem();
        newsItem1.by = "by1";
        newsItem1.title = "title1";
        newsItem1.type = "type1";
        newsItem1.url = "url1";
        newsItem1.score = 1;
        newsItem1.time = (new Date()).getTime()/1000;
        item.add(newsItem1);

        NewsItem newsItem2 =  new NewsItem();
        newsItem2.by = "by2";
        //newsItem2.title = "title2";
        newsItem2.type = "type2";
        newsItem2.url = "http://www.google.com";
        newsItem2.score = 2;
        newsItem2.time = (new Date()).getTime()/1000;
        newsItem2.kids = new int[]{1};

        item.add(newsItem2);

        NewsItem newsItem3 =  new NewsItem();
        newsItem3.by = "by3";
        newsItem3.type = "type3";
        newsItem3.url = "http://plus.google.com";
        newsItem3.score = 3;
        newsItem3.time = (new Date()).getTime()/1000;
        newsItem3.kids = new int[]{1,2};

        item.add(newsItem3);

        newsAdapterActivity.setNewsItem(item);
        assertEquals(item.size(),newsAdapterActivity.adapter.getItemCount());
        assertEquals(item.get(0),newsAdapterActivity.adapter.getItemAt(0));
    }
}
