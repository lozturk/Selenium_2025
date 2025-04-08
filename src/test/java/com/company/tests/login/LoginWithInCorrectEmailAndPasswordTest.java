package com.company.tests.login;

import com.company.pages.*;
import com.company.tests.BaseTest;
import com.company.utils.Constants;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Log4j2
public class LoginWithInCorrectEmailAndPasswordTest extends BaseTest {

    protected HomePage homePage;
    protected LoginPage loginPage;
    protected NavigationBar navigationBar;

    @DataProvider(name = "invalidUserData")
    public Object[][] getInvalidUserData() {
        log.info("Initiating DataProvider with invalid credentials!...");
        invalidEmail = getPropertyValue(Constants.INVALID_USER_EMAIL);
        invalidPassword = getPropertyValue(Constants.INVALID_USER_PASSWORD);
        return new Object[][]{{invalidEmail, invalidPassword}};
    }

    // Test to verify login with incorrect email and password

    @Test(dataProvider = "invalidUserData")
    public void loginWithInCorrectEmailAndPassword(String email, String password) {
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        navigationBar = new NavigationBar(driver);
        homePage.navigateToHomePage();
        homePage.verifyHomeTextIsDisplayed();
        homePage.clickSignUpButton();
        loginPage.verifyLogInIsDisplayed();
        loginPage.logIn(email, password);
        loginPage.verifyLoginFailed();
    }



}