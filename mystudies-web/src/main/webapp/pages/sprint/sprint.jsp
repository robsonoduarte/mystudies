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
			<c:choose>
				<c:when test="${sprint != null}">
					<h3><a href="#">
							Sprint ${sprint.id} - <fmt:formatDate value="${sprint.startDate}" pattern="dd/MM/yyyy"/>  a  <fmt:formatDate value="${sprint.finalDate}" pattern="dd/MM/yyyy"/>  ( ${sprint.points} points )							 
						</a> 
					</h3>						
				</c:when>
				<c:otherwise>
					<h3><a href="#"> haven't sprint in running </a></h3>
				</c:otherwise>
			</c:choose>
		</div>
		
		<div class="main-content">		
			<div id="stories">
				<c:if test="${includeStoriesFragment}">
					<jsp:include page="sprint-stories-fragment.jsp"/>				
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