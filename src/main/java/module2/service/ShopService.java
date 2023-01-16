package module2.service;

import module2.model.Customer;
import module2.model.Invoice;
import module2.product.Product;
import module2.repository.ProductRepository;

import java.util.*;

public class ShopService {

    private final ProductRepository productRepository;

    ProductReader reader = new ProductReader();
    Random random = new Random();

    private static ShopService instance;

    private ShopService(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public static ShopService getInstance(){
        if (instance == null){
            instance = new ShopService(ProductRepository.getInstance());
        }
        return instance;
    }

    public void createRandomInvoice(Customer customer){
        Invoice invoice = new Invoice(customer);
        int countOfProducts = productRepository.getAll().size();
        int countOfPositions = 1 + random.nextInt(5);
        for(int i = 0; i < countOfPositions; i++) {
            invoice.addToInvoice(productRepository.getByIndex(random.nextInt(countOfProducts)));
        }
        System.out.println(new Date() + "  " + invoice.getProductList().toString() + "  " + customer.toString());

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

    public List<Product> getAllproducts() {
        return productRepository.getAll();
    }

    public void printAll(){
        getAllproducts().stream()
        .forEach(product -> System.out.println(product.toString()));
    }


}
