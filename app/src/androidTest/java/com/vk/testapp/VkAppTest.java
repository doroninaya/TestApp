package com.vk.testapp;

import android.util.Log;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.SdkSuppress;

import com.vk.testapp.Suites.Acceptance;
import com.vk.testapp.Suites.Regression;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.vk.testapp.TestMatchers.VkAppMatchers.vkFriendsRecBtn;
import static com.vk.testapp.TestMatchers.VkAppMatchers.vkFriendsRecList;
import static com.vk.testapp.TestMatchers.VkAppMatchers.vkIcon;
import static com.vk.testapp.TestMatchers.VkAppMatchers.vkNegative;
import static com.vk.testapp.TestMatchers.VkAppMatchers.vkNewsTab;
import static com.vk.testapp.TestMatchers.VkAppMatchers.vkPhoto;
import static com.vk.testapp.TestMatchers.VkAppMatchers.vkPositive;
import static com.vk.testapp.TestMatchers.VkAppMatchers.vkSubtitle;
import static com.vk.testapp.TestMatchers.VkAppMatchers.vkSubtitle2;
import static com.vk.testapp.TestUtils.MemoryLogsUtil.MAX_PEAK_PSS_ALLOWED;
import static com.vk.testapp.TestUtils.TestHelpers.DEFAULT_TIMEOUT;
import static com.vk.testapp.TestUtils.TestHelpers.VK_PACKAGE_NAME;
import static com.vk.testapp.TestUtils.TestHelpers.getUsedMemorySize;
import static com.vk.testapp.TestUtils.TestHelpers.launchApp;
import static com.vk.testapp.TestUtils.TestHelpers.pressHome;
import static com.vk.testapp.TestUtils.TestHelpers.scrollUiObjectDown;
import static com.vk.testapp.TestUtils.TestHelpers.scrollUiObjectUp;
import static com.vk.testapp.TestUtils.TestHelpers.shouldSeeUiObject;
import static com.vk.testapp.TestUtils.TestHelpers.sleepStatement;
import static com.vk.testapp.TestUtils.TestHelpers.waitForApp;
import static android.content.ContentValues.TAG;

@RunWith(AndroidJUnit4.class)
@SdkSuppress(minSdkVersion = 21)

public class VkAppTest {
    @Before
    public void goToHomeScreen() {
        //Go to the home screen
        pressHome();
    }

    @Test
    @Regression
    public void vkFriendsRecListTest() {

        //Launch Vk application
        launchApp(VK_PACKAGE_NAME);

        //Wait for it
        waitForApp(VK_PACKAGE_NAME);

        //Find News Tab button and then click it
        shouldSeeUiObject(vkNewsTab());
        vkNewsTab()
                .click();
        //Find friends recommendation button and click it
        shouldSeeUiObject(vkFriendsRecBtn());
        vkFriendsRecBtn()
                .click();
        //Find scrollable list and scroll down
        shouldSeeUiObject(vkFriendsRecList());
        scrollUiObjectDown(vkFriendsRecList());

        //Check all friends matchers
        shouldSeeUiObject(vkPhoto(),
                vkNewsTab(),
                vkIcon(),
                vkSubtitle(),
                vkSubtitle2(),
                vkPositive(),
                vkNegative());

        //Scroll up
        shouldSeeUiObject(vkFriendsRecList());
        scrollUiObjectUp(vkFriendsRecList());

        //Check bottom menu matchers
        shouldSeeUiObject(vkPhoto(),
                vkNewsTab(),
                vkIcon(),
                vkSubtitle(),
                vkSubtitle2(),
                vkPositive(),
                vkNegative());
    }

    @Test
    @Acceptance
    public void vkMemoryCheckTest() {

        //Launch Vk application
        launchApp(VK_PACKAGE_NAME);

        //Wait for it
        waitForApp(VK_PACKAGE_NAME);

        //Wait for dumpsys
        Log.d(TAG, "wait 3 sec");
        sleepStatement(DEFAULT_TIMEOUT);

        //Check memory peak and return results
        long peakMemoryUsage = getUsedMemorySize(VK_PACKAGE_NAME);
        Log.i(TAG, "MEMORY IS " + peakMemoryUsage);
        Log.i(TAG, "maxPeakPssAllowed is " + MAX_PEAK_PSS_ALLOWED);

        Assert.assertTrue(String.format("memory usage for %s is too high: %d > %d", VK_PACKAGE_NAME,
                peakMemoryUsage, MAX_PEAK_PSS_ALLOWED), peakMemoryUsage < MAX_PEAK_PSS_ALLOWED);
    }
}
