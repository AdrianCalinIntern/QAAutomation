package AutomationPracticeHomepage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import utils.Driver;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class BaseTest {
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

    //https://www.seleniumeasy.com/
    public void closeAddDialog() {
        driver.webDriverWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("at-cv-lightbox-close")))
                .click();
        driver.webDriverWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("carousel-example-generic")));

    }

    public void NavSelection(int desiredMenuIndex, String option) {
        driver.webDriverWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("navbar-brand-centered")));
        List<WebElement> menus = driver.webDriver.findElements(By.className("dropdown"));
        WebElement desiredMenu = menus.get(desiredMenuIndex);
        desiredMenu.click();
        driver.webDriverWait().until(ExpectedConditions.visibilityOfElementLocated(By.className("open")));
        desiredMenu.findElement(By.linkText(option)).click();
    }

    public List<WebElement> getDropdownsList() {
        List<WebElement> checkBoxList = driver.webDriver.findElements(By.className("cb1-element"));
        return checkBoxList;
    }

    public void checkOptionsFromCheckbox(int[] dropDownItems) {
        for (int i : dropDownItems) {
            if (getDropdownsList().get(i).isSelected()) break;
            else getDropdownsList().get(i).click();
        }
    }

    public void checkAllCheckbox() {
        driver.webDriverWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("check1"))).click();

    }

    public void verifyIfCheckboxIsSelected(int dropdownItem) {
        if (getDropdownsList().get(dropdownItem).isSelected())
            System.out.println("Checkbox " + dropdownItem + " is selected");
    }

    public void openFromDatePicker() {
        driver.webDriverWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("from"))).click();
    }

    public void setMonthFromDropdown(int month) {
        String monthToString = Integer.toString(month);
        driver.webDriverWait().until(ExpectedConditions.visibilityOfElementLocated(By.className("ui-datepicker-month")));
        Select monthDropdown = new Select(driver.webDriver.findElement(By.className("ui-datepicker-month")));
        monthDropdown.selectByValue(monthToString);
    }

    public void setDayFromCalendar(int day) {
        driver.webDriverWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath
                ("//a[@class='ui-state-default' and text() =" + day + "]"))).click();
    }

    public void selectDateFromDatePicker(int month, int day) {
        openFromDatePicker();
        setMonthFromDropdown(month);
        setDayFromCalendar(day);
        if (day < 10)
            Assert.assertEquals(month + 1 + "/" + "0" + day + "/" + "2021", driver.webDriver.
                    findElement(By.id("from")).getAttribute("value"));
        else
            Assert.assertEquals(month + 1 + "/" + day + "/" + "2021", driver.webDriver.
                    findElement(By.id("from")).getAttribute("value"));
    }

    public void getAllItemsFromTaskTable() {
        List<WebElement> allItemsFromTable = driver.webDriver.findElements(By.cssSelector("#task-table tr"));

    }

    public boolean getTableInputState(){
       return driver.webDriverWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath
               ("//tr[@class='filters']/th[1]/input"))).isEnabled();
    }

    public void printTableInputState(){
        System.out.println(getTableInputState());
    }

    public void enableInputFilters(){
        driver.webDriverWait().until(ExpectedConditions.visibilityOfElementLocated(By.className("btn-filter")))
        .click();

    }
}
