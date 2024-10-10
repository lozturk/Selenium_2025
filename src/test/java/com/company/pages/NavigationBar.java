package com.company.pages;

import lombok.extern.apachecommons.CommonsLog;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

@Log4j2
public class NavigationBar extends BasePage{
    public NavigationBar(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[contains(text(), ' Home')]")
    protected WebElement homeButton;

    @FindBy(xpath = "//a[contains(text(), ' Products')]")
    protected WebElement productsButton;

    @FindBy(xpath = "//a[contains(text(), ' Cart')]")
    protected WebElement cartButton;

    @FindBy(xpath = "//a[contains(text(), ' Logout')]")
    protected WebElement logOutButton;

    @FindBy(xpath = "//a[contains(text(), ' Delete Account')]")
    protected WebElement deleteAccountButton;

    @FindBy(xpath = "//a[contains(text(), ' Test Cases')]")
    protected WebElement testCasesButton;

    @FindBy(xpath = "//a[contains(text(), ' API Testing')]")
    protected WebElement apiTestingButton;

    @FindBy(xpath = "//a[contains(text(), ' Video Tutorials')]")
    protected WebElement videoTutorialsButton;

    @FindBy(xpath = "//a[contains(text(), ' Contact us')]")
    protected WebElement contactUsButton;

    @FindBy(xpath = "//a[contains(text(), ' Logged in as')]")
    protected WebElement loggedInText;

    public void clickOnHomeButton(){
        webDriverWait.until((d)->homeButton.isDisplayed());
        homeButton.click();
        log.info("Home button is clicked successfully.");
    }

    public void clickOnProductsButton(){
        webDriverWait.until((d)->productsButton.isDisplayed());
        productsButton.click();
        log.info("Products button is clicked successfully.");
    }

    public void clickOnCartButton(){
        webDriverWait.until((d)->cartButton.isDisplayed());
        cartButton.click();
        log.info("Cart button is clicked successfully.");
    }

    public void clickOnLogoutButton(){
        webDriverWait.until((d)->logOutButton.isDisplayed());
        logOutButton.click();
        log.info("Logout button is clicked successfully.");
    }

    public void clickOnDeleteAccountButton(){
        webDriverWait.until((d)->deleteAccountButton.isDisplayed());
        deleteAccountButton.click();
        log.info("Delete account button is clicked successfully.");
    }

    public void clickOnTestCasesButton(){
        webDriverWait.until((d)->testCasesButton.isDisplayed());
        testCasesButton.click();
        log.info("Test cases button is clicked successfully.");
    }

    public void clickOnApiTestingButton(){
        webDriverWait.until((d)->apiTestingButton.isDisplayed());
        apiTestingButton.click();
        log.info("Api testing button is clicked successfully.");
    }

    public void clickOnVideoTutorialsButton(){
        webDriverWait.until((d)->videoTutorialsButton.isDisplayed());
        videoTutorialsButton.click();
        log.info("Video tutorials button is clicked successfully.");
    }

    public void clickOnContactUsButton(){
        webDriverWait.until((d)->contactUsButton.isDisplayed());
        contactUsButton.click();
        log.info("Contact us button is clicked successfully.");
    }


    public void verifyLoggedInTextIsDisplayed(String name) {
        webDriverWait.until((d)->loggedInText.isDisplayed());
        log.debug(loggedInText.getText());
        Assert.assertEquals(loggedInText.getText(),"Logged in as "+name);
    }
}
