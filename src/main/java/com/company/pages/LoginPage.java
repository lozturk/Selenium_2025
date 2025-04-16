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
    public WebElement newUserSignUpText;

    @FindBy(xpath = "//*[@id=\"form\"]/div/div/div[3]/div/form/input[2]")
    public WebElement nameTextBox;

    @FindBy(xpath = "//*[@id=\"form\"]/div/div/div[3]/div/form/input[3]")
    public WebElement emailTextBoxForRegister;

    @FindBy(xpath = "//*[@id=\"form\"]/div/div/div[3]/div/form/button")
    public WebElement signUpButton;

    @FindBy(xpath = "//h2[contains(text(),'Login to your account')]")
    public WebElement logInText;

    @FindBy(xpath = "//*[@id=\"form\"]/div/div/div[1]/div/form/input[2]")
    public WebElement emailTextBoxForSignIn;

    @FindBy(name = "password")
    public WebElement passwordTextBoxForSignIn;

    @FindBy(xpath = "//button[contains(text(),'Login')]")
    public WebElement logInButton;

    @FindBy(xpath = "   //*[@id=\"form\"]/div/div/div[1]/div/form/p")
    public WebElement inValidEmailAndPasswordText;
    



    public void verifySignUpIsDisplayed(){
        webDriverWait.until((d) -> newUserSignUpText.isDisplayed());
        log.debug(newUserSignUpText.getText());
        Assert.assertTrue(newUserSignUpText.isDisplayed());
    }

    public void verifyLogInIsDisplayed(){
        webDriverWait.until((d) -> logInText.isDisplayed());
        log.debug(logInText.getText());
        Assert.assertTrue(logInText.isDisplayed());
    }

    public void register (String name, String email) {
        nameTextBox.sendKeys(name);
        log.info(name);
        emailTextBoxForRegister.sendKeys(email);
        log.info(email);
        signUpButton.click();
        log.info("Sign up button is clicked successfully.");
    }

    public void logIn (String email, String password) {
        webDriverWait.until((d)-> emailTextBoxForSignIn.isEnabled());
        emailTextBoxForSignIn.sendKeys(email);
        log.info("Email : {} ", email);
        webDriverWait.until((d)-> passwordTextBoxForSignIn.isEnabled());
        passwordTextBoxForSignIn.sendKeys(password);
        log.info("Password : {} ",password);
        logInButton.click();
        log.info("Login button is clicked successfully.");
    }



    public void verifyLoginFailed() {
        webDriverWait.until((d) -> inValidEmailAndPasswordText.isDisplayed());
        log.debug(inValidEmailAndPasswordText.getText());
        Assert.assertEquals(inValidEmailAndPasswordText.getText().trim(), "Your email or password is incorrect!");
    }
}
