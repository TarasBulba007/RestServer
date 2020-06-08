package com.tarasPlus.serverRest.service;

import com.tarasPlus.serverRest.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleService extends JpaRepository<Role, Long> {
}
