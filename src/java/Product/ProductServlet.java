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
        
        HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("currentUser") == null) {
                response.sendRedirect(request.getContextPath() + "/login.jsp");
                return;
            }
            System.out.println("SESSION CHECK: " + session);
            System.out.println("currentUser: " + session.getAttribute("currentUser"));
            

        ArrayList<Product> products = ProductIO.getInitList(); 
        
        if (products == null || products.isEmpty()) {
            request.setAttribute("error", "No products available.");
        } else {
            request.setAttribute("products", products);
        }
        
        String actiontype = request.getParameter("actiontype");
        
        if ("new".equalsIgnoreCase(actiontype)) {
            getServletContext()
                .getRequestDispatcher("/product_new.jsp")
                .forward(request, response);
            return;
        }
        
        if ("edit".equalsIgnoreCase(actiontype)) {
            String productid = request.getParameter("productid");

            if (productid != null) {
                Product p = ProductIO.get(productid);

                if (p != null) {
                    request.setAttribute("product", p);
                    getServletContext()
                        .getRequestDispatcher("/product_edit.jsp")
                        .forward(request, response);
                    return;
                }
            }

            request.getSession().setAttribute("message", "Invalid product selected.");
            response.sendRedirect(request.getContextPath() + "/products");
            return;
        }

        
        getServletContext().getRequestDispatcher("/product.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("currentUser") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }
        System.out.println("SESSION CHECK: " + session);
        System.out.println("currentUser: " + session.getAttribute("currentUser"));

        String actiontype = request.getParameter("actiontype");
        String productid = request.getParameter("productid");
        
        if (actiontype == null || actiontype.isEmpty()) {
            doGet(request, response);
            return;
        }

        if ("AddCart".equalsIgnoreCase(actiontype)) {
            System.out.println("AddCart Request: productid=" + request.getParameter("productid"));
    
            Product p = ProductIO.get(productid);

            if (p != null) {
                Cart cart = (Cart) session.getAttribute("CurrentCart");
                if (cart == null) {
                    cart = new Cart();
                }

                LineItem item = new LineItem();
                item.setProduct(p);
                item.setQuantity(1);
                cart.addItem(item);
                
                session.setAttribute("CurrentCart", cart);
                response.sendRedirect(request.getContextPath() + "/cart");
                return;
                
            } else {
                request.setAttribute("message", "Error: Product not found.");
            }
        }
        
        else if ("Delete".equalsIgnoreCase(actiontype)) {

            try {
                int id = Integer.parseInt(productid);
                boolean deleted = ProductIO.delete(id);

                if (deleted) {
                    request.getSession().setAttribute("message",
                            "Product deleted successfully.");
                } else {
                    request.getSession().setAttribute("message",
                            "Error: Product not found or could not be deleted.");
                }

            } catch (NumberFormatException e) {
                request.getSession().setAttribute("message", "Invalid product ID.");
            }

            response.sendRedirect(request.getContextPath() + "/products");
            return;
        } 
        
        else if ("Update".equalsIgnoreCase(actiontype)) {
            try {
                Product p = new Product();
                p.setId(Integer.parseInt(request.getParameter("productid")));
                p.setCode(request.getParameter("code"));
                p.setDescription(request.getParameter("description"));
                p.setPrice(new java.math.BigDecimal(request.getParameter("price")));
                
                boolean created =  ProductIO.update(p);   // SQL UPDATE
                if (created) {
                    request.getSession().setAttribute("message",
                            "Product updated successfully.");
                } else {
                    request.getSession().setAttribute("message",
                            "Failed to update product.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
                response.sendRedirect(request.getContextPath() + "/products");
                return;
        }

        else if ("Create".equalsIgnoreCase(actiontype)) {
            try {
                Product p = new Product();
                p.setCode(request.getParameter("code"));
                p.setDescription(request.getParameter("description"));
                p.setPrice(new java.math.BigDecimal(request.getParameter("price")));

                boolean created = ProductIO.add(p);

                if (created) {
                    request.getSession().setAttribute("message",
                            "New product added successfully.");
                } else {
                    request.getSession().setAttribute("message",
                            "Failed to add product.");
                }

            } catch (Exception e) {
                e.printStackTrace();
                request.getSession().setAttribute("message",
                        "Invalid product data.");
            }

            response.sendRedirect(request.getContextPath() + "/products");
            return;
        }

        // DEFAULT
        doGet(request, response);

    }

    @Override
    public String getServletInfo() {
        return "Handles product listing and Add-to-Cart operations";
    }
}
