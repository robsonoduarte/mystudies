<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<c:choose>
	<c:when test="${containsSprintInRun}">
	 	a sprint in run....
	</c:when>
	<c:otherwise>
		<form>
			<div>
				<p>
					<label>Start Date: 
						<input name="startDate" size="10"  maxlength="10" />
					</label>							
				</p>
				<p>
					<label>Final Date: 
						<input name="finalDate" size="10"  maxlength="10" />
					</label>							
				</p>
				<p>
					<button id="btnCreateSprint" type="button">Create</button>
				</p>
			</div>
			<input type="hidden" name="action" value="CREATESPRINT"/>
		</form>
	</c:otherwise>
</c:choose>