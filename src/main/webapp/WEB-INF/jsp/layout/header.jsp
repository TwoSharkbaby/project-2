<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal" var="principal" />
</sec:authorize>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>TwoSharkbaby</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
	crossorigin="anonymous">
<script src="/js/jquery.js"></script>
<link rel="stylesheet" type="text/css" href="/css/css.css">
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js"></script>
</head>
<body>
	<header class="p-3 text-bg-dark">
		<div class="container">
			<div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">

				<ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
					<li><a href="/" class="nav-link px-2 text-white fw-bold">홈</a></li>
					<li></li>
					<li></li>
					<li><a href="/board" class="nav-link px-5 text-white fw-bold">자유게시판</a></li>
				</ul>

				<form action="/search" method="get" class="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3" role="search">
					<input type="search" name="keyword" class="form-control form-control-dark text-bg-dark" placeholder="Search..." aria-label="Search">
				</form>

				<div class="text-end">
					<c:choose>
						<c:when test="${empty principal}">
							<a href="/loginForm"><button type="button" class="btn btn-outline-light me-2">로그인</button></a>
							<a href="/joinForm"><button type="button" class="btn btn-outline-light me-2">회원가입</button></a>
						</c:when>
						<c:otherwise>
							<a href="/updateForm/${principal.user.id}"><button type="button" class="btn btn-outline-light me-2">회원정보</button></a>
							<a href="/logout"><button type="button" class="btn btn-outline-light me-2">로그아웃</button></a>
						</c:otherwise>
					</c:choose>

				</div>
			</div>
		</div>
	</header>