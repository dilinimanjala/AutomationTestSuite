package com.wiley.ranku.steps;

import com.thoughtworks.gauge.Step;
import com.wiley.ranku.TestBase;
import com.wiley.ranku.datamodel.Element;
import com.wiley.ranku.datamodel.TestDataSet;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;


public class RFIStepImpl extends TestBase {
    @Step("Click on RFI button")
    public void clickonRequestInformationButton() {
        WebElement rfi = driver.findElement(By.xpath(siteData.getAppPages().getHome().getXpath("requestInfo")));
        clickElement(rfi);
    }

    @Step("Fill the RFI form <TC_RFI_01>")
    public void fillRFIForm(String testcaseID) {

        TestDataSet dataset=testData.getTestdataset(testcaseID);

        ArrayList<Element> elements = siteData.getAppPages().getRfi().getElements();

        for (Element e : elements) {
            String fieldname=e.getName();

            if(driver.findElement(By.xpath(e.getXpath())).isDisplayed()){
                WebElement element = driver.findElement(By.xpath(e.getXpath()));

                switch (fieldname){
                    case("degreeLevel"):
                        String degreeLevel=dataset.getDegreeLevel();
                        if(degreeLevel!=null){
                            selectDropDownList(element, degreeLevel);
                        }
                        break;
                    case("fieldOfStudy"):
                        String fieldOfStudy=dataset.getFieldOfStudy();
                        if(fieldOfStudy!=null){
                            selectDropDownList(element, fieldOfStudy);
                        }
                        break;
                    case("intrestProgram"):
                        String intrestProgram=dataset.getIntrestProgram();
                        if(intrestProgram!=null){
                            selectDropDownList(element, intrestProgram);
                        }
                        break;
                    case("firstName"):
                        String firstName=dataset.getFirstName();
                        if(firstName!=null){
                            setTextAs(element, firstName);
                        }
                        break;
                    case("lastName"):
                        String lastName=dataset.getLastName();
                        if(lastName!=null){
                            setTextAs(element, lastName);
                        }
                        break;
                    case("emailAddress"):
                        String emailAddress=dataset.getEmailAdress();
                        if(emailAddress!=null){
                            setTextAs(element, emailAddress);
                        }
                        break;
                    case("phoneNumber"):
                        String phoneNumber=dataset.getPhoneNumber();
                        if(phoneNumber!=null){
                            setTextAs(element, phoneNumber);
                        }
                        break;
                    case("country"):
                        String country=dataset.getCountry();
                        if(country!=null){
                            selectDropDownList(element, country);
                        }
                        break;
                    case("zipcode"):
                        String zipcode=dataset.getZipcode();
                        if(zipcode!=null){
                            setTextAs(element, zipcode);
                        }
                        break;
                    case("levelOfEducation"):
                        String levelOfEducation=dataset.getLevelOfEducation();
                        if(levelOfEducation!=null){
                            selectDropDownList(element, levelOfEducation);
                        }
                        break;
                    case("pursuingDegree"):
                        String pursuingDegree=dataset.getPursuingDegree();
                        if(pursuingDegree!=null){
                            selectDropDownList(element, pursuingDegree);
                        }
                        break;
                    case("question"):
                        String question=dataset.getQuestion();
                        if(question!=null){
                            selectDropDownList(element, question);
                        }
                        break;
                    case("contactCheckBox"):
                        String contactCheckBox=dataset.getContactCheckBox();
                        if(Boolean.parseBoolean(contactCheckBox)){
                            selectCheckBox(element);
                        }
                        break;
                }
            }
        }
        driver.findElement(By.xpath((siteData.getAppPages().getRfi().getXpath("submitRequest")))).click();

    }


    @Step("Verify Request Information Submitted")
    public void verifyRequestInformationSubmited() {
        WebElement thankYou = driver.findElement(By.xpath(siteData.getAppPages().getRfi().getXpath("thankYou")));
        checkElementVisible(thankYou);
        WebElement closeRFIThankYou = driver.findElement(By.xpath(siteData.getAppPages().getRfi().getXpath("closeThankYou")));
        clickElement(closeRFIThankYou);
    }



}
