package com.examly.springapp.service;

import com.examly.springapp.model.AccountStatusLog;
import java.util.List;
import java.util.Optional;

public interface AccountStatusLogService {
    AccountStatusLog saveAccountStatusLog(AccountStatusLog accountStatusLog);
    List<AccountStatusLog> getAllAccountStatusLogs();
    Optional<AccountStatusLog> getAccountStatusLogById(Long id);
    AccountStatusLog updateAccountStatusLog(AccountStatusLog accountStatusLog);
    void deleteAccountStatusLog(Long id);
}
