package com.examly.springapp.service;

import com.examly.springapp.model.Role;
import java.util.List;
import java.util.Optional;

public interface RoleService {
    Role saveRole(Role role);
    List<Role> getAllRoles();
    Optional<Role> getRoleById(Long id);
    Role updateRole(Role role);
    void deleteRole(Long id);
}
