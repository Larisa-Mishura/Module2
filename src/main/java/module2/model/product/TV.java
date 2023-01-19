package module2.model.product;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TV extends Product{
    private final int diagonal;
    private final Country country;

    public TV(String series, ScreenType screenType, int price, int diagonal, Country country) {
        super(ProductType.TV, series, screenType, price);
        this.diagonal = diagonal;
        this.country = country;
    }

    @Override
    public String toString() {
        return "TV {" +
                "type=" + type +
                ", price=" + price +
                ", series='" + series + '\'' +
                ", screenType=" + screenType +
                ", diagonal=" + diagonal +
                ", country=" + country +
                '}';
    }
}