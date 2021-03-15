<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
  <!-- Web 설정 -->
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <meta name="viewport" content="width=device-width initial-scale=1" />
  <!-- JS -->
  <script src="./js/bootstrap.min.js"></script>
  <script src="./js/jquery-3.5.1.min.js"></script>
  <script src="./js/popper.min.js"></script>

  <!-- CSS -->
  <link rel="stylesheet" href="./css/bootstrap.min.css" />
  <link rel="stylesheet" href="./css/custom.css" />

  <!-- Main CSS -->
  <link rel="stylesheet" href="./css/main.css" />

  <title>BlackUp_Clone</title>
</head>

<body>
  <!-- 헤드 부분 -->
  <jsp:include page="./Header.jsp"></jsp:include>

  <!-- 맨위 슬라이드 섹션 -->
  <section id="main_slide">
    <div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel">
      <div class="carousel-indicators">
        <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active"
          aria-current="true" aria-label="Slide 1"></button>
        <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1"
          aria-label="Slide 2"></button>
        <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2"
          aria-label="Slide 3"></button>
      </div>
      <div class="carousel-inner">
        <div class="carousel-item active">
          <img src="https://black-up.kr/web/upload/yangji_pc_crumb/210309-main01.jpg" class="d-block w-100"
            alt="main_img" />
        </div>
        <div class="carousel-item">
          <img src="https://black-up.kr/web/upload/yangji_pc_crumb/210309-main02.jpg" class="d-block w-100"
            alt="main_img" />
        </div>
        <div class="carousel-item">
          <img src="https://black-up.kr/web/upload/yangji_pc_crumb/210309-main03.jpg" class="d-block w-100"
            alt="main_img" />
        </div>
      </div>
      <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators"
        data-bs-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
      </button>
      <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators"
        data-bs-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
      </button>
    </div>
  </section>

  <!-- MADE 섹션 -->
  <section class="container" id="made_item">
    <h2 class="product-title text-start">Made Item</h2>
    <c:forEach begin="0" end="${ num }" varStatus="vs">
      <div class="card-group">
        <c:forEach begin="${ vs.index + (vs.index * 3) }" end="${ (vs.index + (vs.index * 3)) + 3 }"
          items="${ madeList }" var="product">
          <div class="card">
            <a href="/BlackUp_Clone_Project/product?pdID=${ product.pdID }">
              <img src="${ product.imagePathList[0].imgPath }" class="card-img-top" alt="..." />
            </a>
          </div>
        </c:forEach>
      </div>
    </c:forEach>
    <div class="vbtn d-flex justify-content-center mt-5">
      <a class="d-flex justify-content-center align-content-center" href="/BlackUp_Clone_Project/main?num=${ num }"
        class="">View More
      </a>
    </div>
  </section>

  <!-- PANTS 섹션 -->
  <section class="container" id="pants_item">
    <h2 class="product-title text-start">Pants Item</h2>
    <c:forEach begin="0" end="1" varStatus="vs">
      <div class="card-group">
        <c:forEach begin="${ vs.index + (vs.index * 3) }" end="${ (vs.index + (vs.index * 3)) + 3 }"
          items="${ pantsList }" var="pants">
          <!-- 의상 -->
          <div class="card border-0">
            <a href="/BlackUp_Clone_Project/product?pdID=${ pants.pdID }">
              <img src="${ pants.imagePathList[0].imgPath }" class="card-img-top" alt="..." />
            </a>
            <div class="card-body">
              <!-- 상품 정보 -->
              <p class="text-center">${ pants.pdName }</p>
              <p class="text-center">${ patns.pdPrice }won</p>
              <!-- 컬러 셋 -->
              <div class="colors d-flex align-items-center justify-content-center mt-1">
                <c:forEach items="${ pants.colorSetList }" var="color">
                  <span class="border border-dark" style="background-color: ${color.colorCode}"></span>
                </c:forEach>
              </div>
            </div>
          </div>
        </c:forEach>
      </div>
    </c:forEach>
  </section>
  <!-- OUTER 섹션 -->
  <section class="container" id="pants_item">
    <h2 class="product-title text-start">Outer Item</h2>
    <c:forEach begin="0" end="1" varStatus="vs">
      <div class="card-group">
        <c:forEach begin="${ vs.index + (vs.index * 3) }" end="${ (vs.index + (vs.index * 3)) + 3 }"
          items="${ outerList }" var="outer">
          <!-- 의상 -->
          <div class="card border-0">
            <a href="/BlackUp_Clone_Project/product?pdID=${ outer.pdID }">
              <img src="${ outer.imagePathList[0].imgPath }" class="card-img-top" alt="..." />
            </a>
            <div class="card-body">
              <!-- 상품 정보 -->
              <p class="text-center">${ outer.pdName }</p>
              <p class="text-center">${ outer.pdPrice }won</p>
              <!-- 컬러 셋 -->
              <div class="colors d-flex align-items-center justify-content-center mt-1">
                <c:forEach items="${ outer.colorSetList }" var="color">
                  <span class="border border-dark" style="background-color: ${color.colorCode}"></span>
                </c:forEach>
              </div>
            </div>
          </div>
        </c:forEach>
      </div>
    </c:forEach>
  </section>

  <!-- 푸터 부분 -->
  <jsp:include page="./Footer.jsp"></jsp:include>
</body>

</html>