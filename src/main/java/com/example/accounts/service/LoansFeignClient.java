package com.example.accounts.service;

import com.example.accounts.model.Customer;
import com.example.accounts.model.Loans;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient("loans")
public interface LoansFeignClient {

    @RequestMapping(method= RequestMethod.POST, value="/myLoans", consumes = "application/json")
    List<Loans> getAllLoans(@RequestBody Customer customer);

}
