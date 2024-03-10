package com.tobeto.stockcontrol2.controller;


import com.tobeto.stockcontrol2.dto.login.LoginRequestDTO;
import com.tobeto.stockcontrol2.dto.login.LoginResponseDTO;
import com.tobeto.stockcontrol2.entity.Employee;
import com.tobeto.stockcontrol2.service.EmployeeService;
import com.tobeto.stockcontrol2.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class LoginController {
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private TokenService tokenService;

	@PostMapping("/login")
	public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginRequestDTO dto) {
		Employee employees = employeeService.getEmployeeByEmail(dto.getEmail());
		if(!passwordEncoder.matches(dto.getPassword(), employees.getPassword())) {
			String token = tokenService.createToken(employees);
			return ResponseEntity.ok(new LoginResponseDTO(token));
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
	}

}
