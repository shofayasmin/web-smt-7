<%-- 
    Document   : product_edit
    Created on : Dec 17, 2025, 1:06:51 PM
    Author     : shofa
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="DataModel.Product" %>
<%@ include file="/includes/header.jsp" %>

<%
    Product product = (Product) request.getAttribute("product");
    if (product == null) {
        response.sendRedirect(request.getContextPath() + "/products");
        return;
    }

%>

<div class="container mt-4">
    <h2>Edit Product</h2>

    <form method="post" action="<%= request.getContextPath() %>/products">
        <input type="hidden" name="actiontype" value="Update">
        <input type="hidden" name="productid" value="<%= product.getId() %>">

        <div class="form-group">
            <label>Code</label>
            <input type="text" name="code"
                   class="form-control"
                   value="<%= product.getCode() %>" required>
        </div>

        <div class="form-group">
            <label>Description</label>
            <input type="text" name="description"
                   class="form-control"
                   value="<%= product.getDescription() %>" required>
        </div>

        <div class="form-group">
            <label>Price</label>
            <input type="text" name="price"
                   class="form-control"
                   value="<%= product.getPrice() %>" required>
        </div>

        <button type="submit" class="btn btn-primary">Update</button>
        <a href="<%= request.getContextPath() %>/products"
           class="btn btn-secondary">Cancel</a>
    </form>
</div>

<%@ include file="/includes/footer.jsp" %>

