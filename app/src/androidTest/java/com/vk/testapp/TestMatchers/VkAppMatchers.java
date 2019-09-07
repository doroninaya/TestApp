package com.vk.testapp.TestMatchers;

import androidx.test.uiautomator.UiObject2;

import static com.vk.testapp.TestUtils.TestHelpers.VK_PACKAGE_NAME;
import static com.vk.testapp.TestUtils.TestHelpers.findUiObjectById;

public class VkAppMatchers {
    //Vk friends recommendation button
    public static UiObject2 vkFriendsRecBtn() {
        return findUiObjectById(VK_PACKAGE_NAME, "recom_friends_btn");
    }

    //Vk news tab button
    public static UiObject2 vkNewsTab() {
        return findUiObjectById(VK_PACKAGE_NAME, "tab_news");
    }

    //Vk discover tab button
    public static UiObject2 vkDiscoverTab() {
        return findUiObjectById(VK_PACKAGE_NAME, "tab_discover");
    }

    //Vk messages tab button
    public static UiObject2 vkMessagesTab() {
        return findUiObjectById(VK_PACKAGE_NAME, "tab_messages");
    }

    //Vk feedback tab button
    public static UiObject2 vkFeedbackTab() {
        return findUiObjectById(VK_PACKAGE_NAME, "tab_feedback");
    }

    //Vk menu tab button
    public static UiObject2 vkMenuTab() {
        return findUiObjectById(VK_PACKAGE_NAME, "tab_menu");
    }

    //Vk scrollable list
    public static UiObject2 vkFriendsRecList() {
        return findUiObjectById(VK_PACKAGE_NAME, "rpb_list");
    }
    //Vk friends recommendation list matchers
    public static UiObject2 vkPhoto() {
        return findUiObjectById(VK_PACKAGE_NAME, "photo");
    }

    public static UiObject2 vkIcon() {
        return findUiObjectById(VK_PACKAGE_NAME, "icon");
    }

    public static UiObject2 vkSubtitle() {
        return findUiObjectById(VK_PACKAGE_NAME, "subtitle");
    }

    public static UiObject2 vkSubtitle2() {
        return findUiObjectById(VK_PACKAGE_NAME, "subtitle2");
    }

    public static UiObject2 vkPositive() {
        return findUiObjectById(VK_PACKAGE_NAME, "positive");
    }

    public static UiObject2 vkNegative() {
        return findUiObjectById(VK_PACKAGE_NAME, "negative");
    }
}