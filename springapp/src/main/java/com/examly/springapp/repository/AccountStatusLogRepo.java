package com.examly.springapp.repository;

import com.examly.springapp.model.AccountStatusLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountStatusLogRepo extends JpaRepository<AccountStatusLog, Long> {
}
