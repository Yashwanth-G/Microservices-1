package com.example.deposits.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
@Schema(
        description = "Customer Model"
)
@Getter @Setter
public class Customer {

    @Schema(
            description = "customer ID"
    )
    private int customerId;
}
