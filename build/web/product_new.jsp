<%-- 
    Document   : product_new
    Created on : Dec 17, 2025, 2:17:18 PM
    Author     : shofa
--%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/includes/header.jsp" %>

<div class="container mt-4">
    <h2>New Product</h2>

    <form method="post" action="<%= request.getContextPath() %>/products">
        <input type="hidden" name="actiontype" value="Create">

        <div class="form-group">
            <label>Code</label>
            <input type="text" name="code" class="form-control" required>
        </div>

        <div class="form-group">
            <label>Description</label>
            <input type="text" name="description" class="form-control" required>
        </div>

        <div class="form-group">
            <label>Price</label>
            <input type="text" name="price" class="form-control" required>
        </div>

        <button type="submit" class="btn btn-success">Save</button>
        <a href="<%= request.getContextPath() %>/products"
           class="btn btn-secondary">Cancel</a>
    </form>
</div>

<%@ include file="/includes/footer.jsp" %>

