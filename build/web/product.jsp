<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, DataModel.Product" %>
<%@ include file="/includes/header.jsp" %>

<div class="container mt-4">
    <h2>Products</h2>

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

    <table class="table table-bordered table-striped mt-3">
        <thead class="table-dark">
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
                        <!-- Delete -->
                        <form method="post" action="<%= request.getContextPath() %>/products" style="display:inline;">
                            <input type="hidden" name="actiontype" value="Delete">
                            <input type="hidden" name="productid" value="<%= p.getId() %>">
                            <button type="submit" class="btn btn-danger btn-sm" onclick="deleteProduct('<%= p.getCode() %>')">
                                Delete
                            </button>
                        </form>

                        <!-- Buy -->
                        <form style="display:inline;">
                            <button type="submit" class="btn btn-success btn-sm" 
                                    onclick="buyProduct('<%= p.getCode() %>')">Buy</button>
                        </form>

                        <!-- Add to Cart -->
                        <form method="post" action="<%= request.getContextPath() %>/products" style="display:inline;">
                            <input type="hidden" name="actiontype" value="AddCart">
                            <input type="hidden" name="productid" value="<%= p.getId() %>">
                            <button type="submit" class="btn btn-primary btn-sm">Add to Cart</button>
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
