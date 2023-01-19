package module2.action;

public class CountByTypeAction implements Action{
    @Override
    public void execute() {
        if(INVOICE_REPOSITORY.getAllInvoices().size() == 0){
            System.out.println("There isn't a single invoice!\n Create invoice!");
            return;
        }
        System.out.println("Total amount for every type of items: \n"+ SHOP_SERVICE.countByType(INVOICE_REPOSITORY.getAllInvoices()));
    }
}
