package com.example.soundlair2.Views;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.rule.GrantPermissionRule;
import androidx.test.runner.AndroidJUnit4;

import com.example.soundlair2.Presenters.MainActivity;
import com.example.soundlair2.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class FullUserFlowTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Rule
    public GrantPermissionRule mGrantPermissionRule =
            GrantPermissionRule.grant(
                    "android.permission.WRITE_EXTERNAL_STORAGE");

    @Test
    public void fullUserFlowTest() {

        try {
            Thread.sleep(5200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.btn_AddActiveTrack),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                4),
                        isDisplayed()));
        appCompatButton.perform(click());

        try {
            Thread.sleep(1200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction button = onView(
                allOf(withText("The Hunted"),
                        childAtPosition(
                                allOf(withId(R.id.layout_SongList),
                                        childAtPosition(
                                                withId(R.id.view_SongList),
                                                0)),
                                0)));
        button.perform(scrollTo(), click());

        try {
            Thread.sleep(2400);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction button2 = onView(
                allOf(withText("Temptation"),
                        childAtPosition(
                                allOf(withId(R.id.layout_SongList),
                                        childAtPosition(
                                                withId(R.id.view_SongList),
                                                0)),
                                1)));
        button2.perform(scrollTo(), click());

        try {
            Thread.sleep(2400);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction button3 = onView(
                allOf(withText("The Hunted"),
                        childAtPosition(
                                allOf(withId(R.id.layout_SongList),
                                        childAtPosition(
                                                withId(R.id.view_SongList),
                                                0)),
                                0)));
        button3.perform(scrollTo(), click());

        try {
            Thread.sleep(2400);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.btn_AddToPlaylist), withText("Add to Playlist"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatButton3.perform(click());

        try {
            Thread.sleep(2400);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.btn_AddAmbientTrack),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                5),
                        isDisplayed()));
        appCompatButton4.perform(click());

        try {
            Thread.sleep(1200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction button4 = onView(
                allOf(withText("sfx_ForestWind"),
                        childAtPosition(
                                allOf(withId(R.id.layout_SongList),
                                        childAtPosition(
                                                withId(R.id.view_SongList),
                                                0)),
                                3)));
        button4.perform(scrollTo(), click());

        try {
            Thread.sleep(4800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatButton5 = onView(
                allOf(withId(R.id.btn_AddToPlaylist), withText("Add to Playlist"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatButton5.perform(click());

        try {
            Thread.sleep(2400);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // TODO Figure out Slider Values for SFX Wind

        ViewInteraction appCompatButton6 = onView(
                allOf(withId(R.id.btn_AddAmbientTrack), withText("+ Ambience"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                5),
                        isDisplayed()));
        appCompatButton6.perform(click());

        try {
            Thread.sleep(1200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction button5 = onView(
                allOf(withText("sfx_Rain"),
                        childAtPosition(
                                allOf(withId(R.id.layout_SongList),
                                        childAtPosition(
                                                withId(R.id.view_SongList),
                                                0)),
                                2)));
        button5.perform(scrollTo(), click());

        try {
            Thread.sleep(2400);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatButton7 = onView(
                allOf(withId(R.id.btn_AddToPlaylist), withText("Add to Playlist"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatButton7.perform(click());

        try {
            Thread.sleep(1200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // TODO Figure out Slider Values for wind

        try {
            Thread.sleep(1200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction button6 = onView(
                allOf(withText("The Hunted"),
                        childAtPosition(
                                allOf(withId(R.id.layout_TrackList),
                                        childAtPosition(
                                                withId(R.id.view_TrackList),
                                                0)),
                                0)));
        button6.perform(scrollTo(), click());


        try {
            Thread.sleep(1200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // TODO Figure out Slider Values for rain

        ViewInteraction appCompatButton8 = onView(
                allOf(withId(R.id.btn_AddPlaylist), withText("+"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatButton8.perform(click());

        try {
            Thread.sleep(4800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction tabView = onView(
                allOf(withContentDescription("New PlayList 2"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tabs_Playlist),
                                        0),
                                1)));
        tabView.perform(scrollTo(), click());

        try {
            Thread.sleep(3200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatButton9 = onView(
                allOf(withId(R.id.btn_AddActiveTrack), withText("+ Track"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                4),
                        isDisplayed()));
        appCompatButton9.perform(click());

        try {
            Thread.sleep(1200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction button7 = onView(
                allOf(withText("Temptation"),
                        childAtPosition(
                                allOf(withId(R.id.layout_SongList),
                                        childAtPosition(
                                                withId(R.id.view_SongList),
                                                0)),
                                1)));
        button7.perform(scrollTo(), click());

        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatButton10 = onView(
                allOf(withId(R.id.btn_AddToPlaylist), withText("Add to Playlist"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatButton10.perform(click());

        try {
            Thread.sleep(1200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction tabView2 = onView(
                allOf(withContentDescription("New PlayList 1"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tabs_Playlist),
                                        0),
                                0)));
        tabView2.perform(scrollTo(), click());

        try {
            Thread.sleep(4800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
