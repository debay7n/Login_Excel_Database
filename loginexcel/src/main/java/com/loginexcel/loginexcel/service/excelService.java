package com.loginexcel.loginexcel.service;

import com.loginexcel.loginexcel.helper.excelHelper;
import com.loginexcel.loginexcel.model.userDetails;
import com.loginexcel.loginexcel.repository.userDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;

@Service
public class excelService  {
     @Autowired
     private userDetailsRepo userDetailsRepo;
    public void save(MultipartFile file){
        try {
           List<userDetails> userDetails  = excelHelper.convertExcelToList(file.getInputStream());
            this.userDetailsRepo.saveAll(userDetails);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<userDetails> getAllUser(){
    return this.userDetailsRepo.findAll();
    }

}
