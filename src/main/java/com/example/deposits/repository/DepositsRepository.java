package com.example.deposits.repository;

import com.example.deposits.model.Deposits;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepositsRepository extends JpaRepository<Deposits, Integer> {
    List<Deposits> findByCustomerId(int customerId);
}
