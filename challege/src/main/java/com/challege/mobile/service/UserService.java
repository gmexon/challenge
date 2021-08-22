package com.challege.mobile.service;

import com.challege.mobile.model.User;
import com.challege.mobile.dto.StatusStatisticsDTO;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Andrea
 */

public interface UserService {
    void saveUser(User user);
    User getUserById(long id);
    void deleteAllUsers();
    List < User > getAllUsers();
    User getUserByIdName(String idName);
    List < User > findByStatusInUser(String status);
    List < StatusStatisticsDTO > getStatusCount();
    public void loadFile(MultipartFile file);

    
}
