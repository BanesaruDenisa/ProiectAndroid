package com.example.myapplication.UIAutomator;

import android.util.Log;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject2;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.Until;

import com.example.myapplication.UploadActivity;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class UploadUIAutomatorTest {

    private static long startTime;
    private static long endTime;
    private static long testStartTime;
    private static long testEndTime;
    private UiDevice device;
    private static final String PACKAGE_NAME = "com.example.myapplication";
    private static final int TIMEOUT = 5000;

    @Rule
    public ActivityScenarioRule<UploadActivity> activityScenarioRule =
            new ActivityScenarioRule<>(UploadActivity.class);

    @BeforeClass
    public static void startTimer() {
        startTime = System.nanoTime();
    }

    @AfterClass
    public static void endTimer() {
        endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1_000_000; // Convert to milliseconds
        Log.d("UIAutomatorTest", "Total duration for all tests: " + duration + " ms");
    }

    @Before
    public void setUp() {
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        testStartTime = System.nanoTime();
    }

    @After
    public void tearDown() {
        testEndTime = System.nanoTime();
        long testDuration = (testEndTime - testStartTime) / 1_000_000; // Convert to milliseconds
        Log.d("UIAutomatorTest", "Test duration: " + testDuration + " ms");
    }

    @Test
    public void testSelectImage() throws UiObjectNotFoundException {
        // Simulează selectarea unei imagini
        device.findObject(By.res(PACKAGE_NAME, "uploadImage")).click();
        device.wait(Until.hasObject(By.pkg("com.android.documentsui")), TIMEOUT);
        UiObject2 file = device.findObject(By.res("com.android.documentsui:id/date"));
        if (file != null) {
            file.click();
        } else {
            Log.d("UIAutomatorTest", "No image found");
        }
    }

    // Other tests follow the same structure for logging duration
}
