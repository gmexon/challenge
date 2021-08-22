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
                
                assertEquals(false,tester.phoneNumberMatch("1488176172_DELETED_","^\\d*$"));
                
                assertEquals(true,tester.phoneNumberMatch("123567809","^\\d*$"));
                
                assertEquals(true,tester.phoneNumberMatch("1235678","^\\d{1,8}$"));
                
                assertEquals(false,tester.phoneNumberMatch("","^\\d{1,8}$"));
           
                
    }
    
}
