package module2.model.product;

public enum Country {

    CHINA ("China"),
    DENMARK ("Denmark"),
    GERMANY ("Germany"),
    HUNGARY ("Hungary"),
    JAPAN ("Japan"),
    MALAYSIA ("Malaysia"),
    POLAND ("Poland"),
    SLOVAKIA ("Slovakia"),
    SOUTH_KOREA ("South Korea"),
    TURKEY ("Turkey"),
    UZBEKISTAN ("Uzbekistan"),
    VIETNAM ("Vietnam");

    private final String name;

    Country(final String name){
        this.name = name;
    }

}
