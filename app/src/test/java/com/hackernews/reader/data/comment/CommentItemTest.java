package com.hackernews.reader.data.comment;

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
public class CommentItemTest {

    @Test public void testCommentItemParcelable(){

        CommentItemActivity activity = Robolectric.setupActivity(CommentItemActivity.class);

        CommentItem commentItem = new CommentItem();
        commentItem.by = "by";
        commentItem.id = 123;
        commentItem.parent = 1234;
        commentItem.text = "text";
        commentItem.time = 123L;
        commentItem.type = "type";

        CommentItem createFromParcel = activity.getFromParcel(commentItem);

        assertEquals(commentItem.by,createFromParcel.by);
        assertEquals(commentItem.id,createFromParcel.id);
        assertEquals(commentItem.parent,createFromParcel.parent);
        assertEquals(commentItem.text,createFromParcel.text);
        assertEquals(commentItem.time,createFromParcel.time);
        assertEquals(commentItem.type,createFromParcel.type);
    }
}
