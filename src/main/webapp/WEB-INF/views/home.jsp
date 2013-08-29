<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>ChrisSartori.com</title>
	<script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/js/jquery.js"></script>
	<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/resources/css/sartori.css"></link>
</head>
<body>
<div class="header">The Home of Chris Sartori</div>
<div id="sartoriDiv" class="mainSartoriDiv">
	<img id="sartoriImg" src="${url}"/><br/><p class="description">${description}</p>
	<br/><a class="sartoriButton" href="#sartoriDiv" onclick="getNewSartori()">Next Sartori Image</a>
</div>

</body>
</html>
