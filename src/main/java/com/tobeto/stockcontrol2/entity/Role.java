package com.tobeto.stockcontrol2.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Data
@NamedQuery(name = "Role.findAll", query = "SELECT r FROM Role r")
@AllArgsConstructor
@NoArgsConstructor
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String role;

    @ManyToOne()
    @JoinColumn(name="employee_id")
    private Employee employees;

}