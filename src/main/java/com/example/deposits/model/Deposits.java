package com.example.deposits.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Getter @Setter @ToString
@Schema(
        description = "Deposits Model"
)
public class Deposits {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(
            description = "Deposit ID"
    )
    private int depositId;

    @Schema(
            description = "Customer ID"
    )
    private int customerId;

    @Schema(
            description = "Start Date"
    )
    private LocalDate startDt;

    @Schema(
            description = "End Date"
    )
    private LocalDate endDt;
}
