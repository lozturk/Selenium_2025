package com.company.tests.product;

import com.company.pages.*;
import com.company.tests.BaseTest;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Log4j2
public class AddProductsInCardTest extends BaseTest {

    protected HomePage homePage;
    protected NavigationBar navigationBar;
    protected ProductsPage productsPage;
    protected ViewCartPage viewCartPage;


    @DataProvider(name = "productData")
    public Object[][] getProductData() {
        log.info("Initiating DataProvider!...");
        product_1 = getPropertyValue("product_1");
        product_2 = getPropertyValue("product_2");
        selected_item_count = Integer.parseInt(getPropertyValue("selected_item_count"));
        return new Object[][]{{product_1,product_2,selected_item_count}};
    }

    @Test (dataProvider = "productData")
    public void testAddProductsInCart(String product_1,String product_2,int selectedItemCount) throws InterruptedException {
        homePage = new HomePage(driver);
        navigationBar = new NavigationBar(driver);
        productsPage = new ProductsPage(driver);
        viewCartPage = new ViewCartPage(driver);
        homePage.navigateToHomePage();
        homePage.verifyHomeTextIsDisplayed();
        navigationBar.clickOnProductsButton();
        productsPage.waitForDocumentState();
        productsPage.verifyAllProductsTextIsDisplayed();
        productsPage.hoverOverByProductName(product_1);
        productsPage.clickOnAddToCartForSpecificProduct(product_1);
        productsPage.clickOnContinueShoppingButton();
        productsPage.hoverOverByProductName(product_2);
        productsPage.clickOnAddToCartForSpecificProduct(product_2);
        productsPage.clickOnContinueShoppingButton();
        navigationBar.clickOnCartButton();
        viewCartPage.validateItemCountInCart(selectedItemCount);

    }




}
