package module2.repository;

import module2.model.Invoice;
import module2.model.product.Item;

import java.util.LinkedList;
import java.util.List;

public class InvoiceRepository  {

    private static final List<Invoice<Item>> INVOICES = new LinkedList<>();

    private static InvoiceRepository instance;

    private InvoiceRepository(){

    }

    public static InvoiceRepository getInstance(){
        if (instance == null){
            instance = new InvoiceRepository();
        }
        return instance;
    }

    public void save(final Invoice<Item> invoice) {
        INVOICES.add(invoice);
    }

    public List<Invoice<Item>> getAllInvoices() {
        return INVOICES;
    }
}
