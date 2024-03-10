package com.tobeto.stockcontrol2.controller;

import com.tobeto.stockcontrol2.config.ModelMapperConfig;
import com.tobeto.stockcontrol2.dto.EmployeeRequestDTO;
import com.tobeto.stockcontrol2.dto.EmployeeResponseDTO;
import com.tobeto.stockcontrol2.entity.Employee;
import com.tobeto.stockcontrol2.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/employees")
@AllArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    private final ModelMapperConfig modelMapperConfig;


    // Çalışanı id'ye göre getirme
    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeResponseDTO> getEmployee(@PathVariable UUID employeeId) {
        Optional<Employee> employee = employeeService.getEmployeeById(employeeId);

        if (employee.isPresent()) {
            EmployeeResponseDTO employeeDTO = this.modelMapperConfig.forResponse().map(employee.get(), EmployeeResponseDTO.class);
            return ResponseEntity.ok(employeeDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Tüm çalışanları getirme
    @GetMapping("/all")
    public ResponseEntity<List<EmployeeResponseDTO>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        List<EmployeeResponseDTO> employeeDTOs = employees.stream().map(employee -> this.modelMapperConfig
                .forResponse().map(employee, EmployeeResponseDTO.class)).toList();

        return ResponseEntity.ok(employeeDTOs);
    }

    // Yeni çalışan ekleme
    @PostMapping
    public ResponseEntity<Void> saveEmployee(@RequestBody EmployeeRequestDTO employeeDTO) {
        Employee employee = this.modelMapperConfig.forRequest().map(employeeDTO, Employee.class);
        employeeService.saveEmployee(employee);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // Çalışanı güncelleme
    @PutMapping("/{employeeId}")
    public ResponseEntity<Void> updateEmployee(@PathVariable UUID employeeId, @RequestBody EmployeeRequestDTO employeeDTO) {
        Optional<Employee> existingEmployee = employeeService.getEmployeeById(employeeId);

        if (existingEmployee.isPresent()) {
            Employee employee = this.modelMapperConfig.forRequest().map(employeeDTO, Employee.class);
            employee.setId(employeeId);
            employeeService.updateEmployee(employee);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Çalışanı silme
    @DeleteMapping("/{employeeId}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable UUID employeeId) {
        Optional<Employee> existingEmployee = employeeService.getEmployeeById(employeeId);

        if (existingEmployee.isPresent()) {
            employeeService.deleteEmployee(existingEmployee.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}