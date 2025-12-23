<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, DataModel.Product" %>
<%@ include file="/includes/header.jsp" %>

<div class="container mt-4">
    <h2>Products</h2>

    <c:if test="${not empty message}">
        <div class="alert alert-info alert-dismissible fade in" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
            ${message}
        </div>
    </c:if>
    
    <% 
        List<Product> products = (List<Product>) request.getAttribute("products");
        String message = (String) request.getAttribute("message");
        if (message != null) {
    %>
        <div class="alert alert-info alert-dismissible fade in" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
            <%= message %>
        </div>
    <% } %>
    
    <div class="d-flex justify-content-start gap-2 mb-4">
        <a href="<%= request.getContextPath() %>/products?actiontype=new"
            class="btn btn-success ">
            Add Product
         </a>

        <a href="<%= request.getContextPath() %>/cart"
            class="btn btn-primary ml-2">
            View Cart
         </a>     
    </div>
            
    <table class="table table-bordered table-striped mt-3">
        <thead class="table-default">
            <tr>
                <th>#</th>
                <th>Code</th>
                <th>Description</th>
                <th>Price</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <%
                if (products != null && !products.isEmpty()) {
                    int index = 1;
                    for (Product p : products) {
            %>
                <tr>
                    <td><%= index++ %></td>
                    <td><%= p.getCode() %></td>
                    <td><%= p.getDescription() %></td>
                    <td><%= p.getPriceCurrencyFormat() %></td>
                    <td>
                        <a href="<%= request.getContextPath() %>/products?actiontype=edit&productid=<%= p.getId() %>"
                           class="btn btn-warning btn-sm">
                            Edit
                        </a>

                        <form method="post"
                            action="<%= request.getContextPath() %>/products"
                            style="display:inline;">

                          <input type="hidden" name="productid" value="<%= p.getId() %>">

                          <button type="submit"
                                  name="actiontype"
                                  value="Delete"
                                  class="btn btn-danger btn-sm"
                                  onclick="return confirm('Delete this product?')">
                              Delete
                          </button>

                          <button type="submit"
                                  name="actiontype"
                                  value="AddCart"
                                  class="btn btn-primary btn-sm">
                              Add to Cart
                          </button>
                      </form>
                    </td>

                </tr>
            <%
                    }
                } else {
            %>
                <tr>
                    <td colspan="5" class="text-center text-muted">No products available.</td>
                </tr>
            <%
                }
            %>
        </tbody>
    </table>
</div>

<%@ include file="/includes/footer.jsp" %>
