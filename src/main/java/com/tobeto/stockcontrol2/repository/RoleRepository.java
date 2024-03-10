package com.tobeto.stockcontrol2.repository;

import com.tobeto.stockcontrol2.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
}