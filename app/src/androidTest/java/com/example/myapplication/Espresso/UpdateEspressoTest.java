package com.example.myapplication.Espresso;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.IntentMatchers.isInternal;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNot.not;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.DatePicker;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.myapplication.R;
import com.example.myapplication.UpdateActivity;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Calendar;

@RunWith(AndroidJUnit4.class)
public class UpdateEspressoTest {

    @Rule
    public ActivityScenarioRule<UpdateActivity> activityScenarioRule = new ActivityScenarioRule<>(UpdateActivity.class);

    @Before
    public void setUp() {
        Intents.init();
        Intent startIntent = new Intent(ApplicationProvider.getApplicationContext(), UpdateActivity.class);
        Bundle extras = new Bundle();
        extras.putString("Key", "exampleKey");  // Example key, replace with actual if needed
        startIntent.putExtras(extras);
        startIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ApplicationProvider.getApplicationContext().startActivity(startIntent);
    }

//    @Test
//    public void testUpdateProcess() {
//        // Ensure the UI is idle before interacting
//        onIdle();
//
//        // Perform update actions, similar to what was done in DeleteEspressoTest
//        // Simulate opening the FloatingActionMenu or other necessary navigation
//        onView(withId(R.id.fab_menu)).perform(click());
////        onView(withId(R.id.fab_menu))
////                .perform(waitUntilVisible());
//
//        onView(withId(R.id.editButton)).perform(click());
//
//        // Perform field updates
////        onView(withId(R.id.updateTitle)).perform(scrollTo(), click(), replaceText("New Title"));
////        onView(withId(R.id.updateTitle)).check(matches(withText("New Title")));
////
////        onView(withId(R.id.updateDesc)).perform(scrollTo(), click(), replaceText("Updated Description"));
////        onView(withId(R.id.updateDesc)).check(matches(withText("Updated Description")));
////
////        onView(withId(R.id.updateBudg)).perform(scrollTo(), click(), replaceText("1500"));
////        onView(withId(R.id.updateBudg)).check(matches(withText("1500")));
////
////        // Assuming that there is a button to confirm the updates
////        onView(withId(R.id.updateButton)).perform(scrollTo(), click());
//
//        // Assuming update confirmation results in some UI change
//        // Here you would check for a Toast, a dialog, or a return to another activity
//    }

    @Test
    public void testSelectImage() {
        // Prepară un rezultat de intent cu o imagine mock
        Uri imageUri = Uri.parse("file:///sdcard/Download/food.png");

        Intent resultData = new Intent();
        resultData.setData(imageUri);
        Instrumentation.ActivityResult result = new Instrumentation.ActivityResult(Activity.RESULT_OK, resultData);

        // Intenționăm să răspundem cu acest rezultat când se lansează un picker de imagini
        intending(not(isInternal())).respondWith(result);

        // Simulează click pe ImageView pentru a lansa picker-ul de imagini
        onView(withId(R.id.updateImage)).perform(click());

        // Verifică dacă ImageView are imaginea setată
        // Acest pas necesită validare suplimentară sau mock pentru a verifica vizualizarea efectivă
    }

    @Test
    public void testFillTitle() {
        onView(withId(R.id.updateTitle)).perform(scrollTo(), click(), replaceText("TestTitle"));
        onView(withId(R.id.updateTitle)).check(matches(withText("TestTitle")));
    }

    @Test
    public void testFillDescription() {
        onView(withId(R.id.updateDesc)).perform(scrollTo(), click(), replaceText("Test Description"));
        onView(withId(R.id.updateDesc)).check(matches(withText("Test Description")));
    }

    @Test
    public void testFillBudget() {
        onView(withId(R.id.updateBudg)).perform(scrollTo(), click(), replaceText("1000"));
        onView(withId(R.id.updateBudg)).check(matches(withText("1000")));
    }

    @Test
    public void testSelectTodayDate() {
        // Deschide dialogul DatePicker
        Espresso.onView(withId(R.id.buttonDate))
                .perform(ViewActions.click());

        // Selectează data curentă în dialogul DatePicker
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        // Setează data în DatePickerDialog
        Espresso.onView(ViewMatchers.withClassName(Matchers.equalTo(DatePicker.class.getName())))
                .inRoot(RootMatchers.isDialog()) // asigură-te că interacționezi cu dialogul
                .perform(PickerActions.setDate(year, month + 1, day));

        // Apasă pe butonul OK pentru a confirma data
        Espresso.onView(withId(android.R.id.button1))
                .inRoot(RootMatchers.isDialog())
                .perform(ViewActions.click());

        // Verifică dacă TextView-ul care arată data a fost actualizat (asumând că ai un format specific de dată)
        String expectedDate = String.format("%02d-%02d-%d", day, month + 1, year); // ajustează formatul conform aplicației tale
        Espresso.onView(withId(R.id.updateDate))
                .check(ViewAssertions.matches(ViewMatchers.withText(expectedDate)));
    }


    @After
    public void tearDown() {
        Intents.release();
    }
}
