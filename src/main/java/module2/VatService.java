package module2;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class VatService {
    private VatProvider vatProvider;

    public VatService(VatProvider vatProvider) {
        this.vatProvider = vatProvider;
    }

    public BigDecimal getGrossPriceForDefaultVat(Product product) {

        return calculateGrossPrice(product.getNetPrice(), vatProvider.getDefaultVat()).setScale(2, RoundingMode.CEILING);
    }

    public BigDecimal getGrossPriceForVatSpecificToType(Product product){
        return calculateGrossPrice(product.getNetPrice(), vatProvider.getVatForType(product.getType()));
    }

    public BigDecimal calculateGrossPrice(BigDecimal netPrice, BigDecimal vatValue) {
        if (vatValue.compareTo(BigDecimal.ONE) == 1) {
            throw new IncorrectVatValueException("Podana wartość VAT jest wyższa od 1 (100% ceny produktu)");
        }
        return netPrice.multiply(vatValue.add(BigDecimal.ONE)).setScale(2, RoundingMode.CEILING);
    }
}
