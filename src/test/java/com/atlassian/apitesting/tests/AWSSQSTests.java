package com.atlassian.apitesting.tests;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.NameMap;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.ReturnValue;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.util.Topics;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.Message;
import com.atlassian.api.entities.Employee;
import com.atlassian.apitesting.Group;
import com.google.inject.Inject;
import org.testng.annotations.Test;

import java.util.List;

public class AWSSQSTests {

    @Inject
    Employee employee;

    /*@Test(groups = {Group.REGRESSION, Group.SMOKE})
    public void publishMessageToSNSandRetrieveMessage() {

        AWSCredentials awsCredentials = new BasicAWSCredentials("", "");

        UpdateItemSpec updateItemSpec = new UpdateItemSpec().withPrimaryKey("Id", 121)
                .withUpdateExpression("set #na = :val1").withNameMap(new NameMap().with("#na", "NewAttribute"))
                .withValueMap(new ValueMap().withString(":val1", "Some value")).withReturnValues(ReturnValue.ALL_NEW);

        employee.setName("");

        AmazonSNS amazonSNSClient = AmazonSNSClientBuilder.standard().build();
        String topicARN = "arn:aws:sns:ap-south-1:845178261619:TransactionTopic";

        // Creating a Queue

        AmazonSQS sqs = AmazonSQSClientBuilder.standard().withRegion("ap-south-1").build();
        CreateQueueRequest createQueueRequest =  new CreateQueueRequest("QueueName3");
        sqs.createQueue(createQueueRequest);

        String queueURl = sqs.getQueueUrl("QueueName3").getQueueUrl();
        Topics.subscribeQueue(amazonSNSClient, sqs, topicARN, queueURl);


        //Calling Post Transaction

        amazonSNSClient.publish(new PublishRequest(topicARN, "Message Body").withSubject("Subject"));
        List<Message> messages = sqs.receiveMessage(queueURl).getMessages();

        String message = messages.get(0).getBody();
        String receiptHandle = messages.get(0).getReceiptHandle();
        sqs.deleteMessage(queueURl, receiptHandle);


        sqs.deleteQueue(queueURl);
    }*/
}
