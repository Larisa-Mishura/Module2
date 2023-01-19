package module2.action;

import lombok.SneakyThrows;
import module2.service.ShopService;
import module2.util.UserInput;

import java.sql.SQLOutput;

public class ReadFileAction implements Action{
    @SneakyThrows
    @Override
    public void execute() {

        String fileName = "product.csv";

        String[] menu = {"Input name of file", "Default file - " + fileName};
        final int userChoice = UserInput.menu(menu);

        int count;
        if (userChoice == 0) {
            System.out.println("Input name of file");
            fileName = UserInput.READER.readLine();
        }
        ShopService shopService = ShopService.getInstance();
        shopService.getProductsFromFile(fileName);
    }
}
