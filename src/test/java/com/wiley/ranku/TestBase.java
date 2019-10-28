package com.wiley.ranku;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.gauge.AfterSuite;
import com.thoughtworks.gauge.BeforeScenario;
import com.thoughtworks.gauge.BeforeSuite;
import com.wiley.ranku.datamodel.Site;
import com.wiley.ranku.datamodel.TestData;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;

import static org.apache.logging.log4j.core.util.Loader.getClassLoader;

public class TestBase {

    // Holds the WebDriver instance
    protected static WebDriver driver;
    protected static Site siteData;
    protected static TestData testData;

    // Initialize a driver instance of required browser
    // Since this does not have a significance in the application's business domain, the BeforeSuite hook is used to instantiate the driver
    @BeforeSuite
    public void initializeDriver() throws InterruptedException {
        driver = getDriver();
        driver.manage().window().maximize();
        ObjectMapper mapper = new ObjectMapper();
        try {
            siteData = mapper.readValue(getClassLoader().getResourceAsStream(System.getenv("site") + ".json"),Site.class);
            testData = mapper.readValue(getClassLoader().getResourceAsStream(System.getenv("test")+".json"),TestData.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @AfterSuite
    public void closeDriver(){
     // driver.quit();
    }

    private WebDriver getDriver(){

        switch (System.getenv("browser").toLowerCase())
        {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();
            case "ie":
                WebDriverManager.iedriver().setup();
                return new InternetExplorerDriver();
            case "edge":
                WebDriverManager.edgedriver().setup();
                return new EdgeDriver();
            case "safari":
                return new SafariDriver();
            default:
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
        }
    }

    @BeforeScenario
    public void launchSite() throws InterruptedException {
        driver.get(siteData.getEnvironment().getLaunchUrl(System.getenv("environment")));
        Thread.sleep(1000);

        WebElement element=driver.findElement(By.xpath(siteData.getAppPages().getHome().getXpath("continue")));
        if(element.isDisplayed()){
            element.click();
        }
        if(driver.findElement(By.xpath(siteData.getAppPages().getHome().getXpath("liveAgentChat"))).isDisplayed()){
            driver.findElement(By.xpath(siteData.getAppPages().getHome().getXpath("liveAgentChat"))).click();
        };

    }

    protected void waitForElementClickable(WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    protected void clickElement(WebElement element){
        waitForElementClickable(element);
        element.click();
    }

    protected void selectDropDownList(WebElement dropdownElement, String Value){
        waitForElementClickable(dropdownElement);
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByVisibleText(Value);
    }
    protected void setTextAs(WebElement element, String text){
        waitForElementClickable(element);
        element.sendKeys(text);
    }
    protected String getText(WebElement element){
       return element.getText();
    }
    protected void freeze(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    protected void selectCheckBox(WebElement checkbox){
        if (!checkbox.isSelected()){
            checkbox.click();
        }
    }
    protected boolean checkElementVisible(WebElement element){
        return element.isDisplayed();
    }

}
