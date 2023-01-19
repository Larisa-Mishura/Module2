package module2.action;

public class InvoicesWithOneType implements Action{
    @Override
    public void execute() {
        if(INVOICE_REPOSITORY.getAllInvoices().size() == 0){
            System.out.println("There isn't a single invoice!\n Create invoice!");
            return;
        }
        System.out.println("Invoices with one type of items (Telephone or TV) :");
        SHOP_SERVICE.invoiceWithOneType(INVOICE_REPOSITORY.getAllInvoices())
                .forEach(inv -> System.out.println(inv.toString()));
    }
}
