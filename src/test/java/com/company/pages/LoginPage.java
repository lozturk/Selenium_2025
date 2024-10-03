package com.company.pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

@Log4j2
public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@id=\"form\"]/div/div/div[3]/div/h2")
    public WebElement signUpText;

    @FindBy(xpath = "//*[@id=\"form\"]/div/div/div[3]/div/form/input[2]")
    public WebElement nameTextBox;

    @FindBy(xpath = "//*[@id=\"form\"]/div/div/div[3]/div/form/input[3]")
    public WebElement emailTextBox;

    @FindBy(xpath = "//*[@id=\"form\"]/div/div/div[3]/div/form/button")
    public WebElement signUpButton;


    public void verifySignInIsDisplayed(){
        webDriverWait.until((d) -> signUpText.isDisplayed());
        log.debug(signUpText.getText());
        Assert.assertTrue(signUpText.isDisplayed());
    }

    public void register (String name, String email) {
        nameTextBox.sendKeys(name);
        emailTextBox.sendKeys(email);
        signUpButton.click();
    }


}
