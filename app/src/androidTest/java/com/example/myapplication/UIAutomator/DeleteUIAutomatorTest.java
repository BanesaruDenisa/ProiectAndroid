package com.example.myapplication.UIAutomator;

import android.content.Intent;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiSelector;
import androidx.test.uiautomator.Until;

import com.example.myapplication.DetailActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class DeleteUIAutomatorTest {

    private UiDevice device;

    @Before
    public void setUp() {
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

        Intent intent = new Intent(ApplicationProvider.getApplicationContext(), DetailActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        intent.putExtra("Key", "exampleKey");
        ApplicationProvider.getApplicationContext().startActivity(intent);

        device.waitForIdle();
    }

    @Test
    public void testDeleteAction() throws UiObjectNotFoundException {
        long startTime = System.nanoTime();

        device.wait(Until.hasObject(By.pkg("com.example.myapplication")), 5000);

        UiObject fabMenuButton = device.findObject(new UiSelector().resourceId("com.example.myapplication:id/fab_menu"));
        if (fabMenuButton.exists() && fabMenuButton.isClickable()) {
            fabMenuButton.click();
        }

        UiObject deleteButton = device.findObject(new UiSelector().resourceId("com.example.myapplication:id/deleteButton"));
        if (deleteButton.exists() && deleteButton.isClickable()) {
            deleteButton.click();
        }

        UiObject detailView = device.findObject(new UiSelector().resourceId("com.example.myapplication:id/detail"));
        if (!detailView.exists()) {
            System.out.println("Deletion confirmed, detail view no longer visible.");
        } else {
            System.out.println("Detail view is still visible. Deletion may not have been successful.");
        }

        long endTime = System.nanoTime(); // Oprire cronometrare
        long duration = (endTime - startTime) / 1_000_000; // Convertire nanosecunde în milisecunde
        System.out.println("Test duration: " + duration + " ms");
    }
}
