<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <title>jsp转义html代码</title>
</head>
<body>
<c:set var="html">
    <h1>标题</h1>
</c:set>
未转义前html变量:${html}<br/>
转义后html变量:${fn:escapeXml(html)}

<c:set var="js">
    <h1 onclick="alert('标题')">标题</h1>
</c:set>
未转义前js变量:${js}<br/>
转义后js变量:${fn:escapeXml(js)}
<script type="text/javascript">
    function js() {
        //浏览器解析出错
        var js = "${js}";
        var js_escape = "${fn:escapeXml(js)}";
    }
</script>
</body>
</html>