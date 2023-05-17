package com.learning.techworld.PolicyEventProducer.service;

import com.learning.techworld.PolicyEventProducer.model.Policy;
import com.learning.techworld.PolicyEventProducer.model.PolicyEvent;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class PolicyEventProducerService {
    public PolicyEvent saveEvent(Policy policy){
        Random random=new Random();
        return PolicyEvent.builder().policyEventId(random.nextInt()).policy(policy).build();
    }
}
