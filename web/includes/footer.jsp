</div> 

<footer class="footer">
  <div class="container text-center">
    <p class="text-muted">&copy; <%= java.time.Year.now() %> WebApplication</p>
  </div>
</footer>

<script src="${pageContext.request.contextPath}/assets/js/vendor/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/dist/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/ie10-viewport-bug-workaround.js"></script>

<script>
  function deleteProduct(code) {
    console.log("Delete clicked for product: " + code);
    alert("Are you sure you want to delete product " + code + "?");
  }

  function buyProduct(code) {
    console.log("Buy clicked for product: " + code);
    alert("You have purchased product " + code + " successfully!");
  }

  function updateCartMessage() {
    alert("Cart updated successfully!");
  }
  
  function confirmRemove() {
    return confirm("Are you sure you want to remove this item?");
  }
  
  function updateQuantityMessage() {
    alert("Quantity updated successfully!");
  }
  

</script>

</body>
</html>
