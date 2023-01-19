package module2.model.product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Item {
    protected final ItemType type;
    protected final String series;
    protected final ScreenType screenType;
    protected int price;

    public Item(ItemType type, String series, ScreenType screenType, int price) {
        this.type = type;
        this.series = series;
        this.screenType = screenType;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product  {" +
                "type=" + type +
                ", series='" + series + '\'' +
                ", screenType=" + screenType +
                ", price=" + price +
                '}';
    }
}
