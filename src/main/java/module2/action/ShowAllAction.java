package module2.action;

public class ShowAllAction implements Action{
    @Override
    public void execute() {
        SHOP_SERVICE.printAll(); //TODO
    }
}
