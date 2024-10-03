package com.company.tests;

import com.company.pages.*;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.exec.util.StringUtils;
import org.testng.annotations.*;

import java.time.LocalDateTime;

@Log4j2
public class RegisterUserTest extends BaseTest {

    protected HomePage homePage;
    protected LoginPage loginPage;
    protected SignUpPage signUpPage;
    protected AccountCreatedPage accountCreatedPage;
    protected NavigationBar navigationBar;
    protected DeleteAccountPage deleteAccountPage;

    @DataProvider(name = "userData")
    public Object[][] getUserData() {
        name = getPropertyValue(NAME);
        email = faker.internet().emailAddress();
        password = faker.internet().password();
        day = faker.number().numberBetween(1,31);
        month = "January";
        year = String.valueOf(faker.number().numberBetween(1900,2021));
        firstName = StringUtils.split(name," ")[0];
        lastName = StringUtils.split(name," ")[1];
        company = faker.company().name();
        address = faker.address().streetAddress();
        address2 = faker.address().secondaryAddress();
        country = "United States";
        state = faker.address().state();
        city = faker.address().city();
        zip = faker.address().zipCode();
        mobileNumber = faker.phoneNumber().cellPhone();
        return new Object[][]{{name, email,password, day, month, year, firstName, lastName,company,address,address2,country,state,city,zip,mobileNumber}};
    }

    @BeforeMethod
    public void initiatePages(){
        log.info("Initiating Pages!...");
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        signUpPage = new SignUpPage(driver);
        accountCreatedPage = new AccountCreatedPage(driver);
        navigationBar = new NavigationBar(driver);
        deleteAccountPage = new DeleteAccountPage(driver);
    }

    @Test(dataProvider = "userData")
    public void registerUser (String name,String email,String password,int day,String month,String year,
                              String firstName,String lastName,String company,String address,String address2,
                              String country,String state,String city,String zip,String mobileNumber){
        homePage.navigateToHomePage();
        homePage.verifyHomeTextIsDisplayed();
        homePage.clickSignUpButton();
        loginPage.verifySignInIsDisplayed();
        loginPage.register(name, email);
        signUpPage.verifySignUpIsDisplayed();
        signUpPage.clickOnMrCheckBox();
        signUpPage.setAccountInformation(name,email,password,day,month,year);
        signUpPage.clickOnNewsletterCheckBox();
        signUpPage.clickOnOptinCheckBox();
        signUpPage.setAddressInformation(firstName,lastName,company,address,address2,country,state,city,zip,mobileNumber);
        signUpPage.clickOnCreateAccountButton();
        accountCreatedPage.verifyAccountCreatedIsDisplayed();
        accountCreatedPage.clickOnContinueButton();
        navigationBar.verifyLoggedInTextIsDisplayed(name);
        navigationBar.clickOnDeleteAccountButton();
        deleteAccountPage.verifyAccountDeletedIsDisplayed();
        deleteAccountPage.clickOnContinueButton();
    }




}
