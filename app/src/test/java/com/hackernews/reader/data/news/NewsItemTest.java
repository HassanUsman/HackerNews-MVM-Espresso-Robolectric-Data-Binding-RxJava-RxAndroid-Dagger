package com.hackernews.reader.data.news;

/**
 * Created by HassanUsman on 16/11/2017.
 */


import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static junit.framework.Assert.assertEquals;

/**
 * Created by HassanUsman on 16/11/2017.
 *
 */
@RunWith(RobolectricTestRunner.class)
public class NewsItemTest {

    @Test
    public void testNewsItemParcelable(){
        NewsItemActivity activity = Robolectric.setupActivity(NewsItemActivity.class);

        NewsItem newsItem = new NewsItem();
        newsItem.by = "by";
        newsItem.title = "title";
        newsItem.type = "type";
        newsItem.url = "url";

        NewsItem createFromParcel = activity.getFromParcel(newsItem);

        assertEquals(newsItem.by,createFromParcel.by);
        assertEquals(newsItem.title,createFromParcel.title);
        assertEquals(newsItem.type,createFromParcel.type);
        assertEquals(newsItem.url,createFromParcel.url);
    }
}
