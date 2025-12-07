 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DataIO;

import DataModel.Product;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class ProductIO {
    public static ArrayList<Product> list;

    public static ArrayList<Product> getInitList() {
        try {
            if (list == null) {
                list = new ArrayList<>();
                Product p = new Product();
                p.setId(0);
                p.setCode("xxx1");
                p.setPrice(120.0);
                p.setDescription("desc.");
                list.add(p);

                Product p2 = new Product();
                p2.setId(1);
                p2.setCode("xxx2");
                p2.setPrice(150.0);
                p2.setDescription("desc.");
                list.add(p2);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Product get(String productid) {
        try {
            if (list == null) {
                getInitList(); // ensure list initialized
            }
            int id = Integer.parseInt(productid);
            return getById(id);
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Product getById(int id) {
        if (list == null) return null;
        for (Product p : list) {
            if (p.getId() == id) return p;
        }
        return null;
    }

    public static boolean add(Product p) {
        if (list == null) getInitList();
        if (p.getId() == 0) {
            int nextId = list.size() > 0 ? list.get(list.size()-1).getId() + 1 : 0;
            p.setId(nextId);
        }
        return list.add(p);
    }

    public static boolean delete(int id) {
        Product found = getById(id);
        if (found != null) {
            return list.remove(found);
        }
        return false;
    }
}
