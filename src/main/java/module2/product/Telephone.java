package module2.product;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
public class Telephone extends Product{

    private final String model;

    public Telephone(String series, ScreenType screenType, int price, String model) {
        super(ProductType.TELEPHONE, series, screenType, price);
        this.model = model;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Telephone telephone = (Telephone) o;
        return getPrice() == telephone.getPrice() &&
                getType() == telephone.getType() &&
                Objects.equals(getSeries(), telephone.getSeries()) &&
                Objects.equals(getModel(), telephone.getModel()) &&
                getScreenType() == telephone.getScreenType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType(), getSeries(), getScreenType(), getPrice(), getModel());
    }
}
