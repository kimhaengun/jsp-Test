<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="../layout/header.jsp"%>


<div class="container">
	<h2>User Table</h2>
	<c:forEach var="board" items="${users}">
		<div class="card col-md-12 m-2">
			<div class="card-body">
				<h4 class="card-title">${board.username}</h4>
				<h5 class="card-title">${board.email}</h5>

				<c:if test="${sessionScope.principal.id == board.id}">
					<button onclick="deleteById(${board.id})">삭제</button>
				</c:if>

			</div>
		</div>
	</c:forEach>

</div>
<script>
	function deleteById(id){
		$.ajax({
			type:"post",
			url:"/TestProject/user?cmd=delete&id="+id,
		}).done(function(result) {
			console.log(result);
			if (result == "ok") {
				location.href = "index.jsp";
			} else {
				alert("삭제에 실패하였습니다.");
			}
		});
	}

	function sa(){
		alert("od");
	}				
</script>

</body>
</html>
