package module2.repository;

import module2.model.product.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {

    private static final List<Product> PRODUCTS = new ArrayList<>();

    private static ProductRepository instance;

    private ProductRepository(){

    }

    public static ProductRepository getInstance(){
        if (instance == null){
            instance = new ProductRepository();
        }
        return instance;
    }

    public void save(final Product product) {
        PRODUCTS.add(product);
    }

    public List<Product> getAllProducts() {
        return PRODUCTS;
    }

    public Product getByIndex(final int index) {
        return PRODUCTS.get(index);
    }
}
