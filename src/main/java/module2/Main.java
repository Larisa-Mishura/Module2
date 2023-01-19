package module2;

import module2.action.Actions;
import module2.util.UserInput;


public class Main {

    public static void main(String[] args){
        final Actions[] values = Actions.values();
        final String[] names = mapActionsToNames(values);

        while (true) {
            final int userChoice = UserInput.menu(names);
            values[userChoice].execute();
        }
    }

        private static String[] mapActionsToNames(final Actions[] values){
        String[] names = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            names[i] = values[i].getName();
        }
        return names;
    }
}
