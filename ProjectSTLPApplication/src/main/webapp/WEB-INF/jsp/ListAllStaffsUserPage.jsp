<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>รายชื่อเจ้าหน้าที่ศูนย์ให้ความช่วยเหลือบุคคลไร้รัฐไร้สัญชาติ</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" />
<link href="/css/sticky-footer-navbar.css" rel="stylesheet" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<style>
.home {
	position: relative;
	display: block;
	padding: 12px 15px;
	color: #fff;
	background-color: #080808;
}

.logout {
	position: relative;
	display: block;
	padding: 12px 15px;
	color: red;
	background-color: #080808;
}
</style>


</head>
<body>
	<c:choose>
		<c:when test="${usernamesession == null}">
			<jsp:include page="login.jsp"></jsp:include>
			<br />
		</c:when>
		<c:otherwise>
			<nav class="navbar navbar-inverse">
			<div class="container-fluid">
				<ul class="nav navbar-nav">
					<form action="/liststaff" method="post">
						<input type="hidden" name='username' class='form-control'
							value="${username}" /> <input type='hidden' name='password'
							class='form-control' value="${password}" /> <input type='hidden'
							name='telcenter' class='form-control' value="${telcenter}" />
						<button type="submit" class="home">รายชื่อเจ้าหน้าที่ภายในศูนย์ฯ</button>
					</form>
				</ul>
				<ul class="nav navbar-nav">
					<a href="${pageContext.request.contextPath}/logout"><input
						type="button" name="logout" value="ออกจากระบบ" class="logout"></a>
				</ul>
			</div>
			</nav>
			<div class="container">

				<h3>รายชื่อเจ้าหน้าที่</h3>
				<br>
				<table class="table table-hover">

					<thead>
						<tr>
							<th><b>#ชื่อผู้ใช้งาน</b></th>
							<th><b>ชื่อ</b></th>
							<th><b>ตำแหน่ง</b></th>
							<th><b>เบอร์โทร</b></th>
							<th><b>แก้ไข</b></th>
							<th><b>ลบ</b></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${liststaff}" var="staff">
							<tr>
								<td><c:out value="${staff.username}"></c:out></td>
								<c:set var="nameperson1"
									value="${fn:replace(staff.nameperson, '%2F', '/')}" />
								<c:set var="nameperson2"
									value="${fn:replace(nameperson1, '%2C', ',')}" />
								<c:set var="nameperson3"
									value="${fn:replace(nameperson2, '+', ' ')}" />
								<td><c:out value="${nameperson3}"></c:out></td>
								<c:set var="position1"
									value="${fn:replace(staff.position, '%2F', '/')}" />
								<c:set var="position2"
									value="${fn:replace(position1, '%2C', ',')}" />
								<c:set var="position3"
									value="${fn:replace(position2, '+', ' ')}" />
								<td><c:out value="${position3}"></c:out></td>
								<td><c:out value="${staff.telperson}"></c:out></td>

								<td>
									<form action="/createstaff/editstaff" method="post">
										<input type="hidden" name='username' class='form-control'
											value="${staff.username}" /> <input type='hidden'
											name='telcenter' class='form-control' value="${telcenter}" />
										<input type="hidden" name='usernamepresident'
											class='form-control' value="${username}" /> <input
											type='hidden' name='password' class='form-control'
											value="${password}" />
										<button type="submit" class="btn btn-primary">แก้ไข</button>
									</form>
								</td>
								<td>
									<form action="/removetaff/deletestaff" method="post">
										<input type="hidden" name='username' class='form-control'
											value="${staff.username}" /> <input type='hidden'
											name='telcenter' class='form-control' value="${telcenter}" />
										<input type="hidden" name='usernamepresident'
											class='form-control' value="${username}" /> <input
											type='hidden' name='password' class='form-control'
											value="${password}" /> <input type='hidden'
											name='nameperson' class='form-control'
											value="${staff.nameperson}" />
										<button type="submit" class="btn btn-primary"
											onclick="return confirm('ต้องการลบหรือไม่?')">ลบ</button>
									</form>

								</td>
							</tr>

						</c:forEach>
					</tbody>
				</table>
			</div>
			<footer class="footer">
			<div class="container">
				<form action="/createstaff" method="POST">
					<input type="hidden" name="telcenter" value="${telcenter}">
					<input type="hidden" name="username" value="${username}"> <input
						type='hidden' name='password' class='form-control'
						value="${password}" />
					<button type="submit" class="btn btn-primary">เพิ่มเจ้าหน้าที่ศูนย์ให้ความช่วยเหลือฯ</button>
				</form>
			</div>
			</footer>

		</c:otherwise>
	</c:choose>
</body>
</html>