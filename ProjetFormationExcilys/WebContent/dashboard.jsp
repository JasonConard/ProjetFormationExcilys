<jsp:include page="include/header.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<section id="main">
	<h1 id="homeTitle">${nbComputer} Computers found</h1>
	<div id="actions">
		<form action="" method="GET">
			<input type="search" id="searchbox" name="search" value="${search}"
				placeholder="Search name"> <input type="submit"
				id="searchsubmit" value="Filter by name" class="btn btn-primary">
		</form>
		<a class="btn btn-success" id="add" href="AddComputer">Add
			Computer</a>
	</div>
	<br />
	<table class="computers table table-striped table-bordered">
		<thead>
			<tr>
				<!-- Variable declarations for passing labels as parameters -->
				<!-- Table header for Computer Name -->
				<c:if test="${order=='name' && dir=='DESC'}">
					<th>Computer Name
						<a href="DashBoard?page=1&search=${search}&order=name&dir=ASC"><span class="glyphicon glyphicon-arrow-up"></span></a>
					</th>
				</c:if>
				<c:if test="${!(order=='name' && dir=='DESC')}">
					<th>Computer Name
						<a href="DashBoard?page=1&search=${search}&order=name&dir=DESC"><span class="glyphicon glyphicon-arrow-down"></span></a>
					</th>
				</c:if>
				<c:if test="${order=='introduced' && dir=='DESC'}">
					<th>Introduced Date
						<a href="DashBoard?page=1&search=${search}&order=introduced&dir=ASC"><span class="glyphicon glyphicon-arrow-up"></span></a>
					</th>
				</c:if>
				<c:if test="${!(order=='introduced' && dir=='DESC')}">
					<th>Introduced Date
						<a href="DashBoard?page=1&search=${search}&order=introduced&dir=DESC"><span class="glyphicon glyphicon-arrow-down"></span></a>
					</th>
				</c:if>
				<c:if test="${order=='discontinued' && dir=='DESC'}">
					<th>Discontinued Date
						<a href="DashBoard?page=1&search=${search}&order=discontinued&dir=ASC"><span class="glyphicon glyphicon-arrow-up"></span></a>
					</th>
				</c:if>
				<c:if test="${!(order=='discontinued' && dir=='DESC')}">
					<th>Discontinued Date
						<a href="DashBoard?page=1&search=${search}&order=discontinued&dir=DESC"><span class="glyphicon glyphicon-arrow-down"></span></a>
					</th>
				</c:if>
				<c:if test="${order=='company' && dir=='DESC'}">
					<th>Company
						<a href="DashBoard?page=1&search=${search}&order=company&dir=ASC"><span class="glyphicon glyphicon-arrow-up"></span></a>
					</th>
				</c:if>
				<c:if test="${!(order=='company' && dir=='DESC')}">
					<th>Company
						<a href="DashBoard?page=1&search=${search}&order=company&dir=DESC"><span class="glyphicon glyphicon-arrow-down"></span></a>
					</th>
				</c:if>
				<th>Actions</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="entry" items="${requestScope['allComputer']}" begin="${indLineMin}" end="${indLineMax}">
				<tr>
					<td>${entry.name}</td>
					<td>${entry.introduced}</td>
					<td>${entry.discontinued}</td>
					<td>${entry.company.name}</td>
					<td>
						<a href="EditComputer?computerId=${entry.id}"><span class="glyphicon glyphicon-pencil"></span></a> 
						<a href="DashBoard?computerId=${entry.id}&delete=delete&page=${lastPage}&search=${search}&order=${order}&dir=${dir}">
							<span class="glyphicon glyphicon-trash"></span>
						</a>
					</td>

				</tr>
			</c:forEach>
			<tr>
				<td colspan="5">
				<c:if test="${lastPage!=-1}">
					<a href="DashBoard?page=${lastPage}&search=${search}&order=${order}&dir=${dir}">
						<span class="glyphicon glyphicon-backward"></span>
					</a>
				</c:if>
				Page(${idPage}/${nbPage}) 
				<c:if test="${nextPage!=-1}">
					<a href="DashBoard?page=${nextPage}&search=${search}&order=${order}&dir=${dir}">
						<span class="glyphicon glyphicon-forward"></span>
					</a>
				</c:if>
				
				Page 
				<form action="" method="GET">
					<input type="number" id="searchbox" name="page" value="${idPage}" placeholder="n�"/> / ${nbPage}
					<input type="submit" id="pagesubmit" value="Go to" class="btn btn-primary"/>
					<input type="hidden" id="hiddenSearch" name="search" value="${search}"/>
					<input type="hidden" id="hiddenOrder" name="order" value="${order}"/>
					<input type="hidden" id="hiddenDir" name="dir" value="${dir}"/>
				</form>
				
				
				</td>
			</tr>
		</tbody>
	</table>
</section>

<jsp:include page="include/footer.jsp" />
