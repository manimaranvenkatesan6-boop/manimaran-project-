package com.examly.springapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class AccountStatusLog {
    @Id
    private Long accid;
}
