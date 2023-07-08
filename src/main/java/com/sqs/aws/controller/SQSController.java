package com.sqs.aws.controller;

import com.google.gson.Gson;
import com.sqs.aws.configuration.AwsSQSConfiguration;
import com.sqs.aws.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/queue")
public class SQSController {

    @Autowired
    private AwsSQSConfiguration awsSQSConfiguration;

    @Autowired
    private QueueMessagingTemplate queueMessagingTemplate;

    @Value("${cloud.aws.end-point.uri}")
    public String urlQueueAWS;

    @PostMapping
    public void sendToMessageQueue(@RequestBody User user) {
        String dataJson = new Gson().toJson(user);
        queueMessagingTemplate.convertAndSend(urlQueueAWS, dataJson);
    }
}
