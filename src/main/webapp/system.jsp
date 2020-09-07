<%@ page import="java.net.InetAddress" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="util" uri="/WEB-INF/util.tld" %>
<html>
<head>
    <title>system</title>
</head>
<body>
jsp version:<%= JspFactory.getDefaultFactory().getEngineInfo().getSpecificationVersion() %><br/>
<%=InetAddress.getLocalHost().toString()%><br/>
str:${util:escapeHtml4(str)}
<script type="text/javascript">
    var str = "${util:escapeEcmaScript(str)}";
    console.log("str:%s", str);
</script>
</body>
</html>
