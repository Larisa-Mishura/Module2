package module2.model.product;


public enum ItemType {

    TELEPHONE ("Telephone"),
    TV ("TV");

    private final String name;

    ItemType(final String name){
        this.name = name;
    }
}
