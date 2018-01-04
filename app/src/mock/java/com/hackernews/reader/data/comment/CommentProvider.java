package com.hackernews.reader.data.comment;

import java.util.ArrayList;

/**
 * Created by HassanUsman on 2/12/2017.
 */

@SuppressWarnings("ALL")
public class CommentProvider {

    private static ArrayList<CommentModel> commentModelArrayList = new ArrayList<>();
    private static boolean disableConnection = false;

    public static CommentModel getInstance() {
        if(commentModelArrayList.size() == 0){
            return newInstance();
        }

        return commentModelArrayList.get(commentModelArrayList.size()-1);
    }

    public static CommentModel newInstance(){
        CommentModel model = new FakeCommentData();

        if(disableConnection){
            model.disconnect();
        }
        commentModelArrayList.add(model);

        return commentModelArrayList.get(commentModelArrayList.size()-1);
    }

    public static void initDisableConnection(){
        disableConnection = true;
    }

    public static void initNormalConnection(){
        disableConnection = false;
    }
}
