package com.example.accounts.repository;

import com.example.accounts.model.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AccountsRepository extends JpaRepository<Accounts, Integer> {

    List<Accounts> findByCustomerId(int customerId);

}
