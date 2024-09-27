package com.company.pages;

import com.company.utilities.Configuration;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

@Log4j2
public class HomePage extends BasePage{

    protected String homeUrl;

    @FindBy(xpath = "//a[contains(text(), 'Home')]\n")
    public WebElement homeText;

    @FindBy(xpath = "//a[contains(text(), ' Signup / Login')]\n")
    public WebElement signUpButton;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public String getHomeText(){
        return homeText.getText();
    }

    public void clickSignUpButton(){
        webDriverWait.until((d) -> signUpButton.isDisplayed());
        signUpButton.click();
    }

    public void navigateToHomePage() {
        homeUrl = Configuration.getInstance().getProperty("url");
        log.info("URL: {}", homeUrl);
        openUrl(homeUrl);
    }

    public void verifyHomeTextIsDisplayed(){
        webDriverWait.until((d) -> homeText.isDisplayed());
        String home_Text = getHomeText();
        Assert.assertTrue(homeText.isDisplayed());
        Assert.assertEquals(home_Text,"Home");
    }




}
