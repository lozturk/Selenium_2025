package com.company.tests;

import com.company.pages.HomePage;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Log4j2
public class HomePageTest extends BaseTest {

    protected HomePage homePage;

    @BeforeMethod
    public void initiatePages(){
        homePage = new HomePage(driver);
    }

    @Test
    public void verifyHomePageTest(){
        homePage.navigateToHomePage();
        homePage.verifyHomeTextIsDisplayed();
    }


}
