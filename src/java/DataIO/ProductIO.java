package DataIO;

import DataModel.Product;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProductIO {

    // READ ALL 
    public static ArrayList<Product> getInitList() {
        ArrayList<Product> list = new ArrayList<>();
        DBUtil db = new DBUtil();

        try {
            String sql = "SELECT ProductID, ProductCode, ProductDescription, ProductPrice FROM `product`";
            ResultSet rs = db.QueryData(sql);

            while (rs != null && rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("ProductID"));
                p.setCode(rs.getString("ProductCode"));
                p.setDescription(rs.getString("ProductDescription"));
                p.setPrice(rs.getBigDecimal("ProductPrice"));
                list.add(p);
            }
            db.Close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // READ ONE
    public static Product get(String productid) {
        DBUtil db = new DBUtil();

        try {
            int id = Integer.parseInt(productid);
            String sql = "SELECT * FROM `product` WHERE ProductID=" + id;
            ResultSet rs = db.QueryData(sql);

            if (rs != null && rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("ProductID"));
                p.setCode(rs.getString("ProductCode"));
                p.setDescription(rs.getString("ProductDescription"));
                p.setPrice(rs.getBigDecimal("ProductPrice"));
                db.Close();
                return p;
            }
            db.Close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // CREATE
    public static boolean add(Product p) {
        DBUtil db = new DBUtil();
        try {
            String sql =
                "INSERT INTO `product` (ProductCode, ProductDescription, ProductPrice) VALUES ('" +
                p.getCode() + "', '" +
                p.getDescription() + "', " +
                p.getPrice().toPlainString() + ")";
            int result = db.QueryUpdate(sql);
            db.Close();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // DELETE
    public static boolean delete(int id) {
        DBUtil db = new DBUtil();
        try {
            String sql = "DELETE FROM `product` WHERE ProductID=" + id;
            int result = db.QueryUpdate(sql);
            db.Close();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    //UPDATE
    public static boolean update(Product p) {
        DBUtil db = new DBUtil();
        String sql =
            "UPDATE `product` SET " +
            "ProductCode='" + p.getCode() + "', " +
            "ProductDescription='" + p.getDescription() + "', " +
            "ProductPrice=" + p.getPrice() +
            " WHERE ProductID=" + p.getId();

        int result = db.QueryUpdate(sql);
        return result > 0;
    }

}
