package module2.repository;

import lombok.Getter;
import module2.model.Customer;
import module2.model.Invoice;

import java.util.LinkedList;
import java.util.List;

public class CustomerRepository {

    private static final List<Customer> CUSTOMERS = new LinkedList<>();

    private static CustomerRepository instance;

    private CustomerRepository(){

    }

    public static CustomerRepository getInstance(){
        if (instance == null){
            instance = new CustomerRepository();
        }
        return instance;
    }

    public void saveCustomer(final Customer customer) {
        CUSTOMERS.add(customer);
    }

    public List<Customer> getAllCustomers() {
        return CUSTOMERS;
    }
}
