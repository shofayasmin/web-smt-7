<%-- 
    Document   : product
    Created on : Nov 24, 2025, 11:30:35 AM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@include file="/includes/header.html" %>

<div class="container mt-4">
    <h2>Product Catalog</h2>

    <table class="table table-bordered table-striped mt-3">
        <thead class="table-dark">
            <tr>
                <th>ID</th>
                <th>Code</th>
                <th>Description</th>
                <th>Price</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <%
                java.util.ArrayList<DataModel.Product> products =
                    (java.util.ArrayList<DataModel.Product>) request.getAttribute("products");

                if (products != null) {
                    for (DataModel.Product p : products) {
            %>
            <tr data-id="<%= p.getId() %>">
                <td><%= p.getId() %></td>
                <td><%= p.getCode() %></td>
                <td><%= p.getDescription() %></td>
                <td><%= p.getPriceCurrencyFormat() %></td>
                <td>
                    <button class="btn btn-danger btn-sm" onclick="deleteProduct(<%= p.getId() %>)">Delete</button>
                    <button class="btn btn-primary btn-sm" onclick="buyProduct(<%= p.getId() %>)">Buy</button>
                </td>
            </tr>
            <%
                    }
                }
            %>
        </tbody>
    </table>
</div>
<script>
    function deleteProduct(id) {
        if (!confirm('Are you sure you want to delete product #' + id + '?')) return;

        const row = document.querySelector('tr[data-id="' + id + '"]');
        if (row) row.remove();

        showAlert('Product #' + id + ' removed (client-side).', 'warning');

        // NOTE: If you later implement server delete, send fetch()/XMLHttpRequest here.
        // Example:
        // fetch('/yourapp/products/delete?id=' + id, { method: 'POST' }).then(...)
    }

    function buyProduct(id) {
        showAlert('Buy clicked for product #' + id + '.', 'success');
        console.log('Buy clicked for product', id);

        // optional: open a modal or redirect to checkout
        // window.location.href = '/yourapp/checkout?product=' + id;
    }

    function showAlert(message, type) {
        // type: success, info, warning, danger
        var alertDiv = document.createElement('div');
        alertDiv.className = 'alert alert-' + type + ' alert-dismissible';
        alertDiv.setAttribute('role', 'alert');

        // Bootstrap 3 close button
        var closeBtn = document.createElement('button');
        closeBtn.type = 'button';
        closeBtn.className = 'close';
        closeBtn.setAttribute('data-dismiss', 'alert');
        closeBtn.innerHTML = '&times;';
        alertDiv.appendChild(closeBtn);

        // message
        alertDiv.appendChild(document.createTextNode(" " + message));

        // Add alert to .container
        var container = document.querySelector('.container');
        if (container) {
            container.prepend(alertDiv);
        } else {
            document.body.insertBefore(alertDiv, document.body.firstChild);
        }

        // Auto dismiss after 4 seconds
        setTimeout(function() {
            if (alertDiv && alertDiv.parentNode) {
                alertDiv.parentNode.removeChild(alertDiv);
            }
        }, 4000);
    }

</script>
<%@include file="/includes/footer.html" %>
