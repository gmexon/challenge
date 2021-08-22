package com.challege.mobile.service;

import com.challege.mobile.model.User;
import com.challege.mobile.dto.StatusStatisticsDTO;
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
    List < StatusStatisticsDTO > getStatusCount();
  
}
