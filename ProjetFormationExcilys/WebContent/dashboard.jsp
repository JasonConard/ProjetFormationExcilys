<jsp:include page="include/header.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<section id="main">
	<h1 id="homeTitle">${nbComputer} Computers found</h1>
	<div id="actions">
		<form action="" method="GET">
			<input type="search" id="searchbox" name="search"
				value="" placeholder="Search name">
			<input type="submit" id="searchsubmit"
				value="Filter by name"
				class="btn btn-primary">
		</form>
		<a class="btn btn-success" id="add" href="AddComputer">Add Computer</a>
	</div>
	<br/>
		<table class="computers table table-striped table-bordered">
			<thead>
				<tr>
					<!-- Variable declarations for passing labels as parameters -->
					<!-- Table header for Computer Name -->
					<th>Computer Name</th>
					<th>Introduced Date</th>
					<!-- Table header for Discontinued Date -->
					<th>Discontinued Date</th>
					<!-- Table header for Company -->
					<th>Company</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="entry" items="${allComputer}">
					<tr>
						<td>${entry.name}</td>
						<td>${entry.introduced}</td>
						<td>${entry.discontinued}</td>
						<td>${entry.company.name}</td>
						<td>
							<a href="EditComputer?computerId=${entry.id}"><span class="glyphicon glyphicon-pencil"></span></a>
							<a href="DashBoard?computerId=${entry.id}&delete=delete"><span class="glyphicon glyphicon-trash"></span></a>
						</td>
						
					</tr>
				</c:forEach>
			</tbody>
		</table>
</section>

<jsp:include page="include/footer.jsp" />
