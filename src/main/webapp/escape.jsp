<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<html>
<head>
    <title>jsp??html/js??</title>
</head>
<body>
<c:set var="html">
    <h1>??</h1>
</c:set>
??? html ??:${html}<br/>
??? html ??:${fn:escapeXml(html)}

<c:set var="js">
    <h1 onclick="alert('??')">??</h1>
</c:set>
??? js ??:${js}<br/>
??? js ??:${fn:escapeXml(js)}
<script type="text/javascript">
    function demo() {
        // ???????
        var raw = "${js}";
        var escaped = "${fn:escapeXml(js)}";
        console.log(raw, escaped);
    }
</script>
</body>
</html>
