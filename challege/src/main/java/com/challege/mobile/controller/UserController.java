/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.challege.mobile.controller;

import com.challege.mobile.CheckNumber;
import com.challege.mobile.model.User;
import com.challege.mobile.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Andrea
 */
@RestController
@RequestMapping("/v1/api")
public class UserController {
    
    @Autowired
    private UserService userService;
    @Autowired
    CheckNumber checkNumber;
    
    
    @GetMapping("/user/{idName}")
    public ResponseEntity<User> user (@PathVariable("idName") String idName) {
        User user = userService.getUserByIdName(idName);
    return new ResponseEntity<>(user, HttpStatus.OK);
   
    }
    
    @GetMapping("/users/{stato}")
    public ResponseEntity<List<User>> users(@PathVariable("stato") String stato) {
        List<User> list =userService.findByStatusInUser(stato);
    return new ResponseEntity<> ( list, HttpStatus.OK );
    }
    
       @GetMapping("/check/{number}")
    public ResponseEntity<User> check (@PathVariable("number") String number) {
    return new ResponseEntity<>(checkNumber.check("",number), HttpStatus.OK);
   
    } 
    

    
}
