package com.zarszz.date.epochs.persistence.service;

import com.zarszz.date.epochs.domain.dto.CreateTransactionDto;
import com.zarszz.date.epochs.domain.dto.GetTransactionDto;
import com.zarszz.date.epochs.domain.dto.GetTransactionResponseDto;
import com.zarszz.date.epochs.persistence.entity.Transaction;
import com.zarszz.date.epochs.persistence.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {

	@Autowired
	TransactionRepository transactionRepository;

	public Transaction create(CreateTransactionDto createTransactionDto) {
		var transaction = new Transaction();
		transaction.setDate(createTransactionDto.getDate());
		return transactionRepository.save(transaction);
	}

	public List<Transaction> get(GetTransactionDto getTransactionDto) {
		return transactionRepository.findByDateBetween(getTransactionDto.getStartDate(), getTransactionDto.getEndDate());
	}

	public List<GetTransactionResponseDto> getTransactionWithTimeZone(GetTransactionDto getTransactionDto) {
		return transactionRepository
				.findByDateBetween(getTransactionDto.getStartDate(), getTransactionDto.getEndDate()).stream()
				.map(trx -> new GetTransactionResponseDto(trx.getId(), trx.getDate(), getTransactionDto.getTimezone()))
				.collect(Collectors.toList());
	}
}
