package SeleniumEasy;
import AutomationPracticeHomepage.BaseTest;
import org.testng.annotations.Test;


public class SeleniumEasy_TableDataSearch extends BaseTest {
    @Test
    public void SeleniumEasy_TableDataSearch(){
        /*
        On the  Table section - table data search: build a single selector that can get all the elements rows
        */

        //Open the main page
        homePageOpen("https://www.seleniumeasy.com/test/");

        //Close the welcome dialog
        closeAddDialog();

        //navigate to desired page
        NavSelection(2, "Table Data Search");
        getAllItemsFromTaskTable();
    }

}
