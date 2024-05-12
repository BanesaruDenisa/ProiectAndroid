package com.example.myapplication.Espresso;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import android.content.Intent;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.myapplication.DetailActivity;
import com.example.myapplication.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class DeleteEspressoTest {

    @Rule
    public ActivityScenarioRule<DetailActivity> activityScenarioRule = new ActivityScenarioRule<>(DetailActivity.class);

    @Before
    public void setUp() {
        Intents.init();
        Intent startIntent = new Intent(ApplicationProvider.getApplicationContext(), DetailActivity.class);
        startIntent.putExtra("Key", "exampleKey");
        // Nu transmitem imageUrl pentru a simula scenarii fără imagine.
        activityScenarioRule.getScenario().onActivity(activity -> activity.startActivity(startIntent));
    }

    @Test
    public void testTitleVisibility() {
        onView(withId(R.id.detailTitle)).check(matches(isCompletelyDisplayed()));
    }

    @Test
    public void testFabMenuAccessibility() {
        onView(withId(R.id.fab_menu)).perform(click());
        onView(withId(R.id.deleteButton)).check(matches(isDisplayed()));
    }

    @Test
    public void testDeleteButtonVisibility() {
        onView(withId(R.id.fab_menu)).perform(click());
        onView(withId(R.id.deleteButton)).check(matches(isCompletelyDisplayed()));
    }

    @Test
    public void testDeleteFunctionality() throws InterruptedException {
        long startTime = System.nanoTime();
        onView(withId(R.id.fab_menu)).perform(click());
        Thread.sleep(2000);
        //onView(withId(R.id.deleteButton)).perform(click());
        onView(withId(R.id.deleteButton)).check(matches(isDisplayed())).perform(click());
        Thread.sleep(2000); // Așteaptă pentru a verifica navigația

        // Verifică dacă RecyclerView-ul din MainActivity este vizibil
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()));

        long endTime = System.nanoTime(); // Oprire cronometrare
        long duration = (endTime - startTime) / 1_000_000; // Convertire nanosecunde în milisecunde
        System.out.println("Test duration: " + duration + " ms");
    }

    @After
    public void tearDown() {
        Intents.release();
    }
}
