package com.enuygun.tests;

import com.enuygun.common.BaseTest;
import com.enuygun.pages.HomePage;
import com.enuygun.pages.SearchPage;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;

@Epic("Regression Test")
@Feature("Add Flight")
public class EnUygunTask extends BaseTest {

    @Test
    public void testDepartureTime() {
        HomePage homePage = new HomePage();
        homePage.navigateToHomePage();
        homePage.selectRoundTripFlight();
        homePage.selectOriginLocation("Istanbul");
        homePage.selectDestinationLocation("Ankara");
        homePage.selectFlightDepartureDate("2024", "12", "12");
        homePage.selectFlightReturnDate("2025", "01", "18");
        homePage.searchFlight();
        SearchPage searchPage = new SearchPage();
        searchPage.selectDepartureTimeFromFilter(10, 18);
        searchPage.assertFlightsDepartureTimes(10, 18);
    }

    @Test
    public void testSortingByIncreasingPrice() {
        HomePage homePage = new HomePage();
        homePage.navigateToHomePage();
        homePage.selectRoundTripFlight();
        homePage.selectOriginLocation("Istanbul");
        homePage.selectDestinationLocation("Ankara");
        homePage.selectFlightDepartureDate("2024", "12", "12");
        homePage.selectFlightReturnDate("2025", "01", "18");
        homePage.searchFlight();
        SearchPage searchPage = new SearchPage();
        searchPage.selectDepartureTimeFromFilter(10, 18);
        searchPage.assertFlightsDepartureTimes(10, 18);
        searchPage.assertAirlineFlightPricesSortingAscendingOrder("thy");
    }

    @Test
    public void testSearchFlights() {
        HomePage homePage = new HomePage();
        homePage.navigateToHomePage();
        homePage.selectRoundTripFlight();
        homePage.selectOriginLocation("Istanbul");
        homePage.selectDestinationLocation("Ankara");
        homePage.selectFlightDepartureDate("2024", "12", "12");
        homePage.selectFlightReturnDate("2025", "01", "18");
        homePage.searchFlight();
    }


}
