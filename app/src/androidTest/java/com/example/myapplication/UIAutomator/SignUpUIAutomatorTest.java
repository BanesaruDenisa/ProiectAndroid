package com.example.myapplication.UIAutomator;

import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

import static org.junit.Assert.assertTrue;

import android.content.Context;
import android.content.Intent;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.SdkSuppress;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject2;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.Until;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@SdkSuppress(minSdkVersion = 18)
public class SignUpUIAutomatorTest {

    private UiDevice device;

    @Before
    public void setUp() {
        // Inițializează UiDevice instance
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
    }

    @Before
    public void startMainActivityFromHomeScreen() {
        device.pressHome();
        Context context = getInstrumentation().getTargetContext();
        Intent intent = context.getPackageManager().getLaunchIntentForPackage("com.example.myapplication");
        if (intent != null) {
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
        device.wait(Until.hasObject(By.pkg("com.example.myapplication").depth(0)), 5000);
    }


    @Test
    public void testSignUp() throws UiObjectNotFoundException {
        long startTime = System.nanoTime();
        // Așteaptă și verifică că câmpul de email este prezent
        UiObject2 emailField = device.wait(Until.findObject(By.res("com.example.myapplication:id/signup_email")), 5000);
        if (emailField == null) {
            throw new AssertionError("Signup email field not found.");
        }
        emailField.setText("testUI@example.com");

        UiObject2 passwordField = device.wait(Until.findObject(By.res("com.example.myapplication:id/signup_password")), 5000);
        if (passwordField == null) {
            throw new AssertionError("Signup password field not found.");
        }
        passwordField.setText("password1234");

        UiObject2 signUpButton = device.wait(Until.findObject(By.res("com.example.myapplication:id/signup_button")), 5000);
        if (signUpButton == null) {
            throw new AssertionError("Signup button not found.");
        }
        signUpButton.click();

        // Așteaptă pentru interfața de login să apară
        boolean isLoginDisplayed = device.wait(Until.hasObject(By.res("com.example.myapplication:id/login_email")), 10000);

        // Verifică dacă interfața de login este afișată
        assertTrue("Login screen did not display", isLoginDisplayed);

        long endTime = System.nanoTime(); // Oprire cronometrare
        long duration = (endTime - startTime) / 1_000_000; // Convertire nanosecunde în milisecunde
        System.out.println("Test duration: " + duration + " ms");
    }

}
