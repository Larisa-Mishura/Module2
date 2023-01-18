package module2.model;

import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@Getter
public class Customer {
    protected final String id;
    protected String email;
    protected int age;

    public Customer(String email, int age) {
        this.id = UUID.randomUUID().toString();
        this.email = email;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "email='" + email + '\'' +
                ", age=" + age +
                '}';
    }
}
