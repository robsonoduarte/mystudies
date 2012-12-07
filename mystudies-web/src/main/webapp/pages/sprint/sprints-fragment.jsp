<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<c:forEach items="${sprints}" var="sprint">			
	<div class="item">				
		<div>  
		    <h3 class="item-title">
		    	<a href="#"> Sprint - ${sprint.id} </a>
		    </h3>
		</div>
			
		<div >
			<ul class="item-information">	
				<li>${sprint.sprintStatus}</li>
				<li>${sprint.estimatedPoints} estimated points</li>
				<li>${sprint.donePoints} done points</li>
			</ul>
			<div style="clear: both;"></div>					
		</div>					    
	</div>				
</c:forEach>
