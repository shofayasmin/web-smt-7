package DataModel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.NumberFormat;

public class Product implements Serializable {

    private int id;                     
    private String code;                
    private String description;         
    private BigDecimal price;        

    public Product() {
        id = 0;
        code = "";
        description = "";
        price = BigDecimal.ZERO;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getPriceCurrencyFormat() {
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        return currency.format(price);
    }
}
