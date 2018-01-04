package com.hackernews.reader.util;

import android.view.View;
import android.widget.TextView;

import com.hackernews.reader.R;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Created by HassanUsman on 12/11/2017.
 *
 * Robolectric support API 16 and onwards,
 * hence API 11 to API 15 has to be test on real device/simulator
 */


@RunWith(RobolectricTestRunner.class)
public class UILoaderTest {

    @Test
    public void testShowLoader(){
          UILoaderActivity activity = Robolectric.setupActivity(UILoaderActivity.class);
          activity.uiLoader.showLoader();
          assertThat(1f, equalTo(activity.findViewById(R.id.loading_container).getAlpha()));
          assertThat(0f, equalTo(activity.findViewById(R.id.recycler_view_news).getAlpha()));
    }

    @Test
    public void testShowContent(){
        UILoaderActivity activity = Robolectric.setupActivity(UILoaderActivity.class);
        activity.uiLoader.showContent();
        assertThat(0f, equalTo(activity.findViewById(R.id.loading_container).getAlpha()));
        assertThat(1f, equalTo(activity.findViewById(R.id.recycler_view_news).getAlpha()));
    }

    @Test public void testShowError() {

        final UILoaderActivity activity = Robolectric.setupActivity(UILoaderActivity.class);
        activity.uiLoader.showLoadError("error", new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                ((TextView)activity.findViewById(R.id.error_message)).setText("button clicked");
            }
        });

        assertEquals(View.VISIBLE, activity.findViewById(R.id.loading_error_container).getVisibility());
        assertEquals("error", ((TextView) activity.findViewById(R.id.error_message)).getText().toString());

        activity.findViewById(R.id.retry_button).performClick();
        assertEquals("button clicked", ((TextView) activity.findViewById(R.id.error_message)).getText().toString());
    }
}
