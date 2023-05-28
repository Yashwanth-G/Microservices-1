package com.example.accounts.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter @Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class Loans {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int loanNumber;

    private int customerId;

    private LocalDate startDt;

    private String loanType;

    private int totalLoan;

    private int amountPaid;

    private int outstandingAmount;

    private LocalDate createDt;
}
