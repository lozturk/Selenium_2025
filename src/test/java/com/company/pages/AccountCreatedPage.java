package com.company.pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

@Log4j2
public class AccountCreatedPage extends BasePage{
    public AccountCreatedPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@id=\"form\"]/div/div/div/h2/b")
    protected WebElement accountCreatedText;

    @FindBy(xpath = "//a[contains(text(),'Continue')]")
    protected WebElement continueButton;


    public void verifyAccountCreatedIsDisplayed() {
        webDriverWait.until((d) -> accountCreatedText.isDisplayed());
        log.debug(accountCreatedText.getText());
        Assert.assertEquals(accountCreatedText.getText(), "ACCOUNT CREATED!");
    }

    public void clickOnContinueButton() {
        webDriverWait.until((d)->continueButton.isEnabled());
        continueButton.click();
        log.info("Continue button is clicked successfully.");
    }
}
