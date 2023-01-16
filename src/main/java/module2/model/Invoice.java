package module2.model;

import lombok.Getter;
import lombok.ToString;
import module2.repository.InvoiceRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@ToString
@Getter
public class Invoice <Product>{
    private static final int LIMIT = 5;
    final private List<Product> productList;
    final private Customer customer;
    private InvoiceType invoiceType;

    private static final InvoiceRepository INVOICE_REPOSITORY = InvoiceRepository.getInstance();

    public Invoice(Customer customer) {
        this.customer = customer;
        this.productList = new ArrayList<>();
        this.invoiceType = InvoiceType.RETAIL;
        //TODO Limit
    }

    public void addToInvoice(Product product){
        productList.add(product);
        if(productList.size() > LIMIT){
            this.invoiceType = InvoiceType.WHOLESALES;
        }
    }

    public void deleteFromInvoice(Product product){
        productList.remove(product);
        if(productList.size() <= LIMIT){
            this.invoiceType = InvoiceType.RETAIL;
        }
    }


}
