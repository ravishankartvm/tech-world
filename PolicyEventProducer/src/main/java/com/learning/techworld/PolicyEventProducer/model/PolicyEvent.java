package com.learning.techworld.PolicyEventProducer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PolicyEvent {

    private Integer policyEventId;
    private Policy policy;

}
