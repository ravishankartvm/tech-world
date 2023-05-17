package com.learning.techworld.PolicyEventProducer.Producer;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.techworld.PolicyEventProducer.model.PolicyEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Configuration
@Slf4j
public class PolicyEventProducer {

    @Autowired
    KafkaTemplate<Integer,String> kafkaTemplate;
    @Autowired
    ObjectMapper objectMapper;

    public void sendPolicyEvents(PolicyEvent policyEvent) throws JsonProcessingException {
        log.info("Invoking Kafka producer");
        Integer key= policyEvent.getPolicyEventId();
        String value= objectMapper.writeValueAsString(policyEvent);
        ListenableFuture<SendResult<Integer, String>> listenableFuture=  kafkaTemplate.send("policy-topic",key,value);
        listenableFuture.addCallback(new ListenableFutureCallback<SendResult<Integer, String>>() {
            @Override
            public void onFailure(Throwable ex) {
            log.info("Failure in producing the message {}" ,ex);
          }
            @Override
            public void onSuccess(SendResult<Integer, String> result) {
                handleSuccess(key,value,result);
            }
        });
    }

    private void handleSuccess(Integer key, String value, SendResult<Integer, String> result) {
        log.info("Message is successfully produced Key: {}  value: {}  result: {} " ,key,value, result);
    }


}
