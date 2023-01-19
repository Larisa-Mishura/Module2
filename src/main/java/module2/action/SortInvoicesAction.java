package module2.action;

public class SortInvoicesAction implements Action{
    @Override
    public void execute() {
        if(INVOICE_REPOSITORY.getAllInvoices().size() == 0){
            System.out.println("There isn't a single invoice!\n Create invoice!");
            return;
        }
        System.out.println("Invoices are sorted by age of customers, then by count of items, then by total sum: ");
        SHOP_SERVICE.sortInvoices(INVOICE_REPOSITORY.getAllInvoices())
                .forEach(inv -> System.out.println(inv.toString()));
    }
}
