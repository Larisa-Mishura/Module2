package module2.action;

public class TypeCountAction implements Action{
    @Override
    public void execute() {
        System.out.println("Total amount for every type: \n"+ SHOP_SERVICE.countForType());
    }
}
