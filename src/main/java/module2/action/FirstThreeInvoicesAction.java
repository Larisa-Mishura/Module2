package module2.action;

public class FirstThreeInvoicesAction implements Action{
    @Override
    public void execute() {
        if(INVOICE_REPOSITORY.getAllInvoices().size() == 0){
            System.out.println("There isn't a single invoice!\n Create invoice!");
            return;
        }
        System.out.println("The first three invoices :");
        SHOP_SERVICE.threeFirst(INVOICE_REPOSITORY.getAllInvoices())
                .forEach(inv -> System.out.println(inv.toString()));
    }
}
