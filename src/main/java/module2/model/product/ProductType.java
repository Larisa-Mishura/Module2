package module2.model.product;


public enum ProductType {
    TELEPHONE ("Telephone"),
    TV ("TV");

    private final String name;

    ProductType(final String name){
        this.name = name;
    }
}
