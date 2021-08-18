package SeleniumEasy;
import AutomationPracticeHomepage.BaseTest;
import org.testng.annotations.Test;


public class SeleniumEasy_DatePickers extends BaseTest {
    @Test
    public void DatePickers() {
        /*
        On the Date pickers section - JQuery date picker, build selectors for one of the fields that allows you to pick a date
        */

        //Open the main page
        homePageOpen("https://www.seleniumeasy.com/test/");

        //Close the welcome dialog
        closeAddDialog();

        //navigate to desired page
        NavSelection(1, "JQuery Date Picker");

        //send month and day
        selectDateFromDatePicker(11, 2);
    }
}
