package com.company.tests;

import com.company.pages.HomePage;
import com.company.pages.SignUpLoginPage;
import org.properties.PropertiesReader;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.*;

@Log4j2
public class RegisterUserTest extends BaseTest {

    protected HomePage homePage;
    protected SignUpLoginPage signUpLoginPage;

    @DataProvider(name = "userData")
    public Object[][] getUserData() {
        String name = getPropertyValue("name");
        String email = faker.internet().emailAddress();
        return new Object[][]{{name, email}};
    }


    @Test(dataProvider = "userData")
    public void registerUser (String name, String email){
        homePage = new HomePage(driver);
        signUpLoginPage = new SignUpLoginPage(driver);
        homePage.navigateToHomePage();
        homePage.verifyHomeTextIsDisplayed();
        homePage.clickSignUpButton();
        signUpLoginPage.verifySignInIsDisplayed();
        signUpLoginPage.register(name, email);
    }




}
