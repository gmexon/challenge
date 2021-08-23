package com.challege.mobile.service;

import com.challege.mobile.CheckNumber;
import com.challege.mobile.dto.StatusStatisticsDTO;
import com.challege.mobile.model.User;
import com.challege.mobile.repository.UserRepository;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Andrea
 */

@Service
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
    public void loadFile(InputStream is) {
        CSVReader reader = null;
        
        try {
            reader = new CSVReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String[] nextLine;

            while ((nextLine = reader.readNext()) != null) {
                try {
                    userRepository.save(checkNumber.check(nextLine[0], nextLine[1]));
                } catch (Exception ex) {
                      Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } catch (CsvValidationException | IOException ex) {
            Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        
        } finally {
            if (is != null) 
                try {
                is.close();
            } catch (IOException e) {
                System.err.println("Failed to close Streams");
            }
            if (reader != null) 
                try {
                reader.close();
            } catch (IOException ioe) {
                System.err.println("Failed to close CSVReader");
            }
        }
    }

    @Override
    public void deleteAllUsers() {
        userRepository.deleteAll();
    }
    
    
    
}
