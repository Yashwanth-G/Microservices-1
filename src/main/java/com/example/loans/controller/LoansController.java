package com.example.loans.controller;

import com.example.loans.config.LoansServiceConfig;
import com.example.loans.model.Customer;
import com.example.loans.model.Loans;
import com.example.loans.model.Properties;
import com.example.loans.repository.LoansRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class LoansController {

    private Logger logger = LoggerFactory.getLogger(LoansController.class);

    @Autowired
    LoansRepository loansRepository;

    @Autowired
    LoansServiceConfig config;

    @PostMapping("/myLoans")
    public List<Loans> getAllLoans(@RequestBody Customer customer){
        logger.info("/myLoans method inside Loans Controller started");
        List<Loans> loans = loansRepository.findByCustomerId(customer.getCustomerId());
        logger.info("/myLoans method inside Loans Controller ended");
        if(loans != null && loans.size() > 0){
            return loans;
        }
        return null;
    }

    @GetMapping("/myLoans/properties")
    public String getPropertyDetails() throws JsonProcessingException {
        logger.info("/myLoans/properties method LoansController started");
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        Properties properties = new Properties(config.getMsg(), config.getBuildVersion(),
                config.getMailDetails(), config.getActiveBranches());
        String jsonStr = ow.writeValueAsString(properties);
        logger.info("/myLoans/properties method LoansController ended");
        return jsonStr;
    }
}
