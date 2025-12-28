package com.examly.springapp.service;

import com.examly.springapp.model.UserAccount;
import java.util.List;
import java.util.Optional;

public interface UserAccountService {
    UserAccount saveUserAccount(UserAccount userAccount);
    List<UserAccount> getAllUserAccounts();
    Optional<UserAccount> getUserAccountById(Long id);
    UserAccount updateUserAccount(UserAccount userAccount);
    void deleteUserAccount(Long id);
}
