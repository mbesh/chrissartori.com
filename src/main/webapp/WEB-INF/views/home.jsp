<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>ChrisSartori.com</title>
	<script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/js/jquery.js"></script>
	<script type="text/javascript">
$(document).ready(function() {
	$("#sartoriButton").bind("click", function() {
		$.ajax({
			dataType: "json",
			url: "image",
			type: "GET",
			success: function(data) {
				$("#sartoriImg").attr('src', data.url);
				$("#sartoriDesc").text(data.description);
			}
		});
	})
});
	</script>
	<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/resources/css/sartori.css"></link>
</head>
<body>
<div class="header">The Home of Chris Sartori</div>
<div id="sartoriDiv" class="mainSartoriDiv">
	<img id="sartoriImg" src="${url}"/><br/><p id="sartoriDesc" class="description">${description}</p>
	<br/><a id="sartoriButton" class="sartoriButton" href="#sartoriDiv">Next Sartori Image</a>
</div>

</body>
</html>
