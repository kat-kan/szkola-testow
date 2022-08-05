package module2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;

import java.math.BigDecimal;
import java.util.UUID;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.*;

class VatServiceTest {
    private VatService vatService;
    private VatProvider vatProvider;
    private Logger logger;
    private Product product;
    private String type;
    private BigDecimal vatValue;

    @BeforeEach
    void createVatService() {
        vatProvider = Mockito.mock(VatProvider.class);
        logger = Mockito.mock(Logger.class);
        vatService = new VatService(vatProvider, logger);
    }

    @Test
    @DisplayName("Should return gross price for default VAT")
    void shouldReturnGrossPriceForDefaultVat() {
        //given
        type = "Firewood";
        product = getProduct("20.0", type);
        vatValue = new BigDecimal("0.23");
        when(vatProvider.getDefaultVat()).thenReturn(vatValue);

        //when
        vatService.getGrossPriceForDefaultVat(product);

        //then
        //assertThat(result).isEqualTo(new BigDecimal("24.60"));
        // mod2.1 assertions removed so tests use only mocks
        verify(vatProvider).getDefaultVat();
        verify(logger).info("Calculating gross price (default VAT) for product {}", product);
        verifyVatProviderDebug(vatValue);
    }


    @Test
    @DisplayName("Should return gross price for VAT that is other than default")
    void shouldReturnGrossPriceForVatOtherThanDefault() {
        //given
        type = "Fertilizer";
        product = getProduct("10.0", type);
        vatValue = new BigDecimal("0.08");
        stubGetVatForType(type, vatValue);

        //when
        vatService.getGrossPriceForVatSpecificToType(product);

        //then
        //assertThat(result).isEqualTo(new BigDecimal("10.80"));
        verify(vatProvider).getVatForType(type);
        verify(logger).info("Calculating gross price (VAT specific to type {}) for product {}", product.getType(), product);
        verifyVatProviderDebug(vatValue);
    }

    @Test
    @DisplayName("Should return gross price equal to net price for zero VAT")
    void shouldReturnGrossPriceForZeroVat() {
        //given
        type = "Bread";
        product = getProduct("100.90", type);
        vatValue = BigDecimal.ZERO;
        stubGetVatForType(type, vatValue);

        //when
        vatService.getGrossPriceForVatSpecificToType(product);

        //then
        //assertThat(result).isEqualTo(new BigDecimal("100.90"));
        verify(vatProvider).getVatForType(type);
        verify(logger).info("Calculating gross price (VAT specific to type {}) for product {}", product.getType(), product);
        verifyVatProviderDebug(vatValue);
    }

    @Test
    @DisplayName("Should throw exception when VAT is bigger than one")
    void shouldThrowExceptionForVatBiggerThanOne() {
        //given
        type = "Toys";
        product = getProduct("30.0", type);
        vatValue = BigDecimal.TEN;
        stubGetVatForType(type, vatValue);

        //when
        catchThrowable(() -> vatService.getGrossPriceForVatSpecificToType(product));

        //then
        //assertThatExceptionOfType(IncorrectVatValueException.class).isThrownBy(() -> vatService.getGrossPriceForVatSpecificToType(product));
        verify(vatProvider).getVatForType(type);
        verify(logger, times(0)).debug("VAT value used to calculate gross price {}", vatValue);
        verify(logger).error("Error for VAT value {}", vatValue);
    }

    private Product getProduct(String price, String type) {
        return new Product(UUID.randomUUID(), new BigDecimal(price), type);
    }

    private void stubGetVatForType(String type, BigDecimal vatValue) {
        when(vatProvider.getVatForType(type)).thenReturn(vatValue);
    }

    private void verifyVatProviderDebug(BigDecimal vatValue) {
        verify(logger).debug("VAT value used to calculate gross price {}", vatValue);
    }
}
