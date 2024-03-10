package com.tobeto.stockcontrol2.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GlobalExceptionDTO {
	private int code;
	private String mesaj;
}