package com.hackernews.reader.util;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hackernews.reader.R;

/**
 * Created by HassanUsman on 10/11/2017.
 */

@SuppressWarnings("ALL")
public class UILoader {

    private final View loaderView;
    private final View contentView;

    public UILoader(Context context, View loaderView, View contentView){
        this.loaderView = loaderView;
        this.contentView = contentView;
    }

    public void showLoader(){
        loaderView.setVisibility(View.VISIBLE);
        loaderView.setAlpha(1f);
        contentView.setAlpha(0f);
    }

    private void hideLoadError(){
        contentView.setVisibility(View.VISIBLE);
        loaderView.findViewById(R.id.loading_spinner).setAlpha(1f);
        loaderView.findViewById(R.id.loading_error_container).setAlpha(0f);
    }

    public void showLoadError(String message, final View.OnClickListener listener){
        Log.i(AppLog.LOG_TAG,"show load error");

        loaderView.setVisibility(View.VISIBLE);
        loaderView.setAlpha(1f);
        contentView.setVisibility(View.INVISIBLE);

        loaderView.findViewById(R.id.loading_spinner).setAlpha(0f);

        loaderView
            .findViewById(R.id.loading_error_container)
            .setAlpha(0f);

        loaderView
            .findViewById(R.id.loading_error_container)
            .setVisibility(View.VISIBLE);

        loaderView.findViewById(R.id.loading_error_container).setAlpha(1f);

        TextView errorView = (TextView)loaderView.findViewById(R.id.loading_error_container).findViewById(R.id.error_message);
        errorView.setText(message);
        final Button retryButton = (Button)loaderView.findViewById(R.id.retry_button);

        retryButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Log.i(AppLog.LOG_TAG,"retry button is clicked");
                hideLoadError();
                showLoader();
                listener.onClick(retryButton);
            }
        });
    }

    public void showContent(){
        loaderView.setAlpha(0f);
        contentView.setAlpha(1f);
        loaderView.setVisibility(View.GONE);
    }
}
