<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>post-test</title>
</head>
<body>
<form action="${ctx}/post/test" method="post">
    <input name="name" placeholder="name"/>
    <button type="submit">提交</button>
</form>
</body>
</html>
