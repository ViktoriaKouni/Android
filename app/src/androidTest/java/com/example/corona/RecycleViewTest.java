package com.example.corona;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.example.corona.view.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static org.hamcrest.Matchers.notNullValue;


@RunWith(AndroidJUnit4.class)
public class RecycleViewTest {

    @Rule
    ActivityTestRule<MainActivity> activityTestRule=new ActivityTestRule(MainActivity.class);

    @Test
    public void testRecycleView(){
        Espresso.onView(ViewMatchers.withId(R.id.countries)).check(ViewAssertions.matches(isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.countries)).check(ViewAssertions.matches(hasMinimumChildCount(1)));

    }

    @Before
    public void setUp(){
        MainActivity mainActivity=activityTestRule.getActivity();
        assertThat(mainActivity,notNullValue());
    }

}
