package com.wiley.ranku.steps;

import com.thoughtworks.gauge.Step;
import com.wiley.ranku.TestBase;
import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.thoughtworks.gauge.Step;
import com.wiley.ranku.TestBase;
import org.bson.Document;
import org.junit.Assert;

import java.util.HashMap;

import static com.mongodb.client.model.Filters.eq;

public class DatabaseVerificationSteplmpl extends TestBase {
    HashMap<String, String> dataset;

    @Step("Connect to Database and verify RFI data <testCaseID>")
    public void ConnectDBAndVerifyRFISubmit(String testcaseID){

        dataset = testData.getDataSetById(testcaseID);

        MongoClientURI uri = new MongoClientURI("mongodb://RankuDeveloper:OctopusShootBlackInk@aardvark-development-shard-00-00-kgjrm.gcp.mongodb.net/?authSource=admin&ssl=true");
        MongoClient mongoClient = new MongoClient(uri);
        MongoDatabase databasename = mongoClient.getDatabase("ranku_development");
        MongoCollection<Document> collection = databasename.getCollection("main.inquiries");
        //get mail
        String email = dataset.get("emailAddress");
        Document document = new Document();
        document = collection.find((eq("from.email",email))).sort(eq("createdAt",-1)).first();
        Assert.assertTrue(dataset.get("intrestProgram").contains(document.get("program_name").toString()));
        Document from = (Document) document.get("from");
        Assert.assertEquals(dataset.get("firstName"),from.get("first_name"));
        Assert.assertEquals(dataset.get("lastName"),from.get("last_name"));
    }

}
