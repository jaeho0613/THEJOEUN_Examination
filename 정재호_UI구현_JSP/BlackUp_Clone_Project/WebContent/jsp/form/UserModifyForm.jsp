<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>회원정보수정</title>
	<!-- Web 설정 -->
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width initial-scale=1">
	<!-- JS -->
	<script src="/BlackUp_Clone_Project/js/bootstrap.min.js"></script>
	<script src="/BlackUp_Clone_Project/js/jquery-3.5.1.min.js"></script>
	<script src="/BlackUp_Clone_Project/js/popper.min.js"></script>

	<!-- CSS -->
	<link rel="stylesheet" href="/BlackUp_Clone_Project/css/bootstrap.min.css">
	<link rel="stylesheet" href="/BlackUp_Clone_Project/css/custom.css">
</head>

<body>
	<!-- 헤더 영역  -->
	<jsp:include page="../../Header.jsp"></jsp:include>

	<div class="container" style="padding-top: 100px">
		<div class="col-lg-4"></div>
		<div class="col-lg-4" style="margin: auto">
			<div class="jumbtron" style="padding-top: 20px">

				<form method="post" action="/BlackUp_Clone_Project/modify">
					<h3 style="text-align: center;">회원정보 수정</h3>
					<div class="form-group" style="text-align: center;">
						<div>
						<br> 아이디<input style="text-align: center;" type="text" class="form-control" placeholder="아이디" name="userID"
							value="${ user.userID }" readonly /><br>
					</div>
					<div>
						비밀 번호<input style="text-align: center;" type="text" class="form-control" placeholder="비밀번호" name="userPassword" maxlength="20"
							value="${ user.userPassword }" /><br>
					</div>
					<div>
						이름<input style="text-align: center;" type="text" class="form-control" placeholder="이름" name="userName" value="${ user.userName }"
							maxlength="20" /><br>
					</div>
					<div>
						주소<input style="text-align: center;" type="text" class="form-control" placeholder="주소" name="userAddress"
							value="${ user.userAddress }" maxlength="20" /><br>
					</div>
					<div>
						전화 번호<input style="text-align: center;" type="text" class="form-control" placeholder="전화번호" name="userPhone"
							value="${ user.userPhone }" maxlength="20" /><br>
					</div>
					<br>
					<div>
						성 별 : <input type="radio" name="userSex" autoComplete="off" value="MAN" checked>남자 &nbsp; <input type="radio"
							name="userSex" autoComplete="off" value="WOMAN" checked>여자
					</div>

					<br>
					</div>
					<div class=" col-xs-3 ">
						<ul>
							<li style="text-align: center;">
								
								<input type="button" class="btn btn-primary form-control"
									style="background-color: black; border-color: black" value="취소"
									onclick="javascript:window.location='MainForm.jsp'" /> <br>
									<br><input type="submit"
									class="btn btn-primary form-control" style="background-color: black; border-color: black"
									value="수정" />
							</li>
						</ul>
					</div>
				</form>
			</div>
		</div>
	</div>

	<!-- 푸터 영역  -->
	<jsp:include page="../../Footer.jsp"></jsp:include>
</body>

</body>

</html>