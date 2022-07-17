package module2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class VatServiceTest {
    private VatService vatService;

    @BeforeEach
    void createVatService() {
        vatService = new VatService();
    }

    @Test
    @DisplayName("Should return gross price for default VAT")
    void shouldReturnGrossPriceForDefaultVat() {
        //given
        Product product = getProduct("20.0");

        //when
        BigDecimal result = vatService.getGrossPriceForDefaultVat(product);

        //then
        assertThat(result).isEqualTo(new BigDecimal("24.60"));
    }

    @Test
    @DisplayName("Should return gross price for VAT that is other than default")
    void shouldReturnGrossPriceForVatOtherThanDefault() {
        //given
        Product product = getProduct("10.0");
        BigDecimal vatValue = new BigDecimal("0.08");

        //when
        BigDecimal result = vatService.getGrossPrice(product.getNetPrice(), vatValue);

        //then
        assertThat(result).isEqualTo(new BigDecimal("10.80"));
    }

    @Test
    @DisplayName("Should return gross price equal to net price for zero VAT")
    void shouldReturnGrossPriceForZeroVat() {
        //given
        Product product = getProduct("100.90");
        BigDecimal vatValue = BigDecimal.ZERO;

        //when
        BigDecimal result = vatService.getGrossPrice(product.getNetPrice(), vatValue);

        //then
        assertThat(result).isEqualTo(new BigDecimal("100.90"));
    }

    @Test
    @DisplayName("Should throw exception when VAT is bigger than one")
    void shouldThrowExceptionForVatBiggerThanOne() {
        //given
        Product product = getProduct("30.0");
        BigDecimal vatValue = BigDecimal.TEN;

        //then
        assertThatExceptionOfType(IncorrectVatValueException.class).isThrownBy(() -> vatService.getGrossPrice(product.getNetPrice(), vatValue));
    }

    private Product getProduct(String price) {
        return new Product(UUID.randomUUID(), new BigDecimal(price));
    }
}