package com.company.pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import java.util.List;

@Log4j2
public class ProductsPage extends BasePage{

    protected WebElement elementToHoverOver;

    @FindBy(xpath = "//h2[contains(text(),'All Products')]")
    public WebElement allProductsText;

    @FindBy(xpath = "//div[@class='col-sm-4']")
    protected List<WebElement> allProductsList;

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public void verifyAllProductsTextIsDisplayed() {
        webDriverWait.until((d)-> allProductsText.isDisplayed());
        Assert.assertTrue(allProductsText.getText().contains("ALL PRODUCTS"));
        log.info("All products text is displayed successfully.");
    }

    public void hoverOverByProductName(String product) throws InterruptedException {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text() = '"+product+"']")));
        elementToHoverOver = driver.findElement(By.xpath("//*[text() = '"+product+"']"));
        getJS().executeScript("arguments[0].scrollIntoView(true);", elementToHoverOver);
        log.info("Scrolled down to element :{} successfully.", elementToHoverOver.getText());
        webDriverWait.until((d)-> elementToHoverOver.isDisplayed());
        getActions().moveToElement(elementToHoverOver).build().perform();
        log.info("Hovered over the element : {} successfully.", elementToHoverOver.getText());
    }

    public void clickOnAddToCartForSpecificProduct(String product) {
        String addToCartButton = "(//p[contains(text(),'"+product+"')])[2]/../a";
        webDriverWait
                .until(ExpectedConditions
                        .or(ExpectedConditions.visibilityOfElementLocated(By.xpath(addToCartButton)),
                                ExpectedConditions.elementToBeClickable(By.xpath(addToCartButton))));
        driver.findElement(By.xpath(addToCartButton)).click();
        log.info("Clicked on add to cart button successfully for {}.", product);
    }

}