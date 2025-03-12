package com.company.tests.product;

import com.company.pages.HomePage;
import com.company.pages.NavigationBar;
import com.company.tests.BaseTest;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.Test;

@Log4j2
public class HomePageTest extends BaseTest {

    protected HomePage homePage;
    protected NavigationBar navigationBar;

    @Test
    public void verifyHomePageTest(){
        homePage = new HomePage(driver);
        navigationBar = new NavigationBar(driver);
        homePage.navigateToHomePage();
        homePage.verifyHomeTextIsDisplayed();
        navigationBar.clickOnHomeButton();
    }


}
