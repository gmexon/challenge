/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
            return new User(idName, number, Status.OK + "", "dont touch");
        }
        if (phoneNumberMatch(number, "\\d{9}$")) {
            return new User(idName, "27" + number, Status.FIXED + "" + "", "add prefix 27");
        }
        return new User(idName, number, Status.WRONG + "", "bad number");

    }

    boolean phoneNumberMatch(String number, String pattern) {
        return Pattern.compile(pattern).matcher(number).matches();
    }

}

