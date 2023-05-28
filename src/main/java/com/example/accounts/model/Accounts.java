package com.example.accounts.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter @Setter @ToString
@AllArgsConstructor
@NoArgsConstructor
public class Accounts {

    private int customerId;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int accountNumber;

    private String accountType;

    private String branchAddress;

    private LocalDate createDt;

}
