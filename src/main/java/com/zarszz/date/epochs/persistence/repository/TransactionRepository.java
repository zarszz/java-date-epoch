package com.zarszz.date.epochs.persistence.repository;

import com.zarszz.date.epochs.persistence.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	List<Transaction> findByDateBetween(Long startDate, Long endDate);
}