package module2.repository;

import module2.product.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public List<Product> getAll() {
        return PRODUCTS;
    }

    public Optional<Product> getByIndex(final int index) {
        return Optional.of(PRODUCTS.get(index));
    }     //TODO  Delete, getBy
}
