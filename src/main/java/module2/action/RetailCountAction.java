package module2.action;

public class RetailCountAction implements Action{
    @Override
    public void execute() {
        if(INVOICE_REPOSITORY.getAllInvoices().size() == 0){
            System.out.println("There isn't a single invoice!\n Create invoice!");
            return;
        }
        System.out.println("The count of retail invoices - "+
                SHOP_SERVICE.retailCount(INVOICE_REPOSITORY.getAllInvoices()));
    }
}
