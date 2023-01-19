package module2.service;

import module2.model.Customer;
import module2.model.Invoice;
import module2.model.product.Product;
import module2.model.product.ScreenType;
import module2.model.product.Telephone;
import module2.repository.InvoiceRepository;
import module2.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShopServiceTest {

    private ShopService target;
    private InvoiceRepository invoiceRepository;

    private static final List<Invoice<Product>> TEST_INVOICES = new LinkedList<>();

    private static final ArrayList<Product> TEST_PRODUCTS ={
            new Telephone("S-10", ScreenType.OLED, 7000, "Samsung"),
            new Telephone("Galaxy A", ScreenType.PLS, 5500, "Samsung"),
            new Telephone("Galaxy S22", ScreenType.DYNAMIC_AMOLED, 51000, "Samsung"),
            new Telephone("Redmi 10C", ScreenType.IPS, 6300, "Xiaomi"),
            new Telephone("G10", ScreenType.IPS, 4500, "Nokia"),
            new Telephone("Redmi 10", ScreenType.LCD, 86000, "Xiaomi"),

            new TV("Redmi 10", ScreenType.LCD, 86000, "Xiaomi");
    };





    static {
        TEST_INVOICES.add(new Invoice<Product>(new Customer("zzz1@gmail.com", 35)), )
    }

    static {
        TEST_INVOICES.add(new Invoice<Product>(new Customer("zzz1@gmail.com", 35)), )
    }


    @BeforeEach
    void setUp(){
        invoiceRepository = Mockito.mock(InvoiceRepository.class);
        target = ShopService.getInstance();
    }
    
    @Test
    void countForType() {
    }

    @Test
    void customerOfMinInvoice() {
    }

    @Test
    void totalAmount() {
    }

    @Test
    void retailCount() {
    }

    @Test
    void invoiceWithOneType() {
    }

    @Test
    void threeFirst() {
    }

    @Test
    void youngestCustomers() {
    }

    @Test
    void sortInvoices() {
    }
}