package com.challege.mobile.service;

import com.challege.mobile.dto.StatusStatisticsDTO;
import com.challege.mobile.model.User;
import com.challege.mobile.repository.UserRepository;
import java.util.List;
import java.util.Optional;
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

    @Override
    public List<User> getAllUsers() {
    return userRepository.findAll();
    }


    @Override
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
    public void deleteUserById(long id) {
       this.userRepository.deleteById(id); 
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
    
    
}
