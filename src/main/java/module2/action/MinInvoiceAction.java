package module2.action;

import static module2.action.Action.SHOP_SERVICE;

public class MinInvoiceAction implements Action{
    @Override
    public void execute() {
        System.out.println("Customer of the min invoice: \n"+ SHOP_SERVICE.customerOfMinInvoice());//TODO
    }
}
