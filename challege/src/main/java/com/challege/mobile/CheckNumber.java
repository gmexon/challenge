package com.challege.mobile;

import com.challege.mobile.model.User;
import java.util.regex.Pattern;
import org.springframework.stereotype.Component;

/**
 *
 * @author Andrea
 */

@Component
public class CheckNumber {


    public User check(String idName, String number) {
        
        if (phoneNumberMatch(number, "^27\\d{9}$")) {
            return new User(idName, number, Status.OK + "", "Not modified");
        }
        if (phoneNumberMatch(number, "\\d{9}$")) {
            return new User(idName, "27" + number, Status.FIXED + "" + "", "Add prefix 27");
        }
        if (!phoneNumberMatch(number, "^\\d*$")) {
            return new User(idName, number, Status.WRONG + "" + "", "Not digit");
        }
        
        if (phoneNumberMatch(number, "^\\d{1,8}$")) {
            return new User(idName, number, Status.WRONG + "" + "", "Number of digits less than 9");
        }
        
        
        return new User(idName, number, Status.WRONG + "", "wrong number");

    }

    boolean phoneNumberMatch(String number, String pattern) {
        return Pattern.compile(pattern).matcher(number).matches();
    }

}

