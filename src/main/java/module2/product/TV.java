package module2.product;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        TV TV = (TV) o;
        return getPrice() == TV.getPrice() &&
                getType() == TV.getType() &&
                getDiagonal() == TV.getDiagonal() &&
                getCountry() == TV.getCountry() &&
                Objects.equals(getSeries(), TV.getSeries()) &&
                getScreenType() == TV.getScreenType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType(), getSeries(), getScreenType(), getPrice(), getDiagonal(), getCountry());
    }
}