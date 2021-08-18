package SeleniumEasy;
import AutomationPracticeHomepage.BaseTest;
import org.testng.annotations.Test;

public class SeleniumEasy_CheckBoxDemo extends BaseTest {
    @Test
    public void CheckBoxDemo(){


        //Open the main page
        homePageOpen("https://www.seleniumeasy.com/test/");

        //Close the welcome dialog
        closeAddDialog();

        //navigate to desired page
        NavSelection(0, "Checkbox Demo");

        //send checkboxes items
        int[] dropDownItems = {0,2,3};
        //check the checkboxes
        checkOptionsFromCheckbox(dropDownItems);

        //check all checkboxes
        checkAllCheckbox();

        //verify if a checkbox is checked
        verifyIfCheckboxIsSelected(2);


    }

}
