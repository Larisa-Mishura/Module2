package module2.repository;

import module2.model.Invoice;
import module2.model.product.Product;

import java.util.LinkedList;
import java.util.List;

public class InvoiceRepository  {

    private static final List<Invoice<Product>> INVOICES = new LinkedList<>();

    private static InvoiceRepository instance;

    private InvoiceRepository(){

    }

    public static InvoiceRepository getInstance(){
        if (instance == null){
            instance = new InvoiceRepository();
        }
        return instance;
    }

    public void save(final Invoice<Product> invoice) {
        INVOICES.add(invoice);
    }

    public List<Invoice<Product>> getAllInvoices() {
        return INVOICES;
    }
}
