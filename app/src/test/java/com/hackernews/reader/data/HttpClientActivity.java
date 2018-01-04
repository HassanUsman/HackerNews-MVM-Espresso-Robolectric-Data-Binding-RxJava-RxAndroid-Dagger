package com.hackernews.reader.data;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by HassanUsman on 5/12/2017.
 */

@SuppressWarnings("ALL")
public class HttpClientActivity extends AppCompatActivity {

    HttpClient api;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = HttpClient.getInstance();
    }
}
