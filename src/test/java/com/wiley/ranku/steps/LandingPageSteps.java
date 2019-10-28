package com.wiley.ranku.steps;

import com.thoughtworks.gauge.Step;
import com.wiley.ranku.TestBase;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.Set;

public class LandingPageSteps extends TestBase {

    @Step("Site logo direct user to Home page")
    public void clickOnLogo() {
        WebElement logo = driver.findElement(By.xpath(siteData.getAppPages().getHome().getXpath("logo")));
        logo.click();
        String mainHandle = driver.getWindowHandle();
        Set<String> handles = driver.getWindowHandles();
        String url = driver.getCurrentUrl();
        if (handles.size() > 1) {
            for (String handle : handles) {
                if (!handle.equals(mainHandle)) {
                    driver.switchTo().window(handle);
                    url = driver.getCurrentUrl();
                    driver.close();
                    driver.switchTo().window(mainHandle);
                }
            }
        }
        Assert.assertTrue(url.contains(siteData.getHomeUrl()));
    }

    @Step("User can open Apply model on clicking the Apply button")
    public void testApplyNow(){
        WebElement applyNow = driver.findElement(By.xpath(siteData.getAppPages().getHome().getXpath("applyNow")));
        Assert.assertTrue(applyNow.getAttribute("href").contains(siteData.getApplyNowUrl()));
    }

    @Step("Click on Request Info button")
    public void clickRequestForInfo() throws IOException {

        driver.findElement(By.xpath(siteData.getAppPages().getHome().getXpath("requestInfo"))).click();

    }
}

