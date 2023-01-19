package module2.service;

import module2.model.Customer;
import module2.model.Invoice;
import module2.model.InvoiceType;
import module2.model.product.Item;
import module2.model.product.ItemType;
import module2.repository.InvoiceRepository;
import module2.repository.ItemRepository;
import org.apache.commons.lang3.builder.CompareToBuilder;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ShopService {

    private final ItemRepository itemRepository = ItemRepository.getInstance();
    private final InvoiceRepository invoiceRepository;

    private final PersonService personService = new PersonService();

    SumInvoiceComparator sumInvoiceComparator = new SumInvoiceComparator();

    ObjectReader reader = new ObjectReader();

    Random random = new Random();

    SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd.MM.yyyy   hh:mm:ss a zzz");

    private static ShopService instance;

    private ShopService(final InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public static ShopService getInstance(){
        if (instance == null){
            instance = new ShopService(InvoiceRepository.getInstance());
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
        int countOfProducts = itemRepository.getAllProducts().size();
        int countOfPositions = 1 + random.nextInt(5);
        for(int i = 0; i < countOfPositions; i++) {
            invoice.addToInvoice(itemRepository.getByIndex(random.nextInt(countOfProducts)));
        }
        System.out.println(formatForDateNow.format(new Date()) + "  " + invoice.toString());
        invoiceRepository.save(invoice);
    }

    public void saveProduct(Item... items) {
        Arrays.stream(items)
                .forEach(product -> itemRepository.save(product));
    }


    public void getProductsFromFile(String filename) {
        List<Item> productsFromFileList = reader.itemFromFile(filename);
        productsFromFileList.stream()
                .peek(product -> System.out.println(product.toString()))
                .forEach(product -> saveProduct(product));
    }

    public Map<ItemType, Integer> countByType(List<Invoice<Item>> list){//Кількість проданих товарів за категоріями (Telephone/Television)
        return  list.stream()
                .map(invoice -> invoice.getProductList())
                .flatMap(List<Item>::stream)
                .collect(Collectors.toMap(product -> product.getType(), product -> 1, (v1, v2) -> v1 + 1));
    }

    public Customer customerOfTheLeastInvoice(List<Invoice<Item>> list){//Суму найменшого чека та інформацію про покупця
        return   list.stream()
                .min(sumInvoiceComparator)
                .get().getCustomer();
    }


    public int totalAmount(List<Invoice<Item>> list){//Суму всіх покупок
        return list.stream()
                .map(invoice -> invoice.getProductList())
                .flatMap(List<Item>::stream)
                .map(product -> product.getPrice())
                .reduce(0, (subtotal, element) -> subtotal + element);
    }

    public int retailCount(List<Invoice<Item>> list){//Кількість чеків з категорією retail
        return  (int) list.stream()
                .map(invoice -> invoice.getInvoiceType())
                .filter(type -> type == InvoiceType.RETAIL)
                .count();
    }

    public ArrayList<Invoice<Item>> invoiceWithOneType(List<Invoice<Item>> list){//Чеки, які містять тільки один тип товару
        Predicate<Invoice<Item>> typePredicate = invoice -> {
            final ItemType type = invoice.getProductList().get(0).getType();
            boolean result = invoice.getProductList().stream().allMatch(product -> product.getType()==type);
            return result;
        };

        return  list.stream()
                .filter(typePredicate)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Invoice> threeFirst(List<Invoice<Item>> list){//Перші три чеки зроблені покупцями
        return list
                .stream()
                .limit(3)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Invoice> youngCustomers(List<Invoice<Item>> list){//Інформацію по чеках куплених користувачем молодше 18 років, при цьому змінити тип чека на low_age
        return list.stream()
                .filter(invoice -> invoice.getCustomer().getAge() < 18)
                .peek(invoice -> invoice.setInvoiceType(InvoiceType.LOW_AGE))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Invoice>  sortInvoices(List<Invoice<Item>> list) {
        Collections.sort(list, (inv1, inv2) -> new CompareToBuilder()
                .append(inv1.getCustomer().getAge(), inv2.getCustomer().getAge())
                .append(inv1.getProductList().size(), inv2.getProductList().size())
                .append(inv1.getProductList().stream().map(p -> p.getPrice()).count(),
                        inv2.getProductList().stream().map(p -> p.getPrice()).count())
                .toComparison());
        return new ArrayList<>(list);
    }

    public List<Item> getAllproducts() {
        return itemRepository.getAllProducts();
    }

    public void printAllProducts(){
        getAllproducts().stream()
        .forEach(product -> System.out.println(product.toString()));
    }

    public List<Invoice<Item>> getAllinvoices() {
        return invoiceRepository.getAllInvoices();
    }

    public void printAllinvoices(){
        getAllinvoices().stream()
                .forEach(product -> System.out.println(product.toString()));
    }
}
