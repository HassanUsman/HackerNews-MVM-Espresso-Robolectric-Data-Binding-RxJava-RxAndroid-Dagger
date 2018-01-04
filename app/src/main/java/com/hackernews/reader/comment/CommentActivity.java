package com.hackernews.reader.comment;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.idling.CountingIdlingResource;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.hackernews.reader.NewsReaderApplication;
import com.hackernews.reader.data.comment.CommentItem;
import com.hackernews.reader.R;
import com.hackernews.reader.data.comment.CommentModel;
import com.hackernews.reader.databinding.ActivityCommentBinding;
import com.hackernews.reader.util.UILoader;

import java.util.ArrayList;

import javax.inject.Inject;

public class CommentActivity extends AppCompatActivity implements
        CommentAdapter.Callback,
        CommentView {

    public static final String COMMENT_LIST = "comment_list";
    private static final String COMMENT_ITEM = "comment_item";
    private UILoader UILoader;
    private CommentAdapter commentAdapter;

    @Inject
    public CommentModel commentModel;

    @Nullable
    private static CountingIdlingResource idlingResource = new CountingIdlingResource("commentIdleResource");
    private CommentPresenter presenter;
    private ActivityCommentBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.comment);

        ((NewsReaderApplication)getApplication()).getAppComponent().inject(this);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_comment);
        binding.recyclerViewComment.setLayoutManager(new LinearLayoutManager(this));
        UILoader = new UILoader(this, binding.loadingPage.loadingContainer, binding.recyclerViewComment);
        commentAdapter = new CommentAdapter(new ArrayList<CommentItem>(), this);
        binding.recyclerViewComment.setAdapter(commentAdapter);

        presenter = new CommentPresenter(commentModel, this, idlingResource);

        if(savedInstanceState == null){
            int[] commentIdList = getIntent().getIntArrayExtra(COMMENT_LIST);

            if(commentIdList == null || commentIdList.length == 0){
                showEmpty();
                return;
            }

            presenter.loadComemnts(commentIdList);

        }else{
            ArrayList<Parcelable> retrieved = savedInstanceState.getParcelableArrayList(COMMENT_ITEM);
            ArrayList<CommentItem> items = new ArrayList<>();
            for(Parcelable p : retrieved){
                items.add((CommentItem)p);
            }

            presenter.restoreState(items);
            UILoader.showContent();
        }
    }

    private void showEmpty() {
        binding.recyclerViewComment.setVisibility(View.GONE);
        binding.loadingPage.loadingContainer.setVisibility(View.GONE);
        binding.noComment.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(COMMENT_ITEM,presenter.getData());
    }

    @Override
    public void requestReply(int position) {
        Intent i = new Intent(this, CommentActivity.class);
        i.putExtra(COMMENT_LIST,presenter.getData().get(position).kids);
        startActivity(i);
    }

    @Override
    public void fillAdapter(ArrayList<CommentItem> item) {
        commentAdapter.setData(item);
    }

    @Override
    public void showError(Throwable t) {
        UILoader.showLoadError("Error loading comment"
            ,new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    commentAdapter.notifyDataSetChanged();
                }
            });
    }

    @Override
    public void addToAdapter(CommentItem item) {
        if(binding.loadingPage.loadingContainer.getVisibility() != View.GONE){
            UILoader.showContent();
        }
        commentAdapter.addItem(item);
    }

    @VisibleForTesting
    @NonNull
    public static CountingIdlingResource getIdlingResource() {
        return idlingResource;
    }
}
