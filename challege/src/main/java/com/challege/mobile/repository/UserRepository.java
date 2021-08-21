/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.challege.mobile.repository;

import com.challege.mobile.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


/**
 *
 * @author Andrea
 */
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT t FROM User t WHERE t.status = ?1")
    List <User> findByStatusInUser(String status);
    
    Optional <User> findByIdNameIs(String IdName);
    
        @Query("SELECT new com.challege.mobile.dto.StatusStatisticsDTO(u.status, COUNT(u)) " +
           "FROM " +
           "    User u " +
           "GROUP BY " +
           "    u.status")
    List<StatusStatisticsDTO> findStatusCount();
  
}
