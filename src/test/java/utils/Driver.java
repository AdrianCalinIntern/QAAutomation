package utils;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

public class Driver {
    private static Driver driver = null;
    public RemoteWebDriver webDriver;
    public static WebDriverWait wait = null;
    public static Actions action = null;

    private Driver() {
        setNewDriver();
    }

    public static Driver getInstance(){
        if(driver == null){
            driver = new Driver();
        }
        return driver;
    }

    public void setNewDriver(){
        try{
            if(webDriver!= null){
                webDriver.close();
                webDriver.quit();
            }
        }
        catch (Exception ignored){

        }
        finally {
            webDriver= null;
        }
        System.setProperty("webdriver.chrome.driver","/Users/adrian.urate/IdeaProjects/TestQaAutomation/src/chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        webDriver.manage().window().maximize();
    }

    public static WebDriverWait webDriverWait() {
        if (wait == null) {
            wait = new WebDriverWait(driver.webDriver, 10);
        }
        return wait;
    }

    public static Actions setNewAction(){
        if(action == null){
            action = new Actions(driver.webDriver);
        }
        return action;
    }

    public void exit() {
        try {
            if (webDriver != null) {
                webDriver.close();
                webDriver.quit();
            }
        } catch (Exception e) {

        } finally {
            webDriver = null;
            driver = null;
        }
    }

}
