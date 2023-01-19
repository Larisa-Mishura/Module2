package module2.util;

import lombok.SneakyThrows;
import module2.action.Actions;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserInput {
    public static final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));

    @SneakyThrows
    public static int menu(final String[] names) {
        int userChoice = -1;
        do {
            System.out.println("Write what you want to do");
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

    @SneakyThrows
    public static int getInt(final String option){
        String line;
        do{
            System.out.println(option);
            line = READER.readLine();

        } while (!StringUtils.isNumeric(line));
        return Integer.parseInt(line);
    }
}

