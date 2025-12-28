package com.examly.springapp.service;

import com.examly.springapp.model.UserRoleMapping;
import java.util.List;
import java.util.Optional;

public interface UserRoleMappingService {
    UserRoleMapping saveUserRoleMapping(UserRoleMapping userRoleMapping);
    List<UserRoleMapping> getAllUserRoleMappings();
    Optional<UserRoleMapping> getUserRoleMappingById(Long id);
    UserRoleMapping updateUserRoleMapping(UserRoleMapping userRoleMapping);
    void deleteUserRoleMapping(Long id);
}
