/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.challege.mobile;


import com.challege.mobile.repository.UserRepository;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

/**
 *
 * @author Andrea
 */
@Component
public class DemoData implements ApplicationRunner {

    @Autowired
    UserRepository userRepository;
    @Autowired
    CheckNumber checkNumber;

    @Value("classpath:data/South_African_Mobile_Numbers.csv")
    Resource resource;

    @Override
    public void run(ApplicationArguments args) throws Exception {
       
         File fileName = resource.getFile();
         
              try (
                Reader fr = new FileReader(fileName, StandardCharsets.UTF_8);
                CSVReader reader = new CSVReaderBuilder(fr).withSkipLines(1).build(); 
                ) 
        
        {

            String[] nextLine;

            while ((nextLine = reader.readNext()) != null) {  
                try {
                 
                 userRepository.save(checkNumber.check(nextLine[0],nextLine[1]));
                } catch (Exception ex) {ex.printStackTrace();}
                        
               
            }
        }
        
        

    }
}