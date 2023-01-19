package module2.action;

public class ShowAllInvoicesAction implements Action{
    @Override
    public void execute() {
        SHOP_SERVICE.printAllinvoices();
    }
}