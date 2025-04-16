package com.company.pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
public class ViewCartPage extends BasePage {

    protected int item_count;

    List<Map<String,String>> cart_item_list = new ArrayList<Map<String,String>>();

    Map<String,String> rawMap = new HashMap<>();

    @FindBy(xpath = "//table[@id='cart_info_table']")
    protected WebElement cartInfoTable;

    @FindBy(tagName = "tbody")
    protected WebElement tableBody;

    protected List<WebElement> getTableBodyRows(){
        webDriverWait.until(ExpectedConditions.visibilityOf(tableBody));
        return tableBody.findElements(By.tagName("tr"));
    }

    public ViewCartPage(WebDriver driver) {
        super(driver);
    }

    public void validateItemCountInCart(int count) {
        item_count = getTableBodyRows().size() - 1;
        Assert.assertEquals(item_count, count);
        log.info("item count : {} validated successfully ", item_count);
    }

}
