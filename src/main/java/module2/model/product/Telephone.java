package module2.model.product;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Telephone extends Item {

    private final String model;

    public Telephone(String series, ScreenType screenType, int price, String model) {
        super(ItemType.TELEPHONE, series, screenType, price);
        this.model = model;
    }

    @Override
    public String toString() {
        return "Telephone {" +
                "type=" + type +
                ", price=" + price +
                ", series='" + series + '\'' +
                ", screenType=" + screenType +
                ", model='" + model + '\'' +
                '}';
    }
}
