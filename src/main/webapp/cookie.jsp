<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
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
