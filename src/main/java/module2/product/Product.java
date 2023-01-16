package module2.product;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public abstract class Product {
    protected final ProductType type;
    protected final String series;
    protected final ScreenType screenType;
    protected int price;

    public Product(ProductType type, String series, ScreenType screenType, int price) {
        this.type = type;
        this.series = series;
        this.screenType = screenType;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if ((this instanceof Television) && (o instanceof Television)) {
            return equals((Television) o);
        } else if ((this instanceof Telephone) && (o instanceof Telephone)) {
            return equals((Telephone) o);
        } else {
            return false;
        }
    }
}
