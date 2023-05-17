package com.learning.techworld.PolicyEventProducer.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.learning.techworld.PolicyEventProducer.Producer.PolicyEventProducer;
import com.learning.techworld.PolicyEventProducer.model.Policy;
import com.learning.techworld.PolicyEventProducer.model.PolicyEvent;
import com.learning.techworld.PolicyEventProducer.service.PolicyEventProducerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/policy-event/producer/v1")
@Tag(name = "Policy Event", description = "Policy Event producer APIs")
public class PolicyEventController {
    @Autowired
    PolicyEventProducerService policyEventProducerService;
    @Autowired
    PolicyEventProducer policyEventProducer;

    @Operation(
            summary = "Save the Policy Details by Policy Event Id",
            description = "Issued Policies will be saved using this Api for the policies generated from different sources"
            )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = PolicyEvent.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PostMapping(value="/event/add",produces = "application/json")
    public ResponseEntity<PolicyEvent> savePolicyEvent(@RequestBody Policy policy) throws JsonProcessingException {
        PolicyEvent policyEvent = policyEventProducerService.saveEvent(policy);
        policyEventProducer.sendPolicyEvents(policyEvent);
        return new ResponseEntity<>(policyEvent, HttpStatus.CREATED);
    }
}
