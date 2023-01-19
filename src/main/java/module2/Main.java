package module2;

import module2.action.Actions;
import module2.model.Customer;
import module2.service.ShopService;
import module2.util.UserInput;

import java.io.IOException;

public class Main {

    public static void main(String[] args){
        ShopService shopService = ShopService.getInstance();

       /* System.out.println(shopService.countForType());
        System.out.println(shopService.totalAmount());
        System.out.println(shopService.retailCount());
        System.out.println(shopService.threeFirst());
        System.out.println(shopService.youngestCustomers());
        System.out.println(shopService.customerOfMinInvoice().toString());*/

        final Actions[] values = Actions.values();
        final String[] names = mapActionsToNames(values);

        while (true) {
            final int userChoice = UserInput.menu(names);
            values[userChoice].execute();
        }
    }

        private static String[] mapActionsToNames(final Actions[] values){
        String[] names = new String[values.length];
        //System.out.println("What do you want to do?");
        for (int i = 0; i < values.length; i++) {
            //System.out.println(i + "  " + values[i].getName());
            names[i] = values[i].getName();
        }
        return names;
    }
}
