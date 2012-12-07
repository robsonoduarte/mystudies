<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>

		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Backlog</title>

		<link rel="stylesheet" type="text/css" href="resources/css/style.css">

	</head>
	<body>

		<div class="header">
			<a>MYSTUDIES (alfa) </a>
		</div>
			
			
		<div class="title-content">
			<h3><a href="#">THEMES</a> </h3>
		</div>


		<div class="main-content">
		
			<div id="themes">
				<c:if test="${includeThemesFragment}">
					<jsp:include page="backlog-themes-fragment.jsp"/>
				</c:if>	
			</div>
			
			<div id="addTheme" style="display: none;">
				<form id="formAddTheme">
					<div>
						<p>
							<label>Title: 
								<input name="title" size="100"  maxlength="100" />
							</label>							
						</p>
						<p>
							<label>Priority: 
								<select name="priority">
									<option>HEIGHT</option>
									<option>MEDIUM</option>
									<option>LOW</option>
								</select>
							</label>						
						</p>
						<p>
							<button id="btnAddTheme" type="button">Add</button>
						</p>
					</div>
					<input type="hidden" name="action" value="ADDTHEME"/>
				</form>
			</div>
										
		</div>


		<ul class="menu">
			<li id="addTheme" ><a>Add Theme</a></li>
			<li ><a>Add Comment</a></li>
			<li ><a href="sprints" >Sprints</a></li>
			<li ><a href="kanban" >Kanban</a></li>
			<li ><a href="statistics" >Statistics</a></li>
		</ul>
		
		
		<script type="text/javascript" src="resources/js/jquery-1.7.2.min.js"></script>
		<script type="text/javascript" src="resources/js/menu.js"></script>
		<script type="text/javascript" src="resources/js/general.js"></script>
		<script type="text/javascript" src="resources/js/backlog.js"></script>

	</body>
</html>