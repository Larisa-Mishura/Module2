package module2.action;

public class ExitAction implements Action{
    @Override
    public void execute() {
        System.out.println("Finita la comedia! ");
        System.exit(0);
    }
}
