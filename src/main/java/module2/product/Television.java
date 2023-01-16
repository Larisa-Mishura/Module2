package module2.product;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
public class Television extends Product{
    private final int diagonal;
    private final Country country;

    public Television (String series, ScreenType screenType, int price, int diagonal, Country country) {
        super(ProductType.TELEVISION, series, screenType, price);
        this.diagonal = diagonal;
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Television television = (Television) o;
        return getPrice() == television.getPrice() &&
                getType() == television.getType() &&
                getDiagonal() == television.getDiagonal() &&
                getCountry() == television.getCountry() &&
                Objects.equals(getSeries(), television.getSeries()) &&
                getScreenType() == television.getScreenType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType(), getSeries(), getScreenType(), getPrice(), getDiagonal(), getCountry());
    }
}