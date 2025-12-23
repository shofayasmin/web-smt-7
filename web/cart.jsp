<%@ page import="DataModel.LineItem"%>
<%@ page import="DataModel.Cart"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/includes/header.jsp" %>

<div class="container mt-4">
    <h2>Cart</h2>

    <%
        Cart cart = (Cart) request.getSession().getAttribute("CurrentCart");
        if (cart == null || cart.getItems().isEmpty()) {
    %>
        <div class="alert alert-info">Your cart is empty.</div>
    <%
        } else {
    %>
        <table class="table table-bordered table-striped mt-3">
            <thead class="table-dark">
                <tr>
                    <th>#</th>
                    <th>Quantity</th>
                    <th>Description</th>
                    <th>Price</th>
                    <th>Amount</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
            <%
                int itemCount = 1;
                for (LineItem item : cart.getItems()) {
                    String code = item.getProduct().getCode();
            %>
                <tr>
                    <form method="POST" action="<%= request.getContextPath() %>/cart" class="form-inline">
                        <td><%= itemCount++ %></td>
                        <td style="width: 120px;">
                            <input type="number"
                                   name="quantity_<%= code %>"
                                   value="<%= item.getQuantity() %>"
                                   min="1"
                                   class="form-control">
                        </td>
                        <td><%= item.getProduct().getDescription() %></td>
                        <td><%= item.getProduct().getPriceCurrencyFormat() %></td>
                        <td><%= item.getTotalCurrencyFormat() %></td>
                        <td>
                            <input type="hidden" name="productCode" value="<%= code %>">
                            <button type="submit" name="action" value="update" class="btn btn-primary btn-sm"
                                    onclick="return updateQuantityMessage()">Update</button>
                            <button type="submit" name="action" value="remove" class="btn btn-danger btn-sm" 
                                    onclick="return confirmRemove()">Remove</button>
                        </td>
                    </form>
                </tr>
            <%
                }
            %>
            </tbody>
        </table>
    <%
        }
    %>
</div>

<a href="<%= request.getContextPath() %>/products"
   class="btn btn-secondary mb-3">
    Back to Products
</a>

<%@include file="/includes/footer.jsp" %>
