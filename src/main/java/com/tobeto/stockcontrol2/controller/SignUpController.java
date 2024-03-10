package com.tobeto.stockcontrol2.controller;

import com.tobeto.stockcontrol2.dto.login.LoginRequestDTO;
import com.tobeto.stockcontrol2.dto.login.LoginResponseDTO;
import com.tobeto.stockcontrol2.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tobeto.stockcontrol2.service.EmployeeService;
import com.tobeto.stockcontrol2.service.TokenService;

@RestController
@RequestMapping("/api/v1")
public class SignUpController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/signup")
    public ResponseEntity<LoginResponseDTO> signup(@RequestBody LoginRequestDTO dto) {
        Employee employee = employeeService.signUp(dto.getEmail(), dto.getPassword());
        String token = tokenService.createToken(employee);
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

}
