package module2.service;

import module2.model.Customer;
import module2.model.Invoice;
import module2.model.InvoiceType;
import module2.product.Product;
import module2.product.ProductType;
import module2.repository.InvoiceRepository;
import module2.repository.ProductRepository;

import java.util.*;
import java.util.stream.Collectors;

public class ShopService {

    private final ProductRepository productRepository;
    private final InvoiceRepository invoiceRepository;
    private final PersonService personService;

    InvoiceComparator invoiceComparator = new InvoiceComparator();

    ProductReader reader = new ProductReader();
    Random random = new Random();

    private static ShopService instance;

    private ShopService(final ProductRepository productRepository, final InvoiceRepository invoiceRepository) {
        this.productRepository = productRepository;
        this.invoiceRepository = invoiceRepository;
        this.personService = new PersonService();
    }

    public static ShopService getInstance(){
        if (instance == null){
            instance = new ShopService(ProductRepository.getInstance(), InvoiceRepository.getInstance());
        }
        return instance;
    }

    public void createRandomInvoices(int count) {
        for(int i = 0; i < count; i++){
            createRandomInvoice();
        }
    }

    public void createRandomInvoice(){
        Invoice invoice = new Invoice(personService.randomCustomer());
        int countOfProducts = productRepository.getAll().size();
        int countOfPositions = 1 + random.nextInt(5);
        for(int i = 0; i < countOfPositions; i++) {
            invoice.addToInvoice(productRepository.getByIndex(random.nextInt(countOfProducts)));
        }
        System.out.println(invoice.toString());
        invoiceRepository.save(invoice);
    }

    public void saveProduct(Product... products) {
        Arrays.stream(products)
                .forEach(product -> productRepository.save(product));
    }


    public void getProductsFromFile(String filename) {
        List<Product> productsFromFileList = reader.productsFromFile(filename);
        productsFromFileList.stream()
                //.filter(product -> product != null)
                .forEach(product -> saveProduct(product));
    }

    public Map<ProductType, Integer> countForType(){//Кількість проданих товарів за категоріями (Telephone/TV)
        Map<ProductType, Integer> map = invoiceRepository.getAll().stream()
                .map(invoice -> invoice.getProductList())
                .flatMap(List<Product>::stream)
                .collect(Collectors.toMap(product -> product.getType(), product -> 1, (v1, v2) -> v1 + 1));
        return map;
    }

    public Customer customerOfMinInvoice(){//Суму найменшого чека та інформацію про покупця
        Customer customer = invoiceRepository.getAll().stream()
                .min(invoiceComparator)
                .get().getCustomer();
        return customer;
    }

    public void totalAmount(){//Суму всіх покупок
        System.out.println("Продано одиниць товару " +
                invoiceRepository.getAll().stream()
                .map(invoice -> invoice.getProductList())
                .flatMap(List<Product>::stream)
               .map(product -> 1)
                .reduce(0, (subtotal, element) -> subtotal + element) +
                " на суму " +
                invoiceRepository.getAll().stream()
                .map(invoice -> invoice.getProductList())
                .flatMap(List<Product>::stream)
                .map(product -> product.getPrice())
                .reduce(0, (subtotal, element) -> subtotal + element));
    }

    public void retailCount(){//Кількість чеків з категорією retail
        int count = (int) invoiceRepository.getAll().stream()
                .map(invoice -> invoice.getInvoiceType())
                .filter(type -> type == InvoiceType.RETAIL)
                .count();
        System.out.println(count);
    }

    public void invoiceWithOneType(){//Чеки, які містять тільки один тип товару
        /*invoiceRepository.getAll().stream()
                .map(invoice -> invoice.getProductList().stream())
                .filter()*/
    }

    public ArrayList<Invoice> threeFirst(){//Перші три чеки зроблені покупцями
        ArrayList<Invoice> list = invoiceRepository.getAll().stream()
                .limit(3)
                .collect(Collectors.toCollection(ArrayList::new));
                return list;
    }

    public ArrayList<Invoice> youngeCustomers(){//Інформацію по чеках куплених користувачем молодше 18 років, при цьому змінити тип чека на low_age
        ArrayList<Invoice> list = invoiceRepository.getAll().stream()
                .filter(invoice -> invoice.getCustomer().getAge() < 18)
               .peek(invoice -> invoice.setInvoiceType(InvoiceType.LOW_AGE))
                .collect(Collectors.toCollection(ArrayList::new));
        return list;
    }

    //Написати метод, який сортує (будь-яким відомим способом) усі замовлення в
    //такому порядку:
    //○ Спочатку за віком покупця від більшого до меншого
    //○ Далі за кількістю куплених предметів
    //○ Далі за загальною сумою куплених предметів

    public void sortInvoices() {
        /*invoiceRepository.getAll().stream()
                .sorted(Comparator.comparing(Invoice.::getColor))*/
    }






    public List<Product> getAllproducts() {
        return productRepository.getAll();
    }

    public void printAll(){
        getAllproducts().stream()
        .forEach(product -> System.out.println(product.toString()));
    }


}
