package com.zarszz.date.epochs.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.ZoneId;

@Getter
@Setter
public class GetTransactionResponseDto {
	private Instant date;
	private Long id;
	public GetTransactionResponseDto(Long id, Long epoch, String timezone) {
		this.id = id;
		this.date = Instant.ofEpochMilli(epoch).atZone(ZoneId.of(timezone)).toInstant();
	}
}
