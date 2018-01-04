package com.hackernews.reader.util;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.hackernews.reader.R;

/**
 * Created by HassanUsman on 15/11/2017.
 */

@SuppressWarnings("ALL")
public class UILoaderActivity extends AppCompatActivity {

    UILoader uiLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loader_test);
        View loadingContainer = findViewById(R.id.loading_container);
        View contentContainer = findViewById(R.id.recycler_view_news);
        uiLoader = new UILoader(this, loadingContainer,contentContainer);
    }
}
