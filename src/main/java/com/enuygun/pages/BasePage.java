package com.enuygun.pages;

import com.enuygun.constants.FrameworkConstants;
import io.qameta.allure.Step;

import static com.enuygun.utils.BrowserUtils.getCurrentUrl;
import static com.enuygun.utils.BrowserUtils.getPageTitle;
import static com.enuygun.utils.BrowserUtils.openWebsite;
import static com.enuygun.utils.BrowserUtils.verifyContains;
import static com.enuygun.utils.BrowserUtils.verifyEquals;

public class BasePage {

    @Step
    public void navigateToHomePage() {
        openWebsite(FrameworkConstants.URL);
        verifyContains(getCurrentUrl(), FrameworkConstants.URL, "The url of page not match. Actual URL: " +
                getCurrentUrl());
        verifyEquals(getPageTitle(), FrameworkConstants.HOMEPAGETITLE, "The title of page not match. Actual Title: " +
                getPageTitle());
    }
}
