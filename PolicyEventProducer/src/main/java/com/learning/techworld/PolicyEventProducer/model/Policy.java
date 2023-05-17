package com.learning.techworld.PolicyEventProducer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Policy {
    private long policyNumber;
    private String lineOfBusiness;
    private String policyTerm;
    private String policyType;
    private LocalDate effectiveDt;
    private LocalDate expirationDt;
    private double premium;
    private String policyStatus;
    private String source;

}
