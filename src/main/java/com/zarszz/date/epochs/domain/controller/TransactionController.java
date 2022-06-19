package com.zarszz.date.epochs.domain.controller;

import com.zarszz.date.epochs.domain.dto.CreateTransactionDto;
import com.zarszz.date.epochs.domain.dto.GetDateMode;
import com.zarszz.date.epochs.domain.dto.GetTransactionDto;
import com.zarszz.date.epochs.persistence.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/transactions")
public class TransactionController {
	@Autowired
	TransactionService transactionService;

	@PostMapping()
	public ResponseEntity<?> create(@RequestBody CreateTransactionDto createTransactionDto) {
		var transaction = transactionService.create(createTransactionDto);
		return ResponseEntity.ok(transaction);
	}

	@GetMapping
	public ResponseEntity<?> get(@Validated GetTransactionDto getTransactionDto) {
		ResponseEntity<?> response = null;
		if (getTransactionDto.getMode().equals(GetDateMode.GET_RAW_NUMBER) || getTransactionDto.getMode() == null)
			response = ResponseEntity.ok().body(transactionService.get(getTransactionDto));
		else if (getTransactionDto.getMode().equals(GetDateMode.GET_WITH_TIMEZONE_FROM_FE))
			response = ResponseEntity.ok().body(transactionService.getTransactionWithTimeZone(getTransactionDto));
		return response;
	}
}
