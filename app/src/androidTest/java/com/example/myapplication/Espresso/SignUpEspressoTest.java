package com.example.myapplication.Espresso;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.myapplication.LoginActivity;
import com.example.myapplication.R;
import com.example.myapplication.SignUpActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class SignUpEspressoTest {

    @Rule
    public ActivityScenarioRule<SignUpActivity> activityScenarioRule = new ActivityScenarioRule<>(SignUpActivity.class);

    @Before
    public void setUp() {
        Intents.init(); // Inițializare Intents pentru a monitoriza navigațiile
    }

    @Test
    public void testUserSignUp() {
        long startTime = System.nanoTime(); // Începe cronometrarea

        // Introducere detalii utilizator
        onView(withId(R.id.signup_email)).perform(typeText("test@example.com"), closeSoftKeyboard());
        onView(withId(R.id.signup_password)).perform(typeText("password123"), closeSoftKeyboard());

        // Apasă pe butonul de înregistrare
        onView(withId(R.id.signup_button)).perform(click());

        // Verifică dacă navighează către activitatea următoare
        intending(hasComponent(LoginActivity.class.getName()));

        long endTime = System.nanoTime(); // Oprire cronometrare
        long duration = (endTime - startTime) / 1_000_000; // Convertire nanosecunde în milisecunde
        System.out.println("Test duration: " + duration + " ms");
    }

    @After
    public void tearDown() {
        Intents.release(); // Curăță Intents după fiecare test
    }
}
