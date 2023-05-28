package com.example.accounts.controller;

import com.example.accounts.config.AccountsServiceConfig;
import com.example.accounts.model.*;
import com.example.accounts.repository.AccountsRepository;
import com.example.accounts.service.CardsFeignClient;
import com.example.accounts.service.LoansFeignClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@Slf4j
@RestController
public class AccountsController {

    private static Logger logger = LoggerFactory.getLogger(AccountsController.class);

    @Autowired
    AccountsRepository accountsRepository;

    @Autowired
    AccountsServiceConfig config;

    @Autowired
    CardsFeignClient cardsFeignClient;

    @Autowired
    LoansFeignClient loansFeignClient;

    @PostMapping("/myAccounts")
    public List<Accounts> getAccounts(@RequestBody Customer customer){
        logger.info("/myAccounts AccountController method started");
        List<Accounts> accounts = accountsRepository.findByCustomerId(customer.getCustomerId());
        logger.info("/myAccounts AccountController method ended");
        if(accounts != null && accounts.size() > 0){
            return accounts;
        }
        return null;
    }

    @GetMapping("/myAccounts/properties")
    public String getPropertyDetails() throws JsonProcessingException{
        logger.info("/myAccounts/properties AccountController method started");
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        Properties properties = new Properties(config.getMsg(), config.getBuildVersion(),
            config.getMailDetails(), config.getActiveBranches());
        String jsonStr = ow.writeValueAsString(properties);
        logger.info("/myAccounts/properties AccountController method ended");
        return jsonStr;
    }

    @PostMapping("/allDetails")
    @CircuitBreaker(name="detailsForCustomerSupportApp", fallbackMethod = "myCustomerDetailsFallBack")
    public CustomerDetails getAllDetails(@RequestBody Customer customer){
        logger.info("/allDetails AccountController method started");
        List<Accounts> accounts = accountsRepository.findByCustomerId(customer.getCustomerId());
        List<Loans> loans = loansFeignClient.getAllLoans(customer);
        List<Cards> cards = cardsFeignClient.getAllCards(customer);
        CustomerDetails customerDetails = new CustomerDetails();
        customerDetails.setAccounts(accounts);
        customerDetails.setCards(cards);
        customerDetails.setLoans(loans);
        logger.info("/allDetails AccountController method ended");
        return customerDetails;
    }

    private CustomerDetails myCustomerDetailsFallBack(Customer customer, Throwable t){
        logger.info("Fallback Method for Circuit Breaker started");
        List<Accounts> accounts = accountsRepository.findByCustomerId(customer.getCustomerId());
        List<Cards> cards = cardsFeignClient.getAllCards(customer);
        CustomerDetails customerDetails = new CustomerDetails();
        customerDetails.setAccounts(accounts);
        customerDetails.setCards(cards);
        logger.info("Fallback Method for Circuit Breaker ended");
        return customerDetails;
    }
}
