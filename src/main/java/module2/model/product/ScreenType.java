package module2.model.product;

public enum ScreenType {
    AMOLED ("AMOLED"),
    DYNAMIC_AMOLED ("Dynamic AMOLED"),
    IPS ("IPS"),
    LCD ("LCD"),
    LED ("LED"),
    LTPS ("LTPS"),
    MicroLED ("MicroLED"),
    NEO_QLED ("NEO QLED"),
    OLED ("OLED"),
    PLS ("PLS"),
    QLED ("QLED"),
    QNED ("QNED"),
    SUPER_AMOLED ("Super AMOLED"),
    TFT ("TFT"),
    TN ("TN"),
    VA ("VA"),
    OTHERS ("Others");

    private final String name;

    ScreenType(final String name){
        this.name = name;
    }
}
