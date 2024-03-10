package com.tobeto.stockcontrol2.service;

import com.tobeto.stockcontrol2.entity.Employee;
import com.tobeto.stockcontrol2.repository.EmployeeRepository;
import com.tobeto.stockcontrol2.repository.RoleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    public Optional<Employee> getEmployeeByName(String name) {
        return employeeRepository.findByEmployeeName(name);
    }

    @Transactional
    public void saveEmployee(Employee employee) {
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        employeeRepository.save(employee);
    }

    @Transactional
    public void deleteEmployee(Employee employee) {
        employeeRepository.delete(employee);
    }

    @Transactional
    public void updateEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Transactional
    public boolean updatePassword(UUID employeeId, String oldPassword, String newPassword) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);

        return optionalEmployee.map(employee -> {
            if (passwordEncoder.matches(oldPassword, employee.getPassword())) {
                employee.setPassword(passwordEncoder.encode(newPassword));
                employeeRepository.save(employee);
                return true;
            } else {
                return false; // Eski şifre doğru değilse
            }
        }).orElse(false); // Employee bulunamadıysa
    }

    @Transactional
    public void updateRole(Employee employee) {
       Employee optionalEmployee = employeeRepository.findById(employee.getId())
               .orElseThrow(() -> new RuntimeException("Employee not found"));
        optionalEmployee.setRole(employee.getRole());
        employeeRepository.save(employee);
    }

    @Transactional
    public void deleteEmployeeById(UUID employeeId) {
        employeeRepository.deleteById(employeeId);
    }

    @Transactional
    public Optional<Employee> getEmployeeById(UUID employeeId) {
        return employeeRepository.findById(employeeId);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeByEmail(String email) {
        return employeeRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    public Employee signUp(String email, String password) {
        checkIfEmailExists(email);
        Employee users = new Employee();
        users.setEmail(email);
        users.setPassword(passwordEncoder.encode(password));
        return employeeRepository.save(users);
    }

    private void checkIfEmailExists(String email) {
        if (employeeRepository.findByEmployeeName(email).isPresent()) {
            throw new RuntimeException("Already email exists!");
        }
    }
}


