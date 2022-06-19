package com.zarszz.date.epochs.domain.dto;

import lombok.*;

@Data
public class GetTransactionDto {

	@NonNull
	private Long startDate;

	@NonNull
	private Long endDate;

	private GetDateMode mode;

	private String timezone;
}
