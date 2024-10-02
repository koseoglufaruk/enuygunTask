package com.enuygun.listeners;

import com.enuygun.driver.DriverManager;
import io.qameta.allure.Allure;
import io.qameta.allure.listener.TestLifecycleListener;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.TestResult;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.ByteArrayInputStream;

import static com.enuygun.constants.FrameworkConstants.SCREENSHOT_FAILED_TCS;
import static com.enuygun.constants.FrameworkConstants.SCREENSHOT_PASSED_TCS;
import static com.enuygun.constants.FrameworkConstants.YES;

public class AllureListener implements TestLifecycleListener {

    @Override
    public void beforeTestSchedule(TestResult result) {
    }

    @Override
    public void afterTestSchedule(TestResult result) {
    }

    @Override
    public void beforeTestUpdate(TestResult result) {
    }

    @Override
    public void afterTestUpdate(TestResult result) {
    }

    @Override
    public void beforeTestStart(TestResult result) {
    }

    @Override
    public void afterTestStart(TestResult result) {
    }

    @Override
    public void beforeTestStop(TestResult result) {
        if (SCREENSHOT_PASSED_TCS.equals(YES) && result.getStatus().equals(Status.PASSED)) {

            if (DriverManager.getDriver() != null) {

                Allure.addAttachment(result.getName() + "_Passed_Screenshot", new ByteArrayInputStream(((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES)));
            }
        }

        if (SCREENSHOT_FAILED_TCS.equals(YES) && result.getStatus().equals(Status.FAILED)) {
            if (DriverManager.getDriver() != null) {
                Allure.addAttachment(result.getName() + "_Failed_Screenshot", new ByteArrayInputStream(((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES)));
            }
        }

        //AllureManager.addAttachmentVideoMP4();
    }

    @Override
    public void afterTestStop(TestResult result) {
    }

    @Override
    public void beforeTestWrite(TestResult result) {
    }

    @Override
    public void afterTestWrite(TestResult result) {
    }

}
