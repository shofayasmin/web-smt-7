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
    
    function confirmAction(message) {
        return confirm(message);
    }
    
</script>

</body>
</html>
