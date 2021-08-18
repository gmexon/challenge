/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.challege.mobile.service;

import com.challege.mobile.model.User;
import java.util.List;

/**
 *
 * @author Andrea
 */
public interface UserService {
    void saveUser(User user);
    User getUserById(long id);
    void deleteUserById(long id);
    List < User > getAllUsers();
    User getUserByIdName(String idName);
    List < User > findByStatusInUser(String status);
    
    

     
     
    
}
