package module2.repository;

import module2.model.product.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemRepository {

    private static final List<Item> ITEMS = new ArrayList<>();

    private static ItemRepository instance;

    private ItemRepository(){

    }

    public static ItemRepository getInstance(){
        if (instance == null){
            instance = new ItemRepository();
        }
        return instance;
    }

    public void save(final Item item) {
        ITEMS.add(item);
    }

    public List<Item> getAllProducts() {
        return ITEMS;
    }

    public Item getByIndex(final int index) {
        return ITEMS.get(index);
    }
}
