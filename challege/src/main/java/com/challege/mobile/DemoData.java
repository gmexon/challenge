package com.challege.mobile;

import com.challege.mobile.service.UserService;
import com.opencsv.CSVReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

/**
 *
 * @author Andrea
 */

@Component
public class DemoData implements ApplicationRunner {

    @Autowired
    UserService userService;
    @Autowired
    CheckNumber checkNumber;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        CSVReader reader;
        try {
            InputStream in = new ClassPathResource("data/South_African_Mobile_Numbers.csv").getInputStream();
            reader = new CSVReader(new InputStreamReader(in, StandardCharsets.UTF_8));
            String[] nextLine;

            while ((nextLine = reader.readNext()) != null) {
                try {
                    userService.saveUser(checkNumber.check(nextLine[0], nextLine[1]));
                } catch (Exception ex) {
                    Logger.getLogger(DemoData.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(DemoData.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
