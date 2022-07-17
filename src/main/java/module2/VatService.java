package module2;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class VatService {
    private BigDecimal vatValue;

    public VatService() {
        this.vatValue = new BigDecimal("0.23");
    }

    public BigDecimal getGrossPriceForDefaultVat(Product product) {

        return getGrossPrice(product.getNetPrice(), vatValue).setScale(2, RoundingMode.CEILING);
    }

    public BigDecimal getGrossPrice(BigDecimal netPrice, BigDecimal vatValue) {
        if (vatValue.compareTo(BigDecimal.ONE) == 1) {
            throw new IncorrectVatValueException("Podana wartość VAT jest wyższa od 1 (100% ceny produktu)");
        }
        return netPrice.multiply(vatValue.add(BigDecimal.ONE)).setScale(2, RoundingMode.CEILING);
    }
}
