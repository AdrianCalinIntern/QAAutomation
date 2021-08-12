package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Methods {
    static Driver driver;

    public static void searchForAnItem(){
        driver.webDriverWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("search_query_top")));
        WebElement searchBar = driver.webDriver.findElement(By.id("search_query_top"));
        //search for a product
        WebElement searchButton = driver.webDriver.findElement(By.className("button-search"));
        searchBar.sendKeys("Dress");
        searchButton.click();
    }
}
