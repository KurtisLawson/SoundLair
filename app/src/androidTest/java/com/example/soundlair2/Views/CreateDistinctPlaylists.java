package com.example.soundlair2.Views;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.rule.GrantPermissionRule;
import androidx.test.runner.AndroidJUnit4;

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
public class CreateDistinctPlaylists {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Rule
    public GrantPermissionRule mGrantPermissionRule =
            GrantPermissionRule.grant(
                    "android.permission.WRITE_EXTERNAL_STORAGE");

    @Test
    public void createDistinctPlaylists() {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.btn_AddActiveTrack), withText("+ Track"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                4),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction button = onView(
                allOf(withText("The Hunted"),
                        childAtPosition(
                                allOf(withId(R.id.layout_SongList),
                                        childAtPosition(
                                                withId(R.id.view_SongList),
                                                0)),
                                0)));
        button.perform(scrollTo(), click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.btn_AddToPlaylist), withText("Add to Playlist"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.btn_AddAmbientTrack), withText("+ Ambience"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                5),
                        isDisplayed()));
        appCompatButton3.perform(click());

        ViewInteraction button2 = onView(
                allOf(withText("sfx_Rain"),
                        childAtPosition(
                                allOf(withId(R.id.layout_SongList),
                                        childAtPosition(
                                                withId(R.id.view_SongList),
                                                0)),
                                2)));
        button2.perform(scrollTo(), click());

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.btn_AddToPlaylist), withText("Add to Playlist"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatButton4.perform(click());

        ViewInteraction appCompatButton5 = onView(
                allOf(withId(R.id.btn_AddPlaylist), withText("+"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatButton5.perform(click());

        ViewInteraction tabView = onView(
                allOf(withContentDescription("New PlayList 2"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tabs_Playlist),
                                        0),
                                1)));
        tabView.perform(scrollTo(), click());

        ViewInteraction appCompatButton6 = onView(
                allOf(withId(R.id.btn_AddActiveTrack), withText("+ Track"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                4),
                        isDisplayed()));
        appCompatButton6.perform(click());

        ViewInteraction button3 = onView(
                allOf(withText("Temptation"),
                        childAtPosition(
                                allOf(withId(R.id.layout_SongList),
                                        childAtPosition(
                                                withId(R.id.view_SongList),
                                                0)),
                                1)));
        button3.perform(scrollTo(), click());

        ViewInteraction appCompatButton7 = onView(
                allOf(withId(R.id.btn_AddToPlaylist), withText("Add to Playlist"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatButton7.perform(click());

        ViewInteraction appCompatButton8 = onView(
                allOf(withId(R.id.btn_AddAmbientTrack), withText("+ Ambience"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                5),
                        isDisplayed()));
        appCompatButton8.perform(click());

        ViewInteraction button4 = onView(
                allOf(withText("sfx_ForestWind"),
                        childAtPosition(
                                allOf(withId(R.id.layout_SongList),
                                        childAtPosition(
                                                withId(R.id.view_SongList),
                                                0)),
                                3)));
        button4.perform(scrollTo(), click());

        ViewInteraction appCompatButton9 = onView(
                allOf(withId(R.id.btn_AddToPlaylist), withText("Add to Playlist"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatButton9.perform(click());

        ViewInteraction tabView2 = onView(
                allOf(withContentDescription("New PlayList 1"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tabs_Playlist),
                                        0),
                                0)));
        tabView2.perform(scrollTo(), click());

        ViewInteraction button5 = onView(
                allOf(withText("The Hunted"),
                        childAtPosition(
                                allOf(withId(R.id.layout_TrackList),
                                        childAtPosition(
                                                withId(R.id.view_TrackList),
                                                0)),
                                0)));
        button5.perform(scrollTo(), click());

        ViewInteraction tabView3 = onView(
                allOf(withContentDescription("New PlayList 2"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tabs_Playlist),
                                        0),
                                1)));
        tabView3.perform(scrollTo(), click());

        ViewInteraction button6 = onView(
                allOf(withText("Temptation"),
                        childAtPosition(
                                allOf(withId(R.id.layout_TrackList),
                                        childAtPosition(
                                                withId(R.id.view_TrackList),
                                                0)),
                                0)));
        button6.perform(scrollTo(), click());

        ViewInteraction tabView4 = onView(
                allOf(withContentDescription("New PlayList 1"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tabs_Playlist),
                                        0),
                                0)));
        tabView4.perform(scrollTo(), click());
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
