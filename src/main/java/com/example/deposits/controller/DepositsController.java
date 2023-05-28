package com.example.deposits.controller;

import com.example.deposits.config.DepositsServiceConfig;
import com.example.deposits.model.Customer;
import com.example.deposits.model.Deposits;
import com.example.deposits.model.Properties;
import com.example.deposits.repository.DepositsRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(
        name= "Deposit Service - DepositsController",
        description = "DepositsController Exposes REST APIs for Deposits Service"
)
public class DepositsController {

    private Logger logger = LoggerFactory.getLogger(DepositsController.class);

    @Autowired
    DepositsRepository depositsRepository;

    @Autowired
    DepositsServiceConfig config;


    @Operation(
            summary = "Get Deposits REST API",
            description = "Get Deposits REST API is used to get deposits details"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 Created"
    )
    @PostMapping("/myDeposits")
    public List<Deposits> getAllDeposits(@RequestBody Customer customer){
        logger.info("/myDeposits inside DepositsController started");
        List<Deposits> deposits = depositsRepository.findByCustomerId(customer.getCustomerId());
        logger.info("/myDeposits inside DepositsController ended");
        if(deposits != null && deposits.size() > 0){
            return deposits;
        }
        return null;
    }

    @Operation(
            summary = "Get Deposits Properties REST API",
            description = "Get Deposits related Properties REST API"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 Created"
    )
    @GetMapping("/myDeposits/properties")
    public String getPropertyDetails() throws JsonProcessingException {
        logger.info("/myDeposits/properties DepositsController started");
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        Properties properties = new Properties(config.getMsg(), config.getBuildVersion(),
                config.getMailDetails(), config.getActiveBranches());
        String jsonStr = ow.writeValueAsString(properties);
        logger.info("/myDeposits/properties DepositsController started");
        return jsonStr;
    }
}
