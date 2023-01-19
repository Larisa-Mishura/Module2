package module2.service;

import module2.model.Customer;
import module2.model.Invoice;
import module2.model.InvoiceType;
import module2.model.product.Product;
import module2.model.product.ProductType;
import module2.repository.InvoiceRepository;
import module2.repository.ProductRepository;

import java.util.*;
import java.util.function.Predicate;
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
        int countOfProducts = productRepository.getAllProducts().size();
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
                .peek(product -> System.out.println(product.toString()))
                .forEach(product -> saveProduct(product));
    }

    public Map<ProductType, Integer> countForType(){
        Map<ProductType, Integer> map = invoiceRepository.getAllInvoices().stream()
                .map(invoice -> invoice.getProductList())
                .flatMap(List<Product>::stream)
                .collect(Collectors.toMap(product -> product.getType(), product -> 1, (v1, v2) -> v1 + 1));
        return map;
    }

    public Customer customerOfMinInvoice(){//Суму найменшого чека та інформацію про покупця
        Customer customer = invoiceRepository.getAllInvoices().stream()
                .min(invoiceComparator)
                .get().getCustomer();
        return customer;
    }

    public int totalAmount(){//Суму всіх покупок
        int sum = invoiceRepository.getAllInvoices().stream()
                .map(invoice -> invoice.getProductList())
                .flatMap(List<Product>::stream)
                .map(product -> product.getPrice())
                .reduce(0, (subtotal, element) -> subtotal + element);
        return sum;
    }

    public int retailCount(){//Кількість чеків з категорією retail
        int count = (int) invoiceRepository.getAllInvoices().stream()
                .map(invoice -> invoice.getInvoiceType())
                .filter(type -> type == InvoiceType.RETAIL)
                .count();
        return count;
    }

    //allMatch(Predicate predicate) - повертає true, якщо всі елементи стріму задовольняють умову;

    public ArrayList<Invoice>  invoiceWithOneType(){//Чеки, які містять тільки один тип товару
        Predicate<Invoice<Product>> typePredicate = invoice -> {
            ProductType type = invoice.getProductList().get(0).getType();
            boolean result = invoice.getProductList().stream().allMatch(product -> product.getType()==type);
            return result;
        };

        ArrayList<Invoice> list = invoiceRepository.getAllInvoices().stream()
                .filter(typePredicate)
                .collect(Collectors.toCollection(ArrayList::new));
        return list;
    }

    public ArrayList<Invoice> threeFirst(){//Перші три чеки зроблені покупцями
        ArrayList<Invoice> list = invoiceRepository.getAllInvoices().stream()
                .limit(3)
                .collect(Collectors.toCollection(ArrayList::new));
                return list;
    }

    public ArrayList<Invoice> youngestCustomers(){//Інформацію по чеках куплених користувачем молодше 18 років, при цьому змінити тип чека на low_age
        ArrayList<Invoice> list = invoiceRepository.getAllInvoices().stream()
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
        invoiceRepository.getAllInvoices().stream()
                .sorted(new Comparator<Invoice>() {
                    @Override
                    public int compare(Invoice o1, Invoice o2) {
                        return 0;
                    }
                });
    }



    public List<Product> getAllproducts() {
        return productRepository.getAllProducts();
    }

    public void printAllProducts(){
        getAllproducts().stream()
        .forEach(product -> System.out.println(product.toString()));
    }

    public List<Invoice<Product>> getAllinvoices() {
        return invoiceRepository.getAllInvoices();
    }

    public void printAllinvoices(){
        getAllinvoices().stream()
                .forEach(product -> System.out.println(product.toString()));
    }


}
