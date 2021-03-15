<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

	<!-- Category CSS -->
	<link rel="stylesheet" href="/BlackUp_Clone_Project/css/category.css">

	<title>CategoryForm</title>
</head>

<body>

	<!-- Navbar 부분 -->
	<jsp:include page="../../Header.jsp"></jsp:include>

	<!-- 상단 UI 부분 -->
	<section id="category_item">
		<h2 class="nav justify-content-center">${cgName}</h2>
		<div>
			<ul class="nav justify-content-center">
				<c:forEach var="type" items="${ cgTypeList }">
					<li class="nav-item">
						<c:choose>
							<c:when test="${ type == cgType }">
								<a class="nav-link bg-dark text-white"
									href="<%= request.getRequestURI() %>?name=${cgName}&type=${ type }">${ type }</a>
							</c:when>
							<c:otherwise>
								<a class="nav-link" href="<%= request.getRequestURI() %>?name=${cgName}&type=${ type }"
									style="color: #000000">${ type }</a>
							</c:otherwise>
						</c:choose>
					</li>
				</c:forEach>
			</ul>
		</div>
	</section>

	<!-- 아이템 섹션 -->
	<section class="container" id="new_item">

		<c:if test="${!empty productList }">
			<!-- Group 반복문 -->
			<c:forEach begin="0"
				end="${ productList.size() % 5 == 0 ? (productList.size() / 5) - 1 : productList.size() / 5 }" varStatus="vs">
				<%-- <P>${ vs.index }</P> --%>
				<div class="card-group">
					<!-- 상품 반복문 -->
					<c:forEach begin="${ vs.index * 5 }" end="${ (vs.index * 5) + 4 }" items="${ productList }" var="product"
						varStatus="vs2">
						<%-- <p>${ vs2.index }</p> --%>
						<%-- :<P>${ vs.index * 5 + 4 }</P> --%>
						<%-- <p>${ vs2.last }</p> --%>
						<div class="card border-0">
							<a href="/BlackUp_Clone_Project/product?pdID=${ product.pdID }">
								<img src="${ product.imagePathList[0].imgPath }" class="card-img-top" alt="..." /></a>
							<div class="card-body">
								<!-- 상품 정보 -->
								<p class="text-center">${ product.pdName }</p>
								<p class="text-center">${ product.pdPrice }won</p>
								<!-- 컬러 셋 -->
								<div class="colors d-flex align-items-center justify-content-center mt-1">
									<c:forEach var="color" items="${product.colorSetList }" varStatus="cs">
										<span class="border border-light"
											style="background-color: ${product.colorSetList[cs.index].colorCode}"></span>
									</c:forEach>
								</div>
							</div>
						</div>

						<c:if test="${ vs2.last && (vs2.index != (vs.index * 5 + 4)) }">
							<c:forEach begin="0" end="${ ((vs.index * 5 + 4) - vs2.index) - 1 }">
								<div class="card border-0">
									<img src="http://via.placeholder.com/264x357" class="card-img-top" alt="..." />
									<div class="card-body">
										<!-- 상품 정보 -->
										<p class="text-center">상품 준비중...</p>
										<p class="text-center">상품 준비중...</p>
										<!-- 컬러 셋 -->
									</div>
								</div>
							</c:forEach>
						</c:if>
					</c:forEach>
				</div>
			</c:forEach>
		</c:if>
	</section>

	<!-- Footer 부분 -->
	<jsp:include page="../../Footer.jsp"></jsp:include>

</body>

</html>