package com.enuygun.pages;

import com.enuygun.constants.FrameworkConstants;
import com.enuygun.utils.LogUtils;
import com.google.common.collect.Ordering;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;
import java.util.Objects;

import static com.enuygun.utils.BrowserUtils.actionDragAndDropBy;
import static com.enuygun.utils.BrowserUtils.clickElement;
import static com.enuygun.utils.BrowserUtils.getListElementsText;
import static com.enuygun.utils.BrowserUtils.getTextElement;
import static com.enuygun.utils.BrowserUtils.getWebElement;
import static com.enuygun.utils.BrowserUtils.getWebElements;
import static com.enuygun.utils.BrowserUtils.scrollToElement;
import static com.enuygun.utils.BrowserUtils.waitLoader;

public class SearchPage extends BasePage {


    By btnDepartureReturnTimeFilter = By.xpath("//span[text()='Gidiş kalkış / varış saatleri']");
    By sliderDepartureAndArrivalTimeIncrease = By.cssSelector("div[class='rc-slider-handle rc-slider-handle-1']");
    By sliderDepartureAndArrivalTimeDecrease = By.cssSelector("div[class='rc-slider-handle rc-slider-handle-2']");
    By sliderDepartureTime = By.cssSelector("div[data-testid='departureDepartureTimeSlider']");
    By textFlightsDepartureTimes = By.cssSelector("div[data-testid='departureTime']");
    By textSliderDepartureTime = By.cssSelector("div[class='filter-slider-content']");

    @Step
    public void selectDepartureTimeFromFilter(int startTime, int endTime) {
        clickElement(btnDepartureReturnTimeFilter);
        setTimeSlider(sliderDepartureTime, startTime, endTime, getWebElements(sliderDepartureAndArrivalTimeIncrease).getFirst(),
                getWebElements(sliderDepartureAndArrivalTimeDecrease).getFirst());
        verifyDepartureTimeFromText(String.valueOf(startTime), String.valueOf(endTime));
    }

    @Step
    private void setTimeSlider(By sliderDepartureOrArrival, int startTime, int endTime, WebElement sliderIncrease, WebElement sliderDecrease) {
        actionDragAndDropBy(sliderIncrease, calculateSliderStartTimeXOffset(sliderDepartureOrArrival, startTime), 0);
        waitLoader(FrameworkConstants.WAIT_DEFAULT);
        actionDragAndDropBy(sliderDecrease, calculateSliderEndTimeXOffset(sliderDepartureOrArrival, endTime), 0);
        waitLoader(FrameworkConstants.WAIT_DEFAULT);
    }

    private int calculateSliderStartTimeXOffset(By by, int startTime) {
        int sliderWidth = getWebElement(by).getSize().getWidth();
        return sliderWidth / 24 * startTime;
    }

    private int calculateSliderEndTimeXOffset(By by, int endTime) {
        int sliderWidth = getWebElement(by).getSize().getWidth();
        return -(sliderWidth - (sliderWidth / 24 * endTime));
    }

    @Step
    private void verifyDepartureTimeFromText(String expectedStartTime, String expectedEndTime) {
        String[] times = getTextElement(getWebElements(textSliderDepartureTime).getFirst()).split(" ");
        String departureHour = times[0].split(":")[0];
        //check after : part
        Assert.assertEquals(times[0].split(":")[1], "00");
        String arrivalHour = times[2].split(":")[0];
        //check after the ":" part
        Assert.assertEquals(times[2].split(":")[1], "00");
        Assert.assertEquals(departureHour, expectedStartTime, "Error: Expected: " + expectedStartTime + ", Actual: " + departureHour);
        Assert.assertEquals(arrivalHour, expectedEndTime, "Error: Expected: " + expectedEndTime + ", Actual: " + arrivalHour);
    }

    @Step
    public void assertFlightsDepartureTimes(int expectedStartTime, int expectedEndTime) {
        List<String> dates = getListElementsText(textFlightsDepartureTimes);
        Assert.assertFalse(dates.isEmpty());
        dates.forEach(date -> {
            int departureTime = Integer.parseInt(date.split(":")[0]);
            int departureMinutes = Integer.parseInt(date.split(":")[1]);
            if (departureTime == expectedEndTime) {
                Assert.assertEquals(departureMinutes, 0);
            }
            LogUtils.info("Expected Start: " + expectedStartTime + ", Actual: " + departureTime + ", Expected End: " + expectedEndTime);
            Assert.assertTrue(departureTime >= expectedStartTime && departureTime <= expectedEndTime,
                    "Expected Start: " + expectedStartTime + ", Actual: " + departureTime + ", Expected End: " + expectedEndTime);
        });
    }

    @Step
    public void assertAirlineFlightPricesSortingAscendingOrder(String airlineName) {
        List<WebElement> elements = getWebElements(By.cssSelector("div[data-booking-provider='" + airlineName + "']"));
        LogUtils.info(airlineName + " flight count: " + elements.size());
        Assert.assertFalse(elements.isEmpty());
        assertFlightPricesAscendingSorting(elements);
    }

    private void assertFlightPricesAscendingSorting(List<WebElement> flights) {
        List<Double> flightPrices = flights.stream()
                .map(this::getFlightPrice)
                .toList();

        Assert.assertTrue(Ordering.natural().isOrdered(flightPrices),
                "Prices is not sorted in ascending order. Actual Data: " + flightPrices);

        LogUtils.info("Airlines flight prices sorted in ascending order successfully.");
    }

    private double getFlightPrice(WebElement flight) {
        WebElement flightPriceElement = getFlightPriceElement(flight);
        scrollToElement(flightPriceElement);
        return Double.parseDouble(Objects.requireNonNull(flightPriceElement.getAttribute("data-price")));
    }

    private WebElement getFlightPriceElement(WebElement flight) {
        String flightPriceLocator = "//div[@data-testid='flightInfoPrice']";
        return flight.findElement(By.xpath(flightPriceLocator));
    }
}
