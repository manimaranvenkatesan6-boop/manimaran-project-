package com.examly.springapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class UserAccount {
    @Id
    private long uid;
    
}
