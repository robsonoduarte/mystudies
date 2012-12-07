<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Kanban</title>
	<link rel="stylesheet" type="text/css" href="resources/css/style.css">
	<style type="text/css">
	
	</style>
</head>
<body>

	<div class="header">
		<a>MYSTUDIES (alfa)</a>
	</div>


	
	<div class="kanban">
	
		<form>
			<input type="hidden" name="kanbanFase" id="kanbanFase">			
			<input type="hidden" name="storyID" id="storyID">			
			<input type="hidden" name="action" value="UPDATESTATUSSTORY">			
		</form>
		
	
		<c:forEach var="entry" items="${map}">
		
			<div style="border: 1px solid blue; float: left; width: 10%;    height: 150px;">									
				<c:out value="${entry.key.title}"></c:out>				
			</div>
			
			<div style="border: 1px solid black; float: left; width: 30%; height: 150px;" class="droppable" id="TODO">
				<c:forEach var="story" items="${entry.value}">
					<c:if test="${story.status == 'TODO'}">
						<div class="story draggable" id="${story.id}">
							<c:out value="${story.title}"></c:out>
							<c:out value="${story.points}"/>  &ensp;points
						</div>
					</c:if>
				</c:forEach>				
			</div>
			
			<div style="border: 1px solid black; float: left;  width: 30%; height: 150px;" class="droppable"  id="DOING">
				<c:forEach var="story" items="${entry.value}">
					<c:if test="${story.status == 'DOING'}">
						<div class="story draggable" id="${story.id}">
							<c:out value="${story.title}"></c:out>
							<c:out value="${story.points}"/> &ensp;points
						</div>
					</c:if>
				</c:forEach>				
			</div>
			
			<div style="border: 1px solid black; margin-left: 70.4%; height: 150px;" class="droppable" id="DONE">
				<c:forEach var="story" items="${entry.value}">
					<c:if test="${story.status == 'DONE'}">
						<div class="story draggable"  id="${story.id}">
							<c:out value="${story.title}"></c:out>
							<c:out value="${story.points}"/> &ensp;points
						</div>
					</c:if>
				</c:forEach>
			</div>


					
		</c:forEach>
	</div>	

	<script type="text/javascript" src="resources/js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="resources/js/general.js"></script>
	<script type="text/javascript" src="resources/js/jquery-ui-1.8.18.custom.min.js"></script>
	<script type="text/javascript" src="resources/js/kanban.js"></script>
	
</body>
</html>