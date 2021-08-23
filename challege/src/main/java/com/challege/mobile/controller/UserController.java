package com.challege.mobile.controller;

import com.challege.mobile.CheckNumber;
import com.challege.mobile.dto.StatusStatisticsDTO;
import com.challege.mobile.model.User;
import com.challege.mobile.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Andrea
 */
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    CheckNumber checkNumber;

    @Operation(summary = "Get user by IdName", responses = {
    @ApiResponse(description = "Successful Operation", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))),
    @ApiResponse(responseCode = "404", description = "Not found", content = @Content) })
    @GetMapping("/user/{idName}")
    public ResponseEntity<User> user(@Parameter(description = "example: 103300640") @PathVariable("idName") String idName) {
        User user;
        try {
            user = userService.getUserByIdName(idName.trim());
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);

    }
    
    @Operation(summary = "Get a list of all users", responses = {
    @ApiResponse(description = "Successful Operation", responseCode = "200", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = User.class)) )),   
    @ApiResponse(responseCode = "500", description = "Error: Internal Server Error") })
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
    
    @Operation(summary = "Get a list of users filter by status", responses = {
    @ApiResponse(description = "Successful Operation", responseCode = "200", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = User.class)) )),    
    @ApiResponse(responseCode = "500", description = "Error: Internal Server Error") })
    @GetMapping("/users/{status}")
    public ResponseEntity<List<User>> users(@Parameter(name = "status", schema = @Schema(type = "string", allowableValues = {"OK", "FIXED", "WRONG"})) @PathVariable("status") String stato) {
        List<User> list;
        try {
            list = userService.findByStatusInUser(stato.trim().toUpperCase());
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    
    @Operation(summary = "Phone Number Validation", responses = {
    @ApiResponse(description = "Successful Operation", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))),
    @ApiResponse(responseCode = "500", description = "Error: Internal Server Error") })
    @GetMapping("/check/{number}")
    public ResponseEntity<User> check(@Parameter(description = "example: 27730276061") @PathVariable("number") String number) {
        try {
        return new ResponseEntity<>(checkNumber.check("check", number.trim()), HttpStatus.OK);
         } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        }

    @Operation(summary = "Counts all the records after the group by status", responses = {
    @ApiResponse(description = "Successful Operation", responseCode = "200", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = StatusStatisticsDTO.class)) )),    
    @ApiResponse(responseCode = "500", description = "Error: Internal Server Error") })
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

    @Operation(summary = "Upload file CSV", responses = {
    @ApiResponse(description = "Successful Operation", responseCode = "200" ),    
    @ApiResponse(responseCode = "500", description = "Error: Internal Server Error"),
    })
    @PostMapping(value = "/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> upload(@RequestPart("file") MultipartFile file)   {
        try {
          userService.loadFile(file.getInputStream());
            return ResponseEntity.status(HttpStatus.OK).body("OK");  
        } catch ( IOException e) {         
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Error");
        }
     
    }
    
    @Operation(summary = "Delete all users", responses = {
    @ApiResponse(description = "Successful Operation", responseCode = "200" ),    
    @ApiResponse(responseCode = "500", description = "Error: Internal Server Error") })
    @DeleteMapping("/allusers/")
    public ResponseEntity<?> deleteAllUsers() {
        try {
            userService.deleteAllUsers();
            return ResponseEntity.status(HttpStatus.OK).body("OK");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Error");
        }
    }

}


