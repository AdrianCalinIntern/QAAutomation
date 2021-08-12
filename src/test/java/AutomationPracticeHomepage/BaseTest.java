package AutomationPracticeHomepage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.Driver;
import java.util.List;


public class BaseTest {
    static Driver driver;

    @BeforeTest
        public void initialisation(){
            driver = Driver.getInstance();
    }

    @Test
    public void openMainPage_searchForAProduct_ClickOnTheFirstItem_AddToCart() {
        // Practice 1
        // Navigate to http://automationpractice.com
        // Search for “dress”
        // Click on the first item on the page
        // Add to cart

        homePageOpen("http://automationpractice.com");
        searchForAProduct("Dress");
        selectProductFromGrid(0);
        addProductToTheBasket();
    }

    @Test
    public void searchForAProduct_selectTheSecondProduct_chanceTheSize_changeTheColor_AddItToTheBasket() {
        /*
        1. Navigate to http://automationpractice.com
        2. Search for “dress”
        3. Click on the second item on the page
        4. Change size
        5. Change color
        6. Add to cart
         */
        homePageOpen("http://automationpractice.com");
        searchForAProduct("Dress");
        selectProductFromGrid(1);
        changeProductColor(2,"color-pink");
        changeSize("2", "size-m");
        addProductToTheBasket();
    }
    @Test
    public void selectAProduct_ClickOnAnyItem_ChangeTheSize_ChangeTheQuantity_ChangeTheColor_AddToTheBasket_OpenBasket_DeleteProd() throws InterruptedException {
    /*
        1. Navigate to http://automationpractice.com
        2. Search for “dress”
        3. Click on any item on the page
        4. Change size and quantity
        5.Change color if possible
        6. Add to cart
        7. Click Proceed to checkout
        8. Delete the product
     */
        homePageOpen("http://automationpractice.com");
        searchForAProduct("Dress");
        selectProductFromGrid(2);
        changeSize("3", "size-l");
        setQuantity(5);
        addProductToTheBasket();
        proceedToCheckout();
        removeProduct();

    }

    @AfterTest
    public void tearDown(){
        driver.exit();
    }

    public void homePageOpen(String url){
        driver.webDriver.get(url);
    }

    public void searchForAProduct(String article){
        driver.webDriverWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("search_query_top")));
        WebElement searchBar = driver.webDriver.findElement(By.id("search_query_top"));
        WebElement searchButton = driver.webDriver.findElement(By.className("button-search"));
        searchBar.sendKeys(article);
        searchButton.click();
    }

    public void selectProductFromGrid(int item){
        driver.webDriverWait().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("ul.product_list")));
        List<WebElement> productFromGrid = driver.webDriver.findElements(By.cssSelector("li.ajax_block_product"));
        productFromGrid.get(item).click();
    }

    public void addProductToTheBasket(){
        driver.webDriverWait().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[class='exclusive']")));
        WebElement addToCartButton = driver.webDriver.findElement(By.cssSelector("button[class='exclusive']"));
        addToCartButton.click();
        driver.webDriverWait().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#layer_cart")));
    }

    public void changeProductColor(int boxNo, String urlFraction){
        driver.webDriverWait().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("ul#color_to_pick_list")));
        WebElement colorBox = driver.webDriver.findElement(By.cssSelector("ul#color_to_pick_list li:nth-of-type(" + boxNo + ")"));
        colorBox.click();
        driver.webDriverWait().until(ExpectedConditions.urlContains(urlFraction));
    }

    public void changeSize(String size, String urlFraction){
        driver.webDriverWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("uniform-group_1")));
        Select sizeDropdown = new Select(driver.webDriver.findElement(By.id("group_1")));
        sizeDropdown.selectByValue(size);
        driver.webDriverWait().until(ExpectedConditions.urlContains(urlFraction));
    }

    public void setQuantity(int quantity){
        driver.webDriverWait().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a.product_quantity_up")));
        WebElement increaseQtyButton = driver.webDriver.findElement(By.cssSelector("a.product_quantity_up"));
        for (int i=1; i<=quantity - 1; i++){
            increaseQtyButton.click();
        }
    }

    public void proceedToCheckout(){
        driver.webDriverWait().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[title='Proceed to checkout']")));
        WebElement proceedToCheckoutButton = driver.webDriver.findElement(By.cssSelector("a[title='Proceed to checkout']"));
        proceedToCheckoutButton.click();
    }

    public void removeProduct(){
        driver.webDriverWait().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a.cart_quantity_delete")));
        WebElement deleteButton = driver.webDriver.findElement(By.cssSelector("a.cart_quantity_delete"));
        deleteButton.click();
    }
}
