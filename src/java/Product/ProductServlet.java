package Product;

import DataIO.ProductIO;
import DataModel.Cart;
import DataModel.LineItem;
import DataModel.Product;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * ProductServlet handles product listing and adding items to cart.
 * @author ASUS
 */
public class ProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ArrayList<Product> products = ProductIO.getInitList(); 
        
        if (products == null || products.isEmpty()) {
            request.setAttribute("error", "No products available.");
        } else {
            request.setAttribute("products", products);
        }
           
        getServletContext().getRequestDispatcher("/product.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        String actiontype = request.getParameter("actiontype");
        System.out.println("AddCart Request: productid=" + request.getParameter("productid"));
        
        if (actiontype == null || actiontype.isEmpty()) {
            doGet(request, response);
            return;
        }

        if ("AddCart".equalsIgnoreCase(actiontype)) {
            String productid = request.getParameter("productid");
            Product p = ProductIO.get(productid);

            if (p != null) {
                HttpSession session = request.getSession();
                Cart cart = (Cart) session.getAttribute("CurrentCart");
                if (cart == null) cart = new Cart();

                LineItem item = new LineItem();
                item.setProduct(p);
                item.setQuantity(1);
                cart.addItem(item);
                session.setAttribute("CurrentCart", cart);

                response.sendRedirect(request.getContextPath() + "/cart");
            } else {
                request.setAttribute("message", "Error: Product not found.");
                getServletContext().getRequestDispatcher("/product.jsp").forward(request, response);
            }
        }
        
        else if ("Delete".equalsIgnoreCase(actiontype)) {
            String productid = request.getParameter("productid");
            try {
                int id = Integer.parseInt(productid);
                boolean deleted = ProductIO.delete(id);
                if (deleted) {
                    request.setAttribute("message", "Product deleted successfully.");
                } else {
                    request.setAttribute("message", "Error: Product not found or could not be deleted.");
                }
            } catch (NumberFormatException e) {
                request.setAttribute("message", "Invalid product ID.");
            }

            ArrayList<Product> products = ProductIO.getInitList();
            request.setAttribute("products", products);
            getServletContext().getRequestDispatcher("/product.jsp").forward(request, response);
            return;
        }  else {
            doGet(request, response);
        }

    }

    @Override
    public String getServletInfo() {
        return "Handles product listing and Add-to-Cart operations";
    }
}
