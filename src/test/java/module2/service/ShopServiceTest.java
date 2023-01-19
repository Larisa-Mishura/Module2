package module2.service;

import module2.model.Customer;
import module2.model.Invoice;
import module2.model.product.*;
import module2.repository.InvoiceRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.*;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.junit.jupiter.api.Assertions.*;

class ShopServiceTest {

    private ShopService target;
    private InvoiceRepository invoiceRepository;

    private final LinkedList<Invoice<Item>> TEST_INVOICES = new LinkedList<>();
    private final static Item[] items ={
                (Item) new Telephone("S-10", ScreenType.OLED, 7000, "Samsung"),
                (Item) new Telephone("Galaxy A", ScreenType.PLS, 5000, "Samsung"),
                (Item) new Telephone("Galaxy S22", ScreenType.DYNAMIC_AMOLED, 50000, "Samsung"),
                (Item) new Telephone("Redmi 10C", ScreenType.IPS, 6300, "Xiaomi"),
                (Item) new Telephone("G10", ScreenType.IPS, 4500, "Nokia"),
                (Item) new Telephone("Redmi 10", ScreenType.LCD, 85000, "Xiaomi"),

                (Item) new TV("RTI-14", ScreenType.LED, 2000, 25, Country.CHINA),
                (Item) new TV("LS03", ScreenType.QLED, 18000, 32, Country.HUNGARY),
                (Item) new TV("MiTV", ScreenType.QLED, 21000, 55, Country.CHINA),
                (Item) new TV("KD", ScreenType.VA, 80000, 75, Country.JAPAN),
                (Item) new TV("UQ", ScreenType.LCD, 27000, 55, Country.POLAND),
                (Item) new TV("US", ScreenType.LCD, 24000, 55, Country.POLAND)};

@BeforeEach
    void setUp(){
        invoiceRepository = Mockito.mock(InvoiceRepository.class);
        target = ShopService.getInstance();

        TEST_INVOICES.add(new Invoice(new Customer("Customer1@gmail.com", 40), items[0], items[1]));
        TEST_INVOICES.add(new Invoice(new Customer("Customer2@gmail.com", 30), items[0], items[1], items[2]));
        TEST_INVOICES.add(new Invoice(new Customer("Customer3@gmail.com", 30), items[1], items[2], items[10]));
        TEST_INVOICES.add(new Invoice(new Customer("Customer4@gmail.com", 30), items[10], items[11]));
        TEST_INVOICES.add(new Invoice(new Customer("Customer5@gmail.com", 17), items[6], items[7]));
        TEST_INVOICES.add(new Invoice(new Customer("Customer6@gmail.com", 40), items[7], items[8]));
        TEST_INVOICES.add(new Invoice(new Customer("Customer7@gmail.com", 50), items[0], items[1], items[5], items[7], items[9]));
        TEST_INVOICES.add(new Invoice(new Customer("Customer8@gmail.com", 16), items[0], items[6]));

        Mockito.when(invoiceRepository.getAllInvoices()).thenReturn(TEST_INVOICES);
}
    
    @Test
    void countByTypeTelephoneTest() {
        final int expected = 11;
        int actual = target.countByType(invoiceRepository.getAllInvoices()).get(ItemType.TELEPHONE);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void countByTypeTVTest() {
        final int expected = 10;
        int actual = target.countByType(invoiceRepository.getAllInvoices()).get(ItemType.TV);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void customerOfTheLeastInvoiceTest() {
        final Customer expected = invoiceRepository.getAllInvoices().get(7).getCustomer();
        final Customer actual = target.customerOfTheLeastInvoice(invoiceRepository.getAllInvoices());
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void totalAmountTest() {
        final int expected = 470_000;
        final int actual = target.totalAmount(invoiceRepository.getAllInvoices());
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void retailCountTest() {
        final int expected = 7;
        final int actual = target.retailCount(invoiceRepository.getAllInvoices());
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void invoiceWithOneTypeTest() {
        final int expected = 5;
        final int actual = target.invoiceWithOneType(invoiceRepository.getAllInvoices()).size();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void threeFirstTest() {
        final Invoice[] expected = {(TEST_INVOICES.get(0)), TEST_INVOICES.get(1), TEST_INVOICES.get(2)};
        final ArrayList<Invoice> actual = target.threeFirst(invoiceRepository.getAllInvoices());
        assertArrayEquals(expected, actual.toArray());
    }

    @Test
    void youngCustomersTest() {
        final Invoice[] expected = {(TEST_INVOICES.get(4)), TEST_INVOICES.get(7)};
        final ArrayList<Invoice> actual = target.youngCustomers(invoiceRepository.getAllInvoices());
        assertArrayEquals(expected, actual.toArray());
    }

    @Test
    void sortInvoicesTest() {
        final Invoice[] expected = {
                (TEST_INVOICES.get(7)), TEST_INVOICES.get(4), (TEST_INVOICES.get(3)), (TEST_INVOICES.get(1)),
                TEST_INVOICES.get(2), (TEST_INVOICES.get(0)), (TEST_INVOICES.get(5)), TEST_INVOICES.get(6)};
        final ArrayList<Invoice> actual = target.sortInvoices(invoiceRepository.getAllInvoices());
        assertArrayEquals(expected, actual.toArray());
    }
}
