package module2;

import org.slf4j.Logger;

import java.math.BigDecimal;
import java.math.RoundingMode;


public class VatService {
    private final Logger logger;
    private final VatProvider vatProvider;

    public VatService(VatProvider vatProvider, Logger logger) {
        this.vatProvider = vatProvider;
        this.logger = logger;
    }

    public BigDecimal getGrossPriceForDefaultVat(Product product) {
        logger.info("Calculating gross price (default VAT) for product {}", product);
        return calculateGrossPrice(product.getNetPrice(), vatProvider.getDefaultVat()).setScale(2, RoundingMode.CEILING);
    }

    public BigDecimal getGrossPriceForVatSpecificToType(Product product) {
        logger.info("Calculating gross price (VAT specific to type {}) for product {}", product.getType(), product);
        return calculateGrossPrice(product.getNetPrice(), vatProvider.getVatForType(product.getType()));
    }

    public BigDecimal calculateGrossPrice(BigDecimal netPrice, BigDecimal vatValue) {
        if (vatValue.compareTo(BigDecimal.ONE) == 1) {
            logger.error("Error for VAT value {}", vatValue);
            throw new IncorrectVatValueException("Given VAT value is bigger than 1 (100% product price)");
        }
        logger.debug("VAT value used to calculate gross price {}", vatValue);
        return netPrice.multiply(vatValue.add(BigDecimal.ONE)).setScale(2, RoundingMode.CEILING);
    }
}
