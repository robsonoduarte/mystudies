<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>

		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Sprint</title>		
		<link rel="stylesheet" type="text/css" href="resources/css/style.css">

	</head>
	<body>

		<div class="header">
			<a>MYSTUDIES (alfa) </a>
		</div>
			
		<div class="title-content">
			<h3><a href="#"> Sprints </a></h3>
		</div>
		
		
		<div class="main-content">
				
			<div id="stories">
				<c:if test="${includeSprintsFragment}">
					<jsp:include page="sprints-fragment.jsp"/>				
				</c:if>
			</div>			
			
			<div id="newSprint">				
			</div>
						
		</div>
		

		<ul class="menu">
			<li ><a id="newSprint">New Sprint</a></li>
			<li ><a href="backlog">Backlog</a></li>
		</ul>


		<script type="text/javascript" src="resources/js/jquery-1.7.2.min.js"></script>
		<script type="text/javascript" src="resources/js/menu.js"></script>
		<script type="text/javascript" src="resources/js/general.js"></script>
		<script type="text/javascript" src="resources/js/sprint.js"></script>

	</body>
</html>