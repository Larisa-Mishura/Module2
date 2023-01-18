package module2.model;

import lombok.Getter;
import lombok.Setter;
import module2.repository.InvoiceRepository;

import java.util.*;

@Getter
@Setter
public class Invoice <Product>{
    private static final int LIMIT = 4;
    final private List<Product> productList;
    final private Customer customer;
    private InvoiceType invoiceType;
    private final Date data;  //TODO

    private static final InvoiceRepository INVOICE_REPOSITORY = InvoiceRepository.getInstance();

    public Invoice(Customer customer) {
        this.customer = customer;
        this.productList = new ArrayList<>();
        this.invoiceType = InvoiceType.RETAIL;
        this.data = new Date();
        //TODO Limit
    }

    public void addToInvoice(Product product){
        productList.add(product);
        if(productList.size() > LIMIT){
            this.invoiceType = InvoiceType.WHOLESALES;
        }
    }

    /*public void deleteFromInvoice(Product product){
        productList.remove(product);
        if(productList.size() <= LIMIT){
            this.invoiceType = InvoiceType.RETAIL;
        }
    }*/
/*
    @Override
    public String toString() {
        return "Invoice{" +
                "productList=" + productList +
                ", customer=" + customer +
                ", invoiceType=" + invoiceType +
                ", data=" + data +
                '}';
    }*/

    public String toString() {
        return data +
                "   Invoice - " +
                invoiceType +
                "("  +
                productList.size() +
                " items)" +
                "   productList=" + productList +
                ", customer=" + customer +
                '}';
    }
}
