package module2.action;

import static module2.action.Action.SHOP_SERVICE;

public class MinInvoiceAction implements Action{
    @Override
    public void execute() {
        if(INVOICE_REPOSITORY.getAllInvoices().size() == 0){
            System.out.println("There isn't a single invoice!\n Create invoice!");
            return;
        }
        System.out.println("Customer of the least invoice: \n"+
                SHOP_SERVICE.customerOfTheLeastInvoice(INVOICE_REPOSITORY.getAllInvoices()).toString());
    }
}
