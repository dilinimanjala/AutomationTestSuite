package com.wiley.ranku.steps;

import com.thoughtworks.gauge.Step;
import com.wiley.ranku.TestBase;
import com.wiley.ranku.datamodel.Element;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.By;

import java.util.ArrayList;

public class RequestForInfo extends TestBase {


    @Step("Validate all label names are correctly displayed")
    public void verifyRFIFields() throws InterruptedException {


        ArrayList<Element> elements =  siteData.getAppPages().getRfi().getElements();

        Thread.sleep(2000);

        for (Element e:elements) {
            String label = e.getLabel();
            if (label !=null) {
                System.out.println(e.getXpath());
                System.out.println(siteData.getAppPages().getRfi().getXpath("fieldLabel"));
                    String legend = driver.findElement(By.xpath(e.getXpath())).findElement(By.xpath(siteData.getAppPages().getRfi().getXpath("fieldLabel"))).getText();
                    System.out.println();

                    Assert.assertEquals(label,legend);

                }
        }

    }
}
