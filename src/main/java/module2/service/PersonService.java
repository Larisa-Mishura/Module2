package module2.service;

import module2.model.Customer;

import java.util.Random;

public class PersonService {

    private final Random random = new Random();
    private static final String charsWithNumbers = "abcdefghijklmnopqrstuvwxyzZ1234567890";
    private static final String chars = "abcdefghijklmnopqrstuvwxyz";

    public Customer randomCustomer(){
        return new Customer(randomEmail(), randomAge());
    }

    private String randomString(String chars, int length) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(chars.length());
            char ch = chars.charAt(number);
            builder.append(ch);
        }
        return builder.toString();
    }

    private String randomEmail() {
        String email = randomString(charsWithNumbers, 5 + random.nextInt(7)) +
                "@" + randomString(chars, 1 + random.nextInt(4)) + "." + randomString(chars,2);
        return email;
    }

    private int randomAge() {
        final int randomAge = 12 + random.nextInt( 50);
        return randomAge;
    }
}
