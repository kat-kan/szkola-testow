package module2;

import java.math.BigDecimal;
import java.util.UUID;

public class Product {
    private UUID id;
    private BigDecimal netPrice;
    private String type;

    public Product() {
    }

    public Product(UUID id, BigDecimal netPrice, String type) {
        this.id = id;
        this.netPrice = netPrice;
        this.type = type;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public BigDecimal getNetPrice() {
        return netPrice;
    }

    public void setNetPrice(BigDecimal netPrice) {
        this.netPrice = netPrice;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
