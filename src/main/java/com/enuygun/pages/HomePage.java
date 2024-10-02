package com.enuygun.pages;

import com.enuygun.driver.DriverManager;
import com.enuygun.enums.MonthConverterEnum;
import com.enuygun.utils.BrowserUtils;
import com.enuygun.utils.DateUtils;
import com.enuygun.utils.LogUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

import static com.enuygun.utils.BrowserUtils.checkElementExists;
import static com.enuygun.utils.BrowserUtils.clickElement;
import static com.enuygun.utils.BrowserUtils.getTextElement;
import static com.enuygun.utils.BrowserUtils.getWebElements;
import static com.enuygun.utils.BrowserUtils.setText;
import static com.enuygun.utils.BrowserUtils.verifyElementNotVisible;
import static com.enuygun.utils.BrowserUtils.verifyElementVisible;


public class HomePage extends BasePage {

    By radioBtnRoundTrip = By.cssSelector("div[data-testid='search-round-trip-text']");
    By comboboxFlightOrigin = By.cssSelector("div[data-testid='endesign-flight-origin-autosuggestion']");
    By textAreaFlightOriginInput = By.cssSelector("input[data-testid='endesign-flight-origin-autosuggestion-input']");
    By flightOriginAutoSuggestionFirstOption = By.xpath("(//ul[@data-testid='endesign-flight-origin-autosuggestion-options']//li)[1]");
    By comboboxFlightDestination = By.cssSelector("div[data-testid='endesign-flight-destination-autosuggestion']");
    By textAreaFlightDestinationInput = By.cssSelector("input[data-testid='endesign-flight-destination-autosuggestion-input']");
    By flightDestinationAutoSuggestionFirstOption = By.xpath("//ul[@data-testid='endesign-flight-destination-autosuggestion-options']//li");
    By btnDepartureDateMonthForward = By.cssSelector("button[data-testid='enuygun-homepage-flight-departureDate-month-forward-button']");
    By btnDepartureDateMonthBack = By.cssSelector("button[data-testid='enuygun-homepage-flight-departureDate-month-back-button']");
    By departureDatePicker = By.cssSelector("div[data-testid='enuygun-homepage-flight-departureDate-datepicker-popover-button']");
    By textDepartureDateMonthNameAndYear = By.cssSelector("h3[data-testid='enuygun-homepage-flight-departureDate-month-name-and-year']");
    By btnReturnDatePicker = By.cssSelector("div[data-testid='enuygun-homepage-flight-returnDate-datepicker-popover-button']");
    By textReturnDateMonthAndYear = By.cssSelector("h3[data-testid='enuygun-homepage-flight-returnDate-month-name-and-year']");
    By btnReturnDateMonthForward = By.cssSelector("button[data-testid='enuygun-homepage-flight-returnDate-month-forward-button']");
    By btnFlightSubmit = By.cssSelector("button[data-testid='enuygun-homepage-flight-submitButton']");
    By textFlightList = By.xpath("//div[@class='flight-list-body']/div");


    @Step
    public void selectRoundTripFlight() {
        clickElement(radioBtnRoundTrip);
    }

    @Step
    public void selectOriginLocation(String originLocation) {
        clickElement(comboboxFlightOrigin);
        setText(textAreaFlightOriginInput, originLocation);
        clickElement(flightOriginAutoSuggestionFirstOption);
    }

    @Step
    public void selectDestinationLocation(String destinationLocation) {
        clickElement(comboboxFlightDestination);
        setText(textAreaFlightDestinationInput, destinationLocation);
        clickElement(flightDestinationAutoSuggestionFirstOption);
    }

    @Step
    public void selectFlightDepartureDate(String year, String month, String day) {
        String departureDateSelector = "//div[@data-testid='enuygun-homepage-flight-departureDate']/div/div/div";
        clickElement(departureDatePicker);
        verifyDatePickerContainsCurrentMonthCalender(departureDateSelector);
        selectSpecifiedDateInDatePicker(textDepartureDateMonthNameAndYear, btnDepartureDateMonthForward, year, month, day);
    }

    public void verifyDatePickerContainsCurrentMonthCalender(String selector) {
        List<WebElement> calenders = DriverManager.getDriver().findElements(By.xpath(selector));
        if (calenders.isEmpty()) {
            LogUtils.error("Calenders list is empty");
        }
        verifyElementNotVisible(btnDepartureDateMonthBack);
        By firstCalendarSelector = By.id(String.format("calendar-month-%s-%s", DateUtils.getCurrentYear(), DateUtils.getCurrentMonth()));
        By secondCalendarSelector = By.id(String.format("calendar-month-%s-%02d", DateUtils.getCurrentYear(), Integer.parseInt(DateUtils.getCurrentMonth()) + 1));
        verifyElementVisible(firstCalendarSelector);
        verifyElementVisible(secondCalendarSelector);
    }

    public void selectSpecifiedDateInDatePicker(By by, By calenderForwardButton, String year, String month, String day) {
        for (int i = 0; i < 12; i++) {
            boolean isDateFound = getWebElements(by).stream()
                    .map(BrowserUtils::getTextElement)  // WebElement'ten text değerini alır
                    .anyMatch(text -> text.equals(MonthConverterEnum.Month.getMonthName(month) + " " + year));  // Eşleşme kontrolü yapar

            if (isDateFound) {
                clickElement(By.cssSelector("button[title='" + year + "-" + month + "-" + day + "']"));
                return;
            }
            // Eğer aranan tarih bulunamadıysa takvimi ileriye taşımak için forward butonuna tıkla
            clickElement(calenderForwardButton);
        }
        LogUtils.error("Date could not be selected");
        Assert.fail("Date could not be selected");

    }

    @Step
    public void selectFlightReturnDate(String year, String month, String day) {
        clickElement(btnReturnDatePicker);
        selectSpecifiedDateInDatePicker(textReturnDateMonthAndYear, btnReturnDateMonthForward, year, month, day);
    }

    @Step
    public void searchFlight() {
        clickElement(btnFlightSubmit);
        Assert.assertTrue(checkElementExists(textFlightList));
        LogUtils.info("Search flight found");
    }
}
