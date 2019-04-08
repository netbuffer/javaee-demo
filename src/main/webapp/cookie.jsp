<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>cookie</title>
</head>
<body>
<div id="cookie"></div>
<script>
    document.querySelector("#cookie").innerHTML = document.cookie;
</script>
</body>
</html>
