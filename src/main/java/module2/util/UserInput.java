package module2.util;

import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserInput {
    public static final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));

    @SneakyThrows           //Todo check
    public static int menu(final String[] names) {
        int userChoice = -1;
        do {
            System.out.println("Write what do you want to do");
            for (int i = 0; i < names.length; i++) {
                System.out.println(i + "  " + names[i]);
            }
            final String line = READER.readLine();
            if (!StringUtils.isNumeric(line)) {
                continue;
            }
            userChoice = Integer.parseInt(line);
        } while (userChoice < 0 || userChoice > names.length);
        System.out.println("User choice: " + userChoice);
        return userChoice;
    }
/*
    public static int getInt(final String option){
        String line;
        do{
            try{  //  замість @SneakyThrows
                System.out.println(option);
                line = READER.readLine();
            } catch (IOException e){
                throw new RuntimeException();
            }
        } while (!StringUtils.isNumeric(line));
        return Integer.parseInt(line);
    }*/
}

