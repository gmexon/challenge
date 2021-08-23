package com.challege.mobile.service;

import com.challege.mobile.model.User;
import com.challege.mobile.dto.StatusStatisticsDTO;
import java.io.InputStream;
import java.util.List;

/**
 *
 * @author Andrea
 */

public interface UserService {
    
    void deleteAllUsers();
    List < User > getAllUsers();
    User getUserByIdName(String idName);
    List < User > findByStatusInUser(String status);
    List < StatusStatisticsDTO > getStatusCount();
    void loadFile(InputStream file);

}

