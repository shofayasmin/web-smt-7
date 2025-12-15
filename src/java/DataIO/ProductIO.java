package DataIO;

import DataModel.Product;
import java.util.ArrayList;

/**
 * ProductIO manages product data for the web application.
 * It currently uses in-memory data but can be extended to read/write from file or database.
 * @author ASUS
 */
public class ProductIO {

    private static ArrayList<Product> list;

    // Ensure product list is initialized only once
    private static synchronized void init() {
        if (list == null) {
            list = new ArrayList<>();

            Product p1 = new Product();
            p1.setId(0);
            p1.setCode("P001");
            p1.setDescription("desc.1");
            p1.setPrice(120.00);
            list.add(p1);

            Product p2 = new Product();
            p2.setId(1);
            p2.setCode("P002");
            p2.setDescription("desc.2");
            p2.setPrice(150.00);
            list.add(p2);

            Product p3 = new Product();
            p3.setId(2);
            p3.setCode("P003");
            p3.setDescription("desc.3");
            p3.setPrice(250.00);
            list.add(p3);

            Product p4 = new Product();
            p4.setId(3);
            p4.setCode("P004");
            p4.setDescription("desc.4");
            p4.setPrice(90.00);
            list.add(p4);

            Product p5 = new Product();
            p5.setId(4);
            p5.setCode("P005");
            p5.setDescription("desc.5");
            p5.setPrice(350.00);
            list.add(p5);
        }
    }

    public static ArrayList<Product> getInitList() {
        try {
            if (list == null) {
                init();
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static Product get(String productid) {
        try {
            if (list == null) init();
            int id = Integer.parseInt(productid);
            return getById(id);
        } catch (NumberFormatException e) {
            System.err.println("Invalid product ID format: " + productid);
            return null;
        }
    }

    public static Product getById(int id) {
        if (list == null) init();
        for (Product p : list) {
            if (p.getId() == id) return p;
        }
        return null;
    }

    public static boolean add(Product p) {
        if (list == null) init();
        int nextId = list.size() > 0 ? list.get(list.size() - 1).getId() + 1 : 0;
        p.setId(nextId);
        return list.add(p);
    }

    public static boolean delete(int id) {
        Product found = getById(id);
        if (found != null) {
            return list.remove(found);
        }
        return false;
    }

    public static void printAll() {
        if (list == null) init();
        System.out.println("=== Product List ===");
        for (Product p : list) {
            System.out.println(p.getId() + " | " + p.getCode() + " | " + p.getDescription() + " | $" + p.getPrice());
        }
    }
}
