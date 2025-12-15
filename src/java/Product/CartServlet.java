package Product;

import DataIO.ProductIO;
import DataModel.*;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * CartServlet handles cart operations: add, remove, update, and display cart contents.
 * @author shofa
 */
public class CartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/cart.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("CurrentCart");

        if (cart == null) {
            cart = new Cart();
            session.setAttribute("CurrentCart", cart);
        }

        if ("add".equalsIgnoreCase(action)) {
            int productId = Integer.parseInt(request.getParameter("productId"));
            Product product = findProductById(productId);

            if (product != null) {
                LineItem newItem = new LineItem();
                newItem.setProduct(product);
                newItem.setQuantity(1);
                cart.addItem(newItem);
            }

        } else if ("remove".equalsIgnoreCase(action)) {
            String productCode = request.getParameter("productCode");
            cart.removeItem(productCode);

        } else if ("update".equalsIgnoreCase(action)) {
            String productCode = request.getParameter("productCode");
            String quantityParam = request.getParameter("quantity_" + productCode);

            if (quantityParam != null) {
                try {
                    int quantity = Integer.parseInt(quantityParam);
                    if (quantity > 0) {
                        cart.updateItemQuantity(productCode, quantity);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid quantity for product " + productCode);
                }
            }
        }

        session.setAttribute("CurrentCart", cart);
        getServletContext().getRequestDispatcher("/cart.jsp").forward(request, response);
    }


    private Product findProductById(int productId) {
        ArrayList<Product> products = ProductIO.getInitList();
        for (Product p : products) {
            if (p.getId() == productId) return p;
        }
        return null;
    }

    @Override
    public String getServletInfo() {
        return "Handles Shopping Cart actions";
    }
}
