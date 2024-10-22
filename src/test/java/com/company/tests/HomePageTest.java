package com.company.tests;

import com.company.pages.HomePage;
import com.company.pages.NavigationBar;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Log4j2
public class HomePageTest extends BaseTest {

    protected HomePage homePage;
    protected NavigationBar navigationBar;

    @BeforeMethod
    public void initiatePages(){
        homePage = new HomePage(driver);
        navigationBar = new NavigationBar(driver);
    }

    @Test
    public void verifyHomePageTest(){
        homePage.navigateToHomePage();
        homePage.verifyHomeTextIsDisplayed();
        navigationBar.clickOnHomeButton();
    }


}
