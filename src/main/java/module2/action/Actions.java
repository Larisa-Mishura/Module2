package module2.action;

import lombok.Getter;

@Getter
public enum Actions {

    READ_FILE ("Read file and create products", new ReadFileAction()),
    CREATE_INVOICE("Create invoice", new CreateInvoiceAction()),
    TOTAL_COUNT ("Show count for every type of product", new TypeCountAction()),
    SHOW_MIN_INVOICE("Show customer of the min invoice", new MinInvoiceAction()),
    SHOW_ALL_PRODUCTS("Show all products", new ShowAllAction()),
    SHOW_ALL_INVOICES("Show all invoices", new ShowAllInvoicesAction()),
    EXIT("Finish program", new ExitAction());

    private final String name;
    private final Action action;

    Actions(final String name, final Action action){
        this.name = name;
        this.action = action;
    }

    public void execute() {
        action.execute();
    }

    private static String[] mapActionsToNames(final Actions[] values) {
        String[] names = new String[values.length];
        //System.out.println("What do you want to do?");
        for (int i = 0; i < values.length; i++) {
            //System.out.println(i + "  " + values[i].getName());
            names[i] = values[i].getName();
        }
        return names;
    }
}
