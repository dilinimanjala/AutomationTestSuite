package com.wiley.ranku.datamodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.junit.Test;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TestData {

    @JsonProperty("TestDataSets")
    private ArrayList<TestDataSet> testDataSets;

    public ArrayList<TestDataSet> getTestdataset(){
        return testDataSets;
    }

    public TestDataSet getTestdataset(String testcaseID){

        TestDataSet testdataset = null;
            for (int i=0;i<testDataSets.size();i++){
                if (testDataSets.get(i).getTestcaseID().equals(testcaseID)){
                    testdataset =  testDataSets.get(i);
                    break;
                }
            }
            return testdataset;

    }



}
