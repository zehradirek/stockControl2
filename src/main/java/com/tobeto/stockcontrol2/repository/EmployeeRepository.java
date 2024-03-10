package com.tobeto.stockcontrol2.repository;

import com.tobeto.stockcontrol2.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
    Optional<Employee> findByEmployeeName(String name);

    Optional<Employee> findByEmail(String email);
}
