package com.tobeto.stockcontrol2.dto;

import com.tobeto.stockcontrol2.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequestDTO {
    private UUID id;
    private String employeeName;
    private String password;
    private Role role;


}

