package com.hackernews.reader.news;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hackernews.reader.R;
import com.hackernews.reader.comment.CommentActivity;
import com.hackernews.reader.data.news.NewsProvider;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.ThreadLocalRandom;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeDown;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayingAtLeast;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by HassanUsman on 29/11/2017.
 *
 */

@RunWith(AndroidJUnit4.class)
public class NewsActivityTest {

    @Rule
    public ActivityTestRule<NewsActivity> activityTestRule =  new ActivityTestRule<NewsActivity>(NewsActivity.class, true,false){

        @Override
        protected void beforeActivityLaunched() {
            super.beforeActivityLaunched();
            NewsProvider.getInstance().connect();
        }

    };

    private Context context;

    @Before
    public void setup(){
        context= InstrumentationRegistry.getTargetContext();
    }

    private Activity launchActivity(){
        activityTestRule.launchActivity(null);
        NewsActivity activity = activityTestRule.getActivity();
        IdlingRegistry.getInstance().register(activity.getIdlingResource());
        return activity;
    }

    @Test
    public void testLoad(){
        launchActivity();
        onView(withText("title 0")).check(matches(isDisplayed()));
    }

    @Test
    public void testScroll(){
        Activity activity = launchActivity();

        //ensure the UI is ready
        onView(withText("title 0")).check(matches(isDisplayed()));

        NewsAdapter newsAdapter = (NewsAdapter)((RecyclerView)activity.findViewById(R.id.recycler_view_news)).getAdapter();
        int position = ThreadLocalRandom.current().nextInt(0, newsAdapter.getItemCount());
        onView(withId(R.id.recycler_view_news)).perform(RecyclerViewActions.scrollToPosition(position));
        onView(withText("title "+position)).check(matches(isDisplayed()));
    }

    @Test
    public void testRotateScreen(){
        Activity activity = launchActivity();

        //ensure the UI is ready
        onView(withText("title 0")).check(matches(isDisplayed()));

        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        onView(withId(R.id.recycler_view_news)).check(matches(isDisplayed()));
        onView(withText("title 0")).check(matches(isDisplayed()));
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        onView(withId(R.id.recycler_view_news)).check(matches(isDisplayed()));
        onView(withText("title 0")).check(matches(isDisplayed()));
    }

    @Test
    public void testSwipeToRefresh(){
        launchActivity();
        onView(withId(R.id.swipe_refresh)).perform(withCustomConstraints(swipeDown(), isDisplayingAtLeast(85)));
        onView(withId(R.id.swipe_refresh)).check(matches(isDisplayed()));
        onView(withText("title 0")).check(matches(isDisplayed()));
    }

    @Test
    public void testOfflineAtStart(){

        ActivityTestRule<NewsActivity> activityTestRule =  new ActivityTestRule<NewsActivity>(NewsActivity.class, true,false){

            @Override
            protected void beforeActivityLaunched() {
                super.beforeActivityLaunched();
                (NewsProvider.getInstance()).disconnect();
            }
        };

        activityTestRule.launchActivity(null);
        onView(withId(R.id.retry_button)).check(matches(isDisplayed()));

        (NewsProvider.getInstance()).connect();
        onView(withId(R.id.retry_button)).perform(click());
        onView(withId(R.id.recycler_view_news)).check(matches(isDisplayed()));
    }

    @Test
    public void testOfflineWhileRefreshing(){
        launchActivity();

        //ensure the UI is ready
        onView(withText("title 0")).check(matches(isDisplayed()));

        (NewsProvider.getInstance()).disconnect();

        onView(withId(R.id.swipe_refresh)).perform(withCustomConstraints(swipeDown(), isDisplayingAtLeast(85)));
        onView(withId(R.id.retry_button)).check(matches(isDisplayed()));
    }

    @Test
    public void testClickNews(){
        IdlingRegistry.getInstance().register(CommentActivity.getIdlingResource());
        launchActivity();
        onView(withId(R.id.recycler_view_news)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withText(context.getString(R.string.comment_text)+" 0")).check(matches(isDisplayed()));
    }

    public static ViewAction withCustomConstraints(final ViewAction action, final Matcher<View> constraints) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return constraints;
            }

            @Override
            public String getDescription() {
                return action.getDescription();
            }

            @Override
            public void perform(UiController uiController, View view) {
                action.perform(uiController, view);
            }
        };
    }
}
