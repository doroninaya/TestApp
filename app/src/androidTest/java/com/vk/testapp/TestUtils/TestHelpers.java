package com.vk.testapp.TestUtils;

import android.content.Context;
import android.content.Intent;

import androidx.test.uiautomator.By;
import androidx.test.uiautomator.Direction;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject2;
import androidx.test.uiautomator.Until;

import junit.framework.AssertionFailedError;

import org.junit.Assert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static com.vk.testapp.TestUtils.MemoryLogsUtil.getMemoryLogs;
import static java.lang.String.format;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class TestHelpers {
    public static UiDevice mDevice = UiDevice.getInstance(getInstrumentation());

    public static final String VK_PACKAGE_NAME = "com.vkontakte.android";

    public static final int DEFAULT_TIMEOUT = 3000;
    public static final int SCROLL_TIMEOUT = 1000;

    public static final int APP_LAUNCH_TIMEOUT = 1500;

    private static final int CENTER_OF_SCREEN = mDevice.getDisplayWidth() / 2;
    private static final int RIGHT_POINT_OF_SCREEN = mDevice.getDisplayWidth() * 9 / 10;
    private static final int LEFT_POINT_OF_SCREEN = mDevice.getDisplayWidth() / 10;

    public static void sleepStatement(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static String executeShellCommand(String shellCommand) {
        String result = null;
        try {
            result = TestHelpers.mDevice.executeShellCommand(shellCommand);
        } catch (IOException e) {
            fail(format("Failed execute shell command: %s", shellCommand));
        }
        return result;
    }

    public static void waitForApp(String pkg) {
        mDevice.wait(Until.hasObject(By.pkg(pkg).depth(0)), APP_LAUNCH_TIMEOUT);
    }

    public static void launchApp(String pkg) {
        Context context = getApplicationContext();
        final Intent intent = context.getPackageManager()
                .getLaunchIntentForPackage(pkg);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    public static void shouldSeeUiObject(UiObject2... ob) {
        for (UiObject2 item : ob) {
            try {
                assertNotNull(item);
            } catch (AssertionFailedError e) {
                fail("UiObject is not found!");
            }
        }
    }

    public static UiObject2 findUiObjectById(String vk, String id) {
        return mDevice.wait(Until.findObject(By.res(vk, id)), DEFAULT_TIMEOUT);
    }

    public static void scrollUiObjectDown(UiObject2 object) {
        object.scroll(Direction.DOWN, 1f);
        sleepStatement(SCROLL_TIMEOUT);
    }

    public static void scrollUiObjectUp(UiObject2 object) {
        object.scroll(Direction.UP, 1f);
        sleepStatement(SCROLL_TIMEOUT);
    }

    public static boolean pressHome() {
        return mDevice.pressHome();
    }

    public static boolean pressBack() { return mDevice.pressBack(); }

    public static Long getUsedMemorySize(String packageName) {
        String dumpsysCommand = format("dumpsys -t 30 meminfo --package %s", packageName);
        String output = executeShellCommand(dumpsysCommand);
        ArrayList<String> usages = new ArrayList<>();
        Matcher matcher = Pattern.compile("TOTAL\\s+([\\d]+)").matcher(output);
        while (matcher.find()) {
            usages.add(matcher.group(1));
        }
        Assert.assertTrue("Could not get meminfo total for " + packageName, !usages.isEmpty());
        long totalMemory = usages.stream().mapToLong(Long::valueOf).sum();

        getMemoryLogs(output, totalMemory);

        return totalMemory;
    }
}