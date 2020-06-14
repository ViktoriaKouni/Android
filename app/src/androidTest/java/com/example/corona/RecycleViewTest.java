package com.example.corona;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.example.corona.api.ResourceIdlng;
import com.example.corona.view.Activities.MainActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.hasChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.notNullValue;


@RunWith(AndroidJUnit4.class)
public class RecycleViewTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule=new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testRecycleView(){
        Espresso.onView(ViewMatchers.withId(R.id.countries)).check(ViewAssertions.matches(isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.countries)).check(ViewAssertions.matches(hasMinimumChildCount(1)));

    }

    @Before
    public void setUp(){
        MainActivity mainActivity=activityTestRule.getActivity();
        assertThat(mainActivity,notNullValue());
        IdlingRegistry.getInstance().register(ResourceIdlng.getInstance().countingIdlingResource);
    }

    @After
    public void unRegister(){
        IdlingRegistry.getInstance().unregister(ResourceIdlng.getInstance().countingIdlingResource);
    }

    @Test
    public void searchTest(){
        Espresso.onView(ViewMatchers.withId(R.id.countries)).check(ViewAssertions.matches(isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.countries)).check(ViewAssertions.matches(hasMinimumChildCount(1)));
        Espresso.onView(ViewMatchers.withId(R.id.search)).perform(click());
        Espresso.onView(isAssignableFrom(android.widget.SearchView.class)).perform(typeText("Greece"));
        Espresso.onView(ViewMatchers.withId(R.id.countries)).check(ViewAssertions.matches(hasChildCount(1)));
    }

    @Test
    public void countryClickTest(){
        Espresso.onView(ViewMatchers.withId(R.id.countries)).check(ViewAssertions.matches(isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.countries)).check(ViewAssertions.matches(hasMinimumChildCount(1)));
        Espresso.onView(allOf(ViewMatchers.withId(R.id.countries))).perform(actionOnItemAtPosition(1,click()));
        Espresso.onView(ViewMatchers.withId(R.id.tvCountryName)).check(ViewAssertions.matches(isDisplayed()));
    }

}
