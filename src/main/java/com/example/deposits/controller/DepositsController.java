package com.example.deposits.controller;

import com.example.deposits.config.DepositsServiceConfig;
import com.example.deposits.model.Customer;
import com.example.deposits.model.Deposits;
import com.example.deposits.model.Properties;
import com.example.deposits.repository.DepositsRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DepositsController {

    private Logger logger = LoggerFactory.getLogger(DepositsController.class);

    @Autowired
    DepositsRepository depositsRepository;

    @Autowired
    DepositsServiceConfig config;

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
