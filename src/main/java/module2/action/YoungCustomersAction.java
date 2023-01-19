package module2.action;

public class YoungCustomersAction implements Action{
    @Override
    public void execute() {
        if(INVOICE_REPOSITORY.getAllInvoices().size() == 0){
            System.out.println("There isn't a single invoice!\n Create invoice!");
            return;
        }
        System.out.println("The invoices of customers under 18 years :");
        SHOP_SERVICE.youngCustomers(INVOICE_REPOSITORY.getAllInvoices())
                .forEach(inv -> System.out.println(inv.toString()));
    }
}
