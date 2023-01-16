package module2;

import module2.action.Action;
import module2.model.Customer;
import module2.service.ShopService;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        ShopService shopService = ShopService.getInstance();
        shopService.getProductsFromFile("product.csv");
        shopService.printAll();

        shopService.createRandomInvoice(new Customer("ss@ss", 15));
    }
}