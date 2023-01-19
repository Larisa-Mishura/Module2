package module2.action;

public class TotalAmountAction implements Action{
    @Override
    public void execute() {
        if(INVOICE_REPOSITORY.getAllInvoices().size() == 0){
            System.out.println("There isn't a single invoice!\n Create invoice!");
            return;
        }
        System.out.println("Total amount of sold items: " + SHOP_SERVICE.totalAmount(INVOICE_REPOSITORY.getAllInvoices()));
    }
}
