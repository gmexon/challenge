/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.challege.mobile.controller;

import com.challege.mobile.CheckNumber;
import com.challege.mobile.model.User;
import com.challege.mobile.service.UserService;
import io.swagger.v3.oas.annotations.Parameter;
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

    @GetMapping("/user/{id_name}")
    public ResponseEntity<User> user(@Parameter(description = "example: 103300640") @PathVariable("id_name") String idName) {
        User user;
        try {
            user = userService.getUserByIdName(idName.trim());
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);

    }

    @GetMapping("/allusers/")
    public ResponseEntity<List<User>> users() {
        List<User> list;
        try {
            list = userService.getAllUsers();
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/users/{status}")
    public ResponseEntity<List<User>> users(@Parameter(description = "OK FIXED WRONG") @PathVariable("status") String stato) {
        List<User> list;
        try {
            list = userService.findByStatusInUser(stato.trim().toUpperCase());
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/check/{number}")
    public ResponseEntity<User> check(@Parameter(description = "example: 27730276061") @PathVariable("number") String number) {
        return new ResponseEntity<>(checkNumber.check("", number.trim()), HttpStatus.OK);
    }
    
     @GetMapping("/statistics")
     public ResponseEntity<List<StatusStatisticsDTO>> statistics() {
      List<StatusStatisticsDTO> list;
       try {
            list = userService.getStatusCount();
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);      
     }

}
