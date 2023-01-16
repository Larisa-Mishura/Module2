package module2.repository;

import module2.model.Invoice;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class InvoiceRepository  {

    private static final List<Invoice> INVOICES = new LinkedList<>();

    private static InvoiceRepository instance;

    private InvoiceRepository(){

    }

    public static InvoiceRepository getInstance(){
        if (instance == null){
            instance = new InvoiceRepository();
        }
        return instance;
    }

    public void save(final Invoice invoice) {
        INVOICES.add(invoice);
    }

    public List<Invoice> getAll() {
        return INVOICES;
    }

    /*public Optional<Invoice> getByCustomer(final Customer customer) {
        return Optional.of(INVOICES.get(index));
    }     //TODO  Delete, getBy*/
}
