package com.examly.springapp.repository;

import com.examly.springapp.model.UserRoleMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleMappingRepo extends JpaRepository<UserRoleMapping, Long> {
}
