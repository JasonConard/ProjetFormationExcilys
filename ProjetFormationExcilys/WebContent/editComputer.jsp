<jsp:include page="include/header.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<section id="main">

	<h1>Edit Computer</h1>
	<form action="EditComputer" class="form-inline" method="POST">
		<input type="hidden" name="idComputer" value="${computer.id}"/>
		<fieldset>
			<div class="clearfix">
				<label for="name">Computer name:</label>
				<div class="input">
					<input type="text" class="form-control text-form" name="name" width="20" value="${computer.name}"/>
					<span class="help-inline">Required</span>
				</div>
			</div>
			<br/>
			<div class="clearfix">
				<label for="introduced">Introduced date:</label>
				<div class="input">
					<input type="date" class="form-control date-form" name="introducedDate" pattern="YYYY-MM-dd" value="${computer.introduced}"/>
					<span class="help-inline">YYYY-MM-DD</span>
				</div>
			</div>
			<br/>
			<div class="clearfix">
				<label for="discontinued">Discontinued date:</label>
				<div class="input">
					<input type="date" class="form-control date-form" name="discontinuedDate" pattern="YYYY-MM-dd" value="${computer.discontinued}"/>
					<span class="help-inline">YYYY-MM-DD</span>
				</div>
			</div>
			<br/>
			<div class="clearfix">
				<label for="company">Company Name:</label>
				<div class="input">
					<select name="company" class="form-control select-form">
						<option value="0">--</option>
						<c:forEach var="entry" items="${allCompany}">
							<c:if test="${computer.company.id == entry.id}">
								<option value="${entry.id}" selected="selected">${entry.name}</option>
							</c:if>
							<c:if test="${computer.company.id != entry.id}">
								<option value="${entry.id}">${entry.name}</option>
							</c:if>
						</c:forEach>
					</select>
				</div>
			</div>
			<br/>
		</fieldset>
		<div class="actions">
			<input type="submit" value="Edit" class="btn btn-primary">
			or <a href="DashBoard" class="btn">Cancel</a>
		</div>
	</form>
	
	<br/>
	<c:if test="${message != null}">
		<p class="bg-success">${message} <br/> Do you want to return into the main <a href="DashBoard">page</a>?</p>
	</c:if>
	<c:if test="${error != null}">
		<p class="bg-danger">${error} <br/> Do you want to return into the main <a href="DashBoard">page</a>?</p>
	</c:if>
</section>

<jsp:include page="include/footer.jsp" />