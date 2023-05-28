package com.example.deposits.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Getter @Setter @ToString
public class Deposits {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int depositId;

    private int customerId;

    private LocalDate startDt;

    private LocalDate endDt;
}
