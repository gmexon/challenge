/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.challege.mobile;


import com.challege.mobile.repository.UserRepository;
import com.challege.mobile.service.UserServiceImpl;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

/**
 *
 * @author Andrea
 */
@Component
public class DemoData implements ApplicationRunner {

    @Autowired
    UserServiceImpl userServiceImpl;
    @Autowired
    CheckNumber checkNumber;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        CSVReader reader;
        try {
           InputStream in = new ClassPathResource("data/South_African_Mobile_Numbers.csv").getInputStream();
   
         
           reader = new CSVReader(new InputStreamReader(in, StandardCharsets.UTF_8));

            String[] nextLine;

            while ((nextLine = reader.readNext()) != null) {  
                try {
                 
                 userServiceImpl.saveUser(checkNumber.check(nextLine[0],nextLine[1]));
                } catch (Exception ex) {ex.printStackTrace();}
                        
               
            }
        
        
        } catch (IOException e) {
           e.printStackTrace();
        }
        
        

    }
}
