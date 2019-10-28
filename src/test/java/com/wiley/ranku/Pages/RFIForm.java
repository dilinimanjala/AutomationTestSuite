package com.wiley.ranku.Pages;

import com.wiley.ranku.TestBase;
import com.wiley.ranku.datamodel.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

public class RFIForm extends TestBase {

//    public void fillRFIForm() {
//
//        ArrayList<Element> elements = siteData.getAppPages().getRfi().getElements();
//
//        for (Element e : elements) {
//
//            if(e.getField().equals("dropdown")){
//                WebElement dropdown = driver.findElement(By.xpath(e.getXpath()));
//                if(dropdown!=null){
//                    selectDropDownList(dropdown, e.getData());
//                }
//            };
//
//            if(e.getField().equals("textbox")){
//                WebElement text = driver.findElement(By.xpath(e.getXpath()));
//                if(text!=null){
//                    setTextAs(text, e.getData());
//                }
//            };
//
//            if(e.getField().equals("checkbox")){
//                if(e.getData().equals("true")){
//                    WebElement checkbox = driver.findElement(By.xpath(e.getXpath()));
//                    if(checkbox!=null){
//                        selectCheckBox(checkbox);
//                    }
//                }
//            }
//        }
//        driver.findElement(By.xpath((siteData.getAppPages().getRfi().getXpath("submitRequest")))).click();
//    }
}
