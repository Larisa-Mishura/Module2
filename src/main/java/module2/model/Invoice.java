package module2.model;

import lombok.Getter;
import lombok.Setter;
import module2.repository.InvoiceRepository;

import java.util.*;

@Getter
@Setter
public class Invoice <Product>{
    private static final int LIMIT = 4;
    private List<Product> productList;
    final private Customer customer;
    private InvoiceType invoiceType;

    public Invoice(Customer customer) {
        this.customer = customer;
        this.productList = new ArrayList<>();
        this.invoiceType = InvoiceType.RETAIL;
    }

    public Invoice(Customer customer, Product... products) {
        this.customer = customer;
        this.productList = new ArrayList<>();
        productList = Arrays.asList(products);
        this.invoiceType = products.length > 4 ? InvoiceType.WHOLESALES : InvoiceType.RETAIL;
    }

    public void addToInvoice(Product product){
        productList.add(product);
        if(productList.size() > LIMIT){
            this.invoiceType = InvoiceType.WHOLESALES;
        }
    }

    public String toString() {
        return "   Invoice - " +
                invoiceType +
                "("  +
                productList.size() +
                " items)" +
                "   productList=" + productList +
                ", customer=" + customer +
                '}';
    }
}
