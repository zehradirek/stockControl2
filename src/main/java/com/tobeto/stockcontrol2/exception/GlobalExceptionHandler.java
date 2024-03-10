package com.tobeto.stockcontrol2.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Log4j2
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<GlobalExceptionDTO> error(Exception ex) {
		log.error(ex);
		GlobalExceptionDTO dto = new GlobalExceptionDTO();
		dto.setCode(1001);
		dto.setMesaj("Hata oluştu");
		return ResponseEntity.internalServerError().body(dto);
	}
	
	
}
