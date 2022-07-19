package module2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.when;

class VatServiceTest {
    private VatService vatService;
    private VatProvider vatProvider;

    @BeforeEach
    void createVatService() {
        vatProvider = Mockito.mock(VatProvider.class);
        vatService = new VatService(vatProvider);
    }

    @Test
    @DisplayName("Should return gross price for default VAT")
    void shouldReturnGrossPriceForDefaultVat() {
        //given
        String type = "Firewood";
        Product product = getProduct("20.0", type);
        when(vatProvider.getDefaultVat()).thenReturn(new BigDecimal("0.23"));

        //when
        BigDecimal result = vatService.getGrossPriceForDefaultVat(product);

        //then
        assertThat(result).isEqualTo(new BigDecimal("24.60"));
    }

    @Test
    @DisplayName("Should return gross price for VAT that is other than default")
    void shouldReturnGrossPriceForVatOtherThanDefault() {
        //given
        String type = "Fertilizer";
        Product product = getProduct("10.0", type);
        BigDecimal vatValue = new BigDecimal("0.08");
        stubGetVatForType(type, vatValue);

        //when
        BigDecimal result = vatService.getGrossPriceForVatSpecificToType(product);

        //then
        assertThat(result).isEqualTo(new BigDecimal("10.80"));
    }

    @Test
    @DisplayName("Should return gross price equal to net price for zero VAT")
    void shouldReturnGrossPriceForZeroVat() {
        //given
        String type = "Bread";
        Product product = getProduct("100.90", type);
        BigDecimal vatValue = BigDecimal.ZERO;
        stubGetVatForType(type, vatValue);

        //when
        BigDecimal result = vatService.getGrossPriceForVatSpecificToType(product);

        //then
        assertThat(result).isEqualTo(new BigDecimal("100.90"));
    }

    @Test
    @DisplayName("Should throw exception when VAT is bigger than one")
    void shouldThrowExceptionForVatBiggerThanOne() {
        //given
        String type = "Toys";
        Product product = getProduct("30.0", type);
        BigDecimal vatValue = BigDecimal.TEN;
        stubGetVatForType(type, vatValue);

        //then
        assertThatExceptionOfType(IncorrectVatValueException.class).isThrownBy(() -> vatService.getGrossPriceForVatSpecificToType(product));
    }

    private Product getProduct(String price, String type) {
        return new Product(UUID.randomUUID(), new BigDecimal(price), type);
    }

    private void stubGetVatForType(String type, BigDecimal vatValue) {
        when(vatProvider.getVatForType(type)).thenReturn(vatValue);
    }
}