package com.company.tests.login;

import com.company.pages.*;
import com.company.tests.BaseTest;
import com.company.utils.Constants;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Log4j2
public class LoginWithCorrectEmailAndPasswordTest extends BaseTest {

    protected HomePage homePage;
    protected LoginPage loginPage;
    protected SignUpPage signUpPage;
    protected AccountCreatedPage accountCreatedPage;
    protected NavigationBar navigationBar;

    @DataProvider(name = "userData")
    public Object[][] getUserData() {
        log.info("Initiating DataProvider!...");
        name = getPropertyValue(Constants.REGISTERED_USER_NAME);
        email = getPropertyValue(Constants.REGISTERED_USER_EMAIL);
        password = getPropertyValue(Constants.REGISTERED_USER_PASSWORD);
        return new Object[][]{{name,email,password}};
    }

    @Test(dataProvider = "userData")
    public void loginWithCorrectEmailAndPassword (String name,String email,String password){
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        signUpPage = new SignUpPage(driver);
        accountCreatedPage = new AccountCreatedPage(driver);
        navigationBar = new NavigationBar(driver);
        homePage.navigateToHomePage();
        homePage.verifyHomeTextIsDisplayed();
        homePage.clickSignUpButton();
        loginPage.verifyLogInIsDisplayed();
        loginPage.logIn(email,password);
        navigationBar.verifyLoggedInTextIsDisplayed(name);
    }




}
