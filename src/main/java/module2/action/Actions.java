package module2.action;

public enum Actions {
    CREATE("Create invoice", new CreateAction()),
    SHOW_ALL("Show all products", new ShowAllAction()),
    EXIT("Finish program", new ExitAction());
    //SHOW_TOTAL_AMOUNT("Show total amount", new ShowTotalAmountAction()),*/


    private final String name;
    private final Action action;

    Actions(final String name, final Action action){
        this.name = name;
        this.action = action;
    }

    public void execute() {
        action.execute();
    }
}
