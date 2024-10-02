package com.enuygun.common;

import com.enuygun.constants.FrameworkConstants;
import com.enuygun.driver.DriverManager;
import com.enuygun.driver.TargetFactory;
import com.enuygun.helpers.PropertiesHelpers;
import com.enuygun.listeners.TestListener;
import com.enuygun.utils.BrowserUtils;
import com.enuygun.utils.TerminalUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ThreadGuard;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.File;
import java.time.Duration;

@Listeners({TestListener.class})
public class BaseTest {

    private static final String projectPath = System.getProperty("user.dir");

    private static final File location = new File(projectPath);

    @Parameters("BROWSER")
    @BeforeMethod
    public void createDriver(@Optional("chrome") String browser) {
        WebDriver driver = ThreadGuard.protect(new TargetFactory().createInstance(browser));
        DriverManager.setDriver(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(FrameworkConstants.WAIT_IMPLICIT));
    }

    @AfterMethod(alwaysRun = true)
    public void closeDriver() {
        BrowserUtils.stopSoftAssertAll();
        DriverManager.quit();
    }

    public WebDriver createBrowser(@Optional("chrome") String browser) {
        PropertiesHelpers.loadAllFiles();
        WebDriver driver = ThreadGuard.protect(new TargetFactory().createInstance(browser));
        driver.manage().window().maximize();
        DriverManager.setDriver(driver);
        return DriverManager.getDriver();
    }

    @BeforeSuite(alwaysRun = true)
    public void removeReportHistory() {
        try {
            TerminalUtils.runCommand(location, "allure generate --clean --output allure-results");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @AfterSuite(alwaysRun = true)
    public void openAllureReport() {
        try {
            TerminalUtils.runCommand(location, "allure serve -h localhost");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}