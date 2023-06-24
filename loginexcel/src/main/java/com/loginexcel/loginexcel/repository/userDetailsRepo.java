package com.loginexcel.loginexcel.repository;

import com.loginexcel.loginexcel.model.userDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface userDetailsRepo extends JpaRepository<userDetails, Integer> {
    userDetails findByUsername(String username);

}


