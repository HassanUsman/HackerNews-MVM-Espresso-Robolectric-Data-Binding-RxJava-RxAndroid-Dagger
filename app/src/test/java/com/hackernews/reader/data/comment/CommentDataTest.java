package com.hackernews.reader.data.comment;

import com.hackernews.reader.data.HackerNewsApi;

import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;

/**
 * Created by HassanUsman on 4/12/2017.
 *
 */

public class CommentDataTest {

    static ArrayList<CommentItem> commentItems = new ArrayList<>();

    static{
        for(int i=0;i<10;i++){
            CommentItem item = new CommentItem();
            item.id = i;
            commentItems.add(item);
        }
    }

    @Test
    public void testFillInteger(){
        HackerNewsApi api = mock(HackerNewsApi.class);
        CommentData commentData = new CommentData(api);
        commentData.fill(new int[]{0,1,2,3,4,5,6,7,8,9});
        ArrayList<CommentItem> list = commentData.getList();
        assertEquals(10,list.size());
        for(int i=0;i<list.size();i++){
            assertEquals(i,list.get(i).id);
        }
    }

    @Test public void testFillObject(){
        HackerNewsApi api = mock(HackerNewsApi.class);
        CommentData commentData = new CommentData(api);
        commentData.fill(commentItems);
        ArrayList<CommentItem> list = commentData.getList();
        assertEquals(10,list.size());
        for(int i=0;i<list.size();i++){
            assertEquals(commentItems.get(i).id,list.get(i).id);
        }
    }
}
