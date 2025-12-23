package DataModel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.NumberFormat;

/**
 *
 * @author shofa
 */
public class LineItem implements Serializable {

    private Product product;
    private int quantity;

    public LineItem() {}

    public void setProduct(Product p) {
        product = p;
    }

    public Product getProduct() {
        return product;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getTotal() {
        BigDecimal price = product.getPrice();             
        BigDecimal qty = new BigDecimal(quantity);        
        return price.multiply(qty);                          
    }

    public String getTotalCurrencyFormat() {
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        return currency.format(this.getTotal());
    }
}