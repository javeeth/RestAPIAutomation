package com.atlassian.apitesting.tests;

import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.util.Topics;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.Message;
import com.atlassian.apitesting.Group;
import org.testng.annotations.Test;

import java.util.List;

public class AWSSQSTests {

    @Test(groups = {Group.REGRESSION, Group.SMOKE})
    public void publishMessageToSNSandRetrieveMessage() {


        AmazonSNSClient amazonSNSClient = (AmazonSNSClient) AmazonSNSClientBuilder.standard().build();
        String topicARN = "";

        // Creating a Queue

        AmazonSQS sqs = AmazonSQSClientBuilder.standard().withRegion("").build();
        CreateQueueRequest createQueueRequest =  new CreateQueueRequest("QueueName");
        sqs.createQueue(createQueueRequest);

        String queueURl = sqs.getQueueUrl("QueueName").getQueueUrl();
        Topics.subscribeQueue(amazonSNSClient, sqs, topicARN, queueURl);


        amazonSNSClient.publish(new PublishRequest(topicARN, "Message Body").withSubject("Subject"));
        List<Message> messages = sqs.receiveMessage(queueURl).getMessages();

        String message = messages.get(0).getBody();
        String receiptHandle = messages.get(0).getReceiptHandle();
        sqs.deleteMessage(queueURl, receiptHandle);
        sqs.deleteQueue(queueURl);

    }
}
