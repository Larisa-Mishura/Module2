package module2.action;

import module2.util.UserInput;

import java.util.Optional;

public class CreateAction implements Action{

    private static final int DEFAULT_COUNT = 10;

    @Override
    public void execute() {
        /*String[] menu = {"Input your value", "Default value"};
        final int userChoice = UserInput.menu(menu);

        int count;
        if (userChoice == 0) {
            count = Optional.of(UserInput.getInt("Write amount of cars"))
                    .filter(c -> c >= 1)
                    .orElse(DEFAULT_COUNT);
        } else {
            count = DEFAULT_COUNT;
        }

        CAR_SERVICE.create(count);
        System.out.printf("Created %d cars%n", count);*/
    }
}
