package com.loginexcel.loginexcel.service;

import com.loginexcel.loginexcel.model.userDetails;
import com.loginexcel.loginexcel.repository.userDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class userService {
    @Autowired
    private final userDetailsRepo userDetailsRepo;

    public userService(userDetailsRepo userDetailsRepo) {
        this.userDetailsRepo = userDetailsRepo;
    }


    public boolean checkCredentials(String username, String password) {
            userDetails user = userDetailsRepo.findByUsername(username);

            if (user != null && user.getPassword().equals(password)) {
                return true;
            }
            return false;
        }

    }


