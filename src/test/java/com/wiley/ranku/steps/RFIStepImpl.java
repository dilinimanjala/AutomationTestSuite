package com.wiley.ranku.steps;

import com.thoughtworks.gauge.Step;
import com.wiley.ranku.TestBase;
import com.wiley.ranku.datamodel.Element;
import com.wiley.ranku.datamodel.TestDataSet;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashMap;


public class RFIStepImpl extends TestBase {
    HashMap<String, String> dataset;

    @Step("Click on RFI button")
    public void clickonRequestInformationButton() {
        WebElement rfi = driver.findElement(By.xpath(siteData.getAppPages().getHome().getXpath("requestInfo")));
        clickElement(rfi);
    }

    @Step("Fill the RFI form <TC_RFI_01>")
    public void fillRFIForm(String testcaseID) throws InterruptedException {

        dataset = testData.getDataSetById(testcaseID);

        ArrayList<Element> elements = siteData.getAppPages().getRfi().getElements();

        for (Element e : elements) {
            String fieldname = e.getName();
            String testdata = dataset.get(fieldname);

            if (isElementPresent(By.xpath(e.getXpath())) && e.getType() != null) {
                WebElement element = driver.findElement(By.xpath(e.getXpath()));
                String type = e.getType();

                switch (type) {
                    case "dropdown":
                        if (testdata != null) {
                            selectDropDownList(element, testdata);
                            Thread.sleep(1000);
                        }
                        break;
                    case "textbox":
                        if (testdata != null) {
                            setTextAs(element, testdata);
                            Thread.sleep(1000);
                        }
                        break;
                    case "checkbox":
                        if (Boolean.parseBoolean(testdata) != false) {
                            selectCheckBox(element);
                            Thread.sleep(1000);
                        }
                        break;
                }
            }
        }
        //driver.findElement(By.xpath((siteData.getAppPages().getRfi().getXpath("submitRequest")))).click();
        clickElement(driver.findElement(By.xpath((siteData.getAppPages().getRfi().getXpath("submitRequest")))));
        Thread.sleep(2000);
    }

    @Step("Verify Request Information Submitted")
    public void verifyRequestInformationSubmited() {
        WebElement thankYou = driver.findElement(By.xpath(siteData.getAppPages().getRfi().getXpath("thankYou")));
        checkElementVisible(thankYou);
        WebElement closeRFIThankYou = driver.findElement(By.xpath(siteData.getAppPages().getRfi().getXpath("closeThankYou")));
        clickElement(closeRFIThankYou);
    }

    @Step("Select Additional programs")
    public void selectPrograms() {
        WebElement additionlProgram = driver.findElement(By.xpath((siteData.getAppPages().getRfi().getXpath(dataset.get("additionalProgram")))));
        selectCheckBox(additionlProgram);
        clickElement(driver.findElement(By.xpath(siteData.getAppPages().getRfi().getXpath("sendInformation"))));
    }

    @Step("No Additional Programs Selected")
    public void NoAdditionalProgram() {
        clickElement(driver.findElement(By.xpath(siteData.getAppPages().getRfi().getXpath("additionalprogramthankyou"))));
    }

    public void getUniqueEmailAddress (){

    }
}
