package module2.service;

import module2.model.Invoice;
import module2.model.product.Product;

import java.util.Comparator;

public class InvoiceComparator implements Comparator<Invoice> {
    @Override
    public int compare(Invoice o1, Invoice o2) {
        return Integer.compare(invoiceSum(o1), invoiceSum(o2));
    }

    private int invoiceSum(Invoice<Product> invoice){
        int sum = 0;
        for (Product product : invoice.getProductList()){
            sum += product.getPrice();
        }
        return sum;
    }
}
