<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
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

  <!-- Login CSS -->
  <link rel="stylesheet" href="/BlackUp_Clone-Project/css/login.css">

  <title>LogInForm</title>
</head>

<body>
  <!-- 헤더 영역  -->
  <jsp:include page="../../Header.jsp"></jsp:include>

  <div class="container" style="padding: 200px 100px">
    <div class="col-lg-5"></div>
    <div class="col-lg-5" style="margin: auto">
      <div class="jumbtron" style="padding-top: 20px">
        <form method="post" action="login">
          <h3 style="text-align: center">로그인 화면</h3>
          <div class="form-group">
            <br />
            <input style="text-align: center" type="text" class="form-control" placeholder="아이디" name="userID"
              maxlength="20" />
            <br />
            <input style="text-align: center" type="password" class="form-control" placeholder="비밀번호"
              name="userPassword" maxlength="20" />
            <br />
            <input type="submit" style="background-color: black; border-color: black"
              class="btn btn-primary form-control" value="로그인" />
          </div>
        </form>
      </div>
    </div>
    <div class="col-lg-5"></div>
  </div>

  <!-- 푸터 영역  -->
  <jsp:include page="../../Footer.jsp"></jsp:include>
</body>

</html>