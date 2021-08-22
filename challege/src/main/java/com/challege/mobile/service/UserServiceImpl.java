/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.challege.mobile.service;

import com.challege.mobile.CheckNumber;
import com.challege.mobile.dto.StatusStatisticsDTO;
import com.challege.mobile.model.User;
import com.challege.mobile.repository.UserRepository;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Andrea
 */

@Service
@Transactional
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserRepository userRepository;
    @Autowired
    CheckNumber checkNumber;

    @Override
    public List<User> getAllUsers() {
    return userRepository.findAll();
    }


    @Override
    @Modifying
    public void saveUser(User user) {
    this.userRepository.save(user); 
    }

    @Override
    public User getUserById(long id) {
        Optional <User> optional = userRepository.findById(id);
        User user = null;
        if (optional.isPresent()) {
            user = optional.get();
        } else {
            throw new RuntimeException(" User not found for id: " + id);
        }
        return user;
    }


    @Override
    public User getUserByIdName(String idName) {
        Optional <User> optional = userRepository.findByIdNameIs(idName);
        User user = null;
        if (optional.isPresent()) {
            user = optional.get();
        } else {
           throw new RuntimeException(" User not found for IdName: " + idName);
         
        }
        return user;
    }

    @Override
    public List<User> findByStatusInUser(String status) {
      return userRepository.findByStatusInUser(status);
    }

    @Override
    public List<StatusStatisticsDTO> getStatusCount() {
       return userRepository.findStatusCount();
    }

    @Override
    @Modifying
    public void loadFile(MultipartFile file) {
        try {
            CSVReader reader = new CSVReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8));
       
            String[] nextLine;

            while ((nextLine = reader.readNext()) != null) {
                try {
                    saveUser(checkNumber.check(nextLine[0], nextLine[1]));
                } catch (Exception ex) {
                      Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        
        } catch (CsvValidationException | IOException ex) {
            Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        
        }
    }

    @Override
    @Modifying
    public void deleteAllUsers() {
        userRepository.deleteAll();
    }
    
    
    
}
