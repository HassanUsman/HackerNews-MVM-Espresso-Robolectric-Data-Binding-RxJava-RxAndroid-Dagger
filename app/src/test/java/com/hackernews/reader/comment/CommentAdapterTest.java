package com.hackernews.reader.comment;

import com.hackernews.reader.data.comment.CommentItem;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import java.util.ArrayList;
import java.util.Date;

import static junit.framework.Assert.assertEquals;

/**
 * Created by HassanUsman on 17/11/2017.
 *
 */

@RunWith(RobolectricTestRunner.class)
public class CommentAdapterTest {

    @Test
    public void testCommentAdapter(){

        CommentAdapterActivity activity = Robolectric.setupActivity(CommentAdapterActivity.class);

        ArrayList<CommentItem> item = new ArrayList<>();
        CommentItem comment1 = new CommentItem();
        comment1.by = "by1";
        comment1.id = 123;
        //comment1.kids
        comment1.parent = 1234;
        comment1.text = "text1";
        comment1.time = ((new Date()).getTime())/1000;
        comment1.type = "comment";
        item.add(comment1);

        CommentItem comment2 = new CommentItem();
        //comment2.by = "by1";
        comment2.id = 123;
        comment2.kids = new int[]{1};
        comment2.parent = 1234;
        comment2.text = "text1";
        comment2.time = ((new Date()).getTime())/1000;
        comment2.type = "comment";
        item.add(comment2);

        CommentItem comment3 = new CommentItem();
        //comment2.by = "by1";
        comment3.id = 123;
        comment3.kids = new int[]{1,2,3};
        comment3.parent = 1234;
        comment3.text = "text1";
        comment3.time = ((new Date()).getTime())/1000;
        comment3.type = "comment";
        item.add(comment3);

        CommentItem comment4 = new CommentItem();
        comment4.by = "by4";
        comment4.id = 123;
        comment4.kids = new int[]{};
        comment4.parent = 1234;
        comment4.text = "text1";
        comment4.time = ((new Date()).getTime())/1000;
        comment4.type = "comment";
        item.add(comment4);


        CommentItem comment5 = new CommentItem();
        comment5.by = "by4";
        comment5.id = 123;
        comment5.kids = new int[]{};
        comment5.parent = 1234;
        //comment5.text = "text1";
        comment5.time = ((new Date()).getTime())/1000;
        comment5.type = "comment";
        item.add(comment5);

        activity.setCommentItem(item);

        assertEquals(item.size(),activity.adapter.getItemCount());
        assertEquals(item.get(0),activity.adapter.getItem(0));
    }
}
