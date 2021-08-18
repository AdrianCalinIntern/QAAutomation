package AutomationPracticeHomepage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.Driver;
import java.util.List;


public class SearchForAnItemTest extends BaseTestAutomationPractice {

    @Test
    public void searchForADressTest() {

        // Navigate to http://automationpractice.com
        homePageOpen("http://automationpractice.com");

        // Search for “dress”
        searchForAProduct("dress");

        // Click on the first item on the page
        addAProductToTheBasket(2);

    }


}
