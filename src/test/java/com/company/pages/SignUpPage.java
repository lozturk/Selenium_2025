package com.company.pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;


@Log4j2
public class SignUpPage extends BasePage{

    private static final String ENTER_ACCOUNT_INFORMATION = "ENTER ACCOUNT INFORMATION";


    public SignUpPage(WebDriver driver) {
        super(driver);
    }


    @FindBy(xpath = "//b[contains(text(), 'Enter Account Information')]")
    protected WebElement enterAccountInformationText;

    @FindBy(css = "#id_gender1")
    protected WebElement misterCheckBox;

    @FindBy(css = "#id_gender2")
    protected WebElement missesCheckBox;

    @FindBy(id = "name")
    protected WebElement nameTextBox;

    @FindBy(id = "email")
    protected WebElement emailTextBox;

    @FindBy(name = "password")
    protected WebElement passwordTextBox;

    @FindBy(id = "first_name")
    protected WebElement firstNameTextBox;

    @FindBy(id = "last_name")
    protected WebElement lastNameTextBox;

    @FindBy(id = "days")
    protected WebElement dayDropDown;

    @FindBy(id = "months")
    protected WebElement monthDropDown;

    @FindBy(id = "years")
    protected WebElement yearDropDown;

    @FindBy(id = "newsletter")
    protected WebElement newsletterCheckBox;

    @FindBy(id = "optin")
    protected WebElement optinCheckBox;

    @FindBy(id = "company")
    protected WebElement companyTextBox;

    @FindBy(id = "address1")
    protected WebElement addressTextBox;

    @FindBy(id = "address2")
    protected WebElement address2TextBox;

    @FindBy(id = "country")
    protected WebElement countryDropdown;

    @FindBy(id = "state")
    protected WebElement stateTextBox;

    @FindBy(id = "city")
    protected WebElement cityTextBox;

    @FindBy(id = "zipcode")
    protected WebElement zipCodeTextBox;

    @FindBy(id = "mobile_number")
    protected WebElement mobileNumberTextBox;

    @FindBy(css = "button[data-qa='create-account']")
    protected WebElement createAccountButton;



    protected String getEnterAccountInformationText(){
        return enterAccountInformationText.getText().trim();
    }


    public void verifySignUpIsDisplayed(){
        webDriverWait.until((d) -> enterAccountInformationText.isDisplayed());
        log.debug(enterAccountInformationText.getText());
        Assert.assertEquals(getEnterAccountInformationText(),ENTER_ACCOUNT_INFORMATION);
    }

    public void clickOnMrCheckBox (){
        webDriverWait.until((d) -> misterCheckBox.isEnabled());
        if (misterCheckBox.isSelected()){
            log.info("Mr. checkbox is already selected.");
        } else {
            misterCheckBox.click();
            log.info("Mr. checkbox is selected successfully.");
        }
    }

    public void clickOnMissCheckBox (){
        webDriverWait.until((d) -> missesCheckBox.isEnabled());
        if (missesCheckBox.isSelected()){
            log.info("Miss. checkbox is already selected.");
        } else {
            missesCheckBox.click();
            log.info("Miss. checkbox is selected successfully.");
        }
    }

    protected void enterName (String name){
        String name_Text = nameTextBox.getText();
        if (!name_Text.isEmpty()) {
            log.info("Name: {} already entered successfully.", name);
        } else {
            nameTextBox.clear();
            nameTextBox.sendKeys(name);
        }
    }

    protected void enterEmail (String email){
        String email_Text = emailTextBox.getText();
        if (!email_Text.isEmpty()) {
            log.info("Email: {} already entered successfully.", email);
        } else {
            emailTextBox.clear();
            emailTextBox.sendKeys(email);
        }
    }


    protected void enterPassword (String password){
        passwordTextBox.sendKeys(password);
    }

    public void selectDay(int day) {
        Select select = new Select(dayDropDown);
        select.selectByIndex(day);
            log.info("Day : {} is selected successfully.", day);
    }

    public void selectMonth(String month) {
        Select select = new Select(monthDropDown);
        select.selectByVisibleText(month);
        log.info("Month : {} is selected successfully.", month);
    }

    public void selectYear(String year) {
        Select select = new Select(yearDropDown);
        select.selectByVisibleText(year);
        log.info("Year : {} is selected successfully.", year);
    }

    public void enterDateOfBirth(int day, String month, String year) {
        selectDay(day);
        selectMonth(month);
        selectYear(year);
    }

    public void setAccountInformation(String name, String email, String password, int day, String month, String year){
        // enterName(name);
        // enterEmail(email);
        enterPassword(password);
        enterDateOfBirth(day, month, year);
    }

    public void clickOnNewsletterCheckBox (){
        webDriverWait.until((d) -> newsletterCheckBox.isEnabled());
        if (newsletterCheckBox.isSelected()){
            log.info("Newsletter checkbox is already selected.");
        } else {
            newsletterCheckBox.click();
            log.info("Newsletter checkbox is selected successfully.");
        }
    }

    public void clickOnOptinCheckBox (){
        webDriverWait.until((d) -> optinCheckBox.isEnabled());
        if (optinCheckBox.isSelected()){
            log.info("Optin checkbox is already selected.");
        } else {
            optinCheckBox.click();
            log.info("Optin checkbox is selected successfully.");
        }
    }

    protected void enterFirstName (String firstName){
        firstNameTextBox.sendKeys(firstName);
        log.info("First name: {} is entered successfully.", firstName);
    }

    protected void enterLastName (String lastName){
        lastNameTextBox.sendKeys(lastName);
        log.info("Last name: {} is entered successfully.", lastName);
    }

    protected void enterCompany (String company){
        companyTextBox.sendKeys(company);
        log.info("Company: {} is entered successfully.", company);
    }

    protected void enterAddress (String address){
        addressTextBox.sendKeys(address);
        log.info("Address: {} is entered successfully.", address);
    }

    protected void enterAddress2 (String address2){
        address2TextBox.sendKeys(address2);
        log.info("Address2: {} is entered successfully.", address2);
    }

    public void selectCountry(String countryName) {
        Select select = new Select(countryDropdown);
        select.selectByVisibleText(countryName);
        log.info("Country: {} is selected successfully.", countryName);
    }

    protected void enterState (String state){
        stateTextBox.sendKeys(state);
        log.info("State: {} is entered successfully.", state);
    }

    protected void enterCity (String city){
        cityTextBox.sendKeys(city);
        log.info("City: {} is entered successfully.", city);
    }

    protected void enterZipCode (String zipCode){
        zipCodeTextBox.sendKeys(zipCode);
        log.info("Zip code: {} is entered successfully.", zipCode);
    }

    public void enterMobileNumber (String mobileNumber){
        mobileNumberTextBox.sendKeys(mobileNumber);
        log.info("Mobile number: {} is entered successfully.", mobileNumber);
    }

    public void setAddressInformation(String firstName, String lastName, String company,String address,String address2,String country,String state,String city,String zip, String mobileNumber){
        enterFirstName(firstName);
        enterLastName(lastName);
        enterCompany(company);
        enterAddress(address);
        enterAddress2(address2);
        selectCountry(country);
        enterState(state);
        enterCity(city);
        enterZipCode(zip);
        enterMobileNumber(mobileNumber);
    };

    public void clickOnCreateAccountButton() {
        createAccountButton.click();
        log.info("Create account button is clicked successfully.");
    }


}
