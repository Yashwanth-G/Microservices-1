package com.example.cards.controller;

import com.example.cards.config.CardsServiceConfig;
import com.example.cards.model.Cards;
import com.example.cards.model.Customer;
import com.example.cards.model.Properties;
import com.example.cards.repository.CardsRepository;
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
public class CardsController {

    private Logger logger = LoggerFactory.getLogger(CardsController.class);

    @Autowired
    CardsRepository cardsRepository;

    @Autowired
    CardsServiceConfig config;

    @PostMapping("/myCards")
    public List<Cards> getAllCards(@RequestBody Customer customer){
        logger.info("/myCards method inside CardsController started");
        List<Cards> cards = cardsRepository.findByCustomerId(customer.getCustomerId());
        logger.info("/myCards method inside CardsController ended");
        if(cards != null && cards.size() > 0){
            return cards;
        }
        return null;
    }

    @GetMapping("/myCards/properties")
    public String getPropertyDetails() throws JsonProcessingException {
        logger.info("/myCards/properties method CardsController started");
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        Properties properties = new Properties(config.getMsg(), config.getBuildVersion(),
                config.getMailDetails(), config.getActiveBranches());
        String jsonStr = ow.writeValueAsString(properties);
        logger.info("/myCards/properties method CardsController ended");
        return jsonStr;
    }

}
