/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.challege.mobile;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Andrea
 */
public class CheckNumberTest {
    
    CheckNumber tester;
    
    public CheckNumberTest() {
      tester = new CheckNumber();
    }

    @Test
    public void testSomeMethod() {
    
                
                assertEquals(false,tester.phoneNumberMatch("_DELETED_1488176172","^27\\d{9}$"));
                
                assertEquals(true,tester.phoneNumberMatch("27831234567","^27\\d{9}$"));
                
                assertEquals(true,tester.phoneNumberMatch("123567809","\\d{9}$"));
                
    }
    
}
