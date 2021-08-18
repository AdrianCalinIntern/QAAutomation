package SeleniumEasy;
import AutomationPracticeHomepage.BaseTest;
import org.testng.annotations.Test;

public class SeleniumEasy_GetStateOfFiltersTable extends BaseTest {
    @Test
    public void SeleniumEasy_GetStateOfFiltersTable() {
    /*
    On the same section for the bottom table get the state of the filters buttons, click filter then get the state again
     */

    //Open the main page
    homePageOpen("https://www.seleniumeasy.com/test/");

    //Close the welcome dialog
    closeAddDialog();

    //navigate to desired page
    NavSelection(2, "Table Data Search");

    //get status of filter input
     getTableInputState();

     //print input status before pressing on filter button
     printTableInputState();

     //click on the filter button
     enableInputFilters();

     //print input status before pressing on filter button
     printTableInputState();


    }
}

