package com.example.FinancialTracker.repository;

import com.example.FinancialTracker.entity.TransactionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends CrudRepository<TransactionEntity, Long> {

    List<TransactionEntity> findAll();

}
