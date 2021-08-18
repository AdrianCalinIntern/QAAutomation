package AutomationPracticeHomepage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import utils.Driver;

import java.util.List;
import java.util.Locale;

public class BaseTestAutomationPractice {
    public Driver driver;

    @BeforeTest
    public void initialisation() {
        driver = Driver.getInstance();
    }


    @AfterTest
    public void tearDown() {
        driver.exit();
    }

    // http://automationpractice.com
    public void homePageOpen(String url) {
        driver.webDriver.get(url);
    }

    public void searchForAProduct(String article) {
        driver.webDriverWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("search_query_top")));
        driver.webDriver.findElement(By.id("search_query_top")).sendKeys(article);
        driver.webDriver.findElement(By.className("button-search")).click();
        driver.webDriverWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[contains(@class,'product_list')]/li[contains(@class,'item')]")));
        String searchItem = driver.webDriver.findElement(By.cssSelector("span.lighter")).getText().toLowerCase(Locale.ROOT).replace("\"", "");
        Assert.assertEquals(searchItem, article);
    }

    public List<WebElement> getProductList() {
        driver.webDriverWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[contains(@class,'product_list')]/li[contains(@class,'item')]")));
        List<WebElement> productFromGrid = driver.webDriver.findElements(By.xpath("//ul[contains(@class,'product_list')]/li[contains(@class,'item')]"));
        return productFromGrid;
    }

    public void addAProductToTheBasket(int item) {
        getProductList().get(item).click();
        driver.webDriverWait().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[class='exclusive']"))).click();
        driver.webDriverWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("layer_cart")));
        String productNameBeforeShoppingCart = driver.webDriver.findElement(By.cssSelector("span#layer_cart_product_title")).getText();
        String quantityBeforeShoppingCartString = driver.webDriver.findElement(By.cssSelector("span#layer_cart_product_quantity")).getText();

        System.out.println(productNameBeforeShoppingCart);
        System.out.println(quantityBeforeShoppingCartString);

    }

}
