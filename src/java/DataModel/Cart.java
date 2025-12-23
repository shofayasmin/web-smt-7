package DataModel;

import java.io.Serializable;
import java.util.ArrayList;

public class Cart implements Serializable {

    private ArrayList<LineItem> items;

    public Cart() {
        items = new ArrayList<LineItem>();
    }

    public ArrayList<LineItem> getItems() {
        return items;
    }

    public int getCount() {
        return items.size();
    }

    // Add item to the cart
    public void addItem(LineItem item) {
        String code = item.getProduct().getCode();
        int quantity = item.getQuantity();

        for (int i = 0; i < items.size(); i++) {
            LineItem lineItem = items.get(i);
            if (lineItem.getProduct().getCode().equals(code)) {
                int newQty = lineItem.getQuantity() + quantity;
                lineItem.setQuantity(newQty);
                return;  
            }
        }

        items.add(item);
    }

    // Remove item from the cart
    public void removeItem(String productCode) {
        for (int i = 0; i < items.size(); i++) {
            LineItem lineItem = items.get(i);
            if (lineItem.getProduct().getCode().equals(productCode)) {
                items.remove(i);
                return;
            }
        }
    }

    // Update item quantity in the cart
    public void updateItemQuantity(String productCode, int quantity) {
        for (int i = 0; i < items.size(); i++) {
            LineItem lineItem = items.get(i);
            if (lineItem.getProduct().getCode().equals(productCode)) {
                lineItem.setQuantity(quantity);
                return;
            }
        }
    }
}
