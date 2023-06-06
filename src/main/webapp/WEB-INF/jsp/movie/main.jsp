<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>


<div class="container content">
	<sec:authorize access="hasRole('ROLE_ADMIN')">
		<div class="d-flex justify-content-end">
			<a href="/admin/movie/saveForm"><button class="btn btn-secondary mt-5 me-3">영화 등록</button></a>
		</div>
	</sec:authorize>
	<c:choose>
		<c:when test="${empty latest}">
			<h1>최신 영화 데이터 없음</h1>
		</c:when>
		<c:when test="${!empty latest}">
			<div id="carouselExampleControls1" class="carousel slide mt-5" data-bs-ride="carousel">
				<div class="carousel-inner">
					<h2 class="fw-bold mb-3 text-light mt-2">최신 영화</h2>
					<div class="carousel-item active mt-2">
						<div class="d-flex flex-owrap">
							<c:forEach var="movie" items="${latest}" begin="0" end="4">
								<a href="/movie/detail/${movie.id}"><div>
										<img src="<c:out value="${movie.img}"/>" class="d-block mainPicture">
										<div class="mt-1">
											<p class="mb-0 text-light">
												제목:
												<c:out value="${movie.title}" />
											</p>
											<p class="mb-0 text-light">
												개봉일:
												<fmt:formatDate pattern="yyyy-MM-dd" value="${movie.releaseDate}" />
											</p>
											<p class="mb-0 text-light">
												장르:
												<c:out value="${movie.genre}" />
											</p>
											<p class="mb-0 text-light">
												감독:
												<c:out value="${movie.director}" />
											</p>
										</div>
									</div> </a>
							</c:forEach>
						</div>
					</div>
					<div class="carousel-item mt-2">
						<div class="d-flex flex-owrap">
							<c:forEach var="movie" items="${latest}" begin="5" end="9">
								<a href="/movie/detail/${movie.id}"><div>
										<img src="<c:out value="${movie.img}"/>" class="d-block mainPicture">
										<div class="mt-1">
											<p class="mb-0 text-light">
												제목:
												<c:out value="${movie.title}" />
											</p>
											<p class="mb-0 text-light">
												개봉일:
												<fmt:formatDate pattern="yyyy-MM-dd" value="${movie.releaseDate}" />
											</p>
											<p class="mb-0 text-light">
												장르:
												<c:out value="${movie.genre}" />
											</p>
											<p class="mb-0 text-light">
												감독:
												<c:out value="${movie.director}" />
											</p>
										</div>
									</div> </a>
							</c:forEach>
						</div>
					</div>
				</div>
				<button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleControls1" data-bs-slide="prev">
					<span class="carousel-control-prev-icon" aria-hidden="true"></span> <span class="visually-hidden">Previous</span>
				</button>
				<button class="carousel-control-next" type="button" data-bs-target="#carouselExampleControls1" data-bs-slide="next">
					<span class="carousel-control-next-icon" aria-hidden="true"></span> <span class="visually-hidden">Next</span>
				</button>
			</div>
		</c:when>
	</c:choose>

	<c:choose>
		<c:when test="${empty action}">
			<h1>액션 영화 데이터 없음</h1>
		</c:when>
		<c:when test="${!empty action}">
			<div id="carouselExampleControls2" class="carousel slide mt-5" data-bs-ride="carousel">
				<div class="carousel-inner">
					<h2 class="fw-bold mb-3 text-light mt-2">액션 영화</h2>
					<div class="carousel-item active mt-2">
						<div class="d-flex flex-owrap">
							<c:forEach var="movie" items="${action}" begin="0" end="4">
								<a href="/movie/detail/${movie.id}"><div>
										<img src="<c:out value="${movie.img}"/>" class="d-block mainPicture">
										<div class="mt-1">
											<p class="mb-0 text-light">
												제목:
												<c:out value="${movie.title}" />
											</p>
											<p class="mb-0 text-light">
												개봉일:
												<fmt:formatDate pattern="yyyy-MM-dd" value="${movie.releaseDate}" />
											</p>
											<p class="mb-0 text-light">
												장르:
												<c:out value="${movie.genre}" />
											</p>
											<p class="mb-0 text-light">
												감독:
												<c:out value="${movie.director}" />
											</p>
										</div>
									</div> </a>
							</c:forEach>
						</div>
					</div>
					<div class="carousel-item mt-2">
						<div class="d-flex flex-owrap">
							<c:forEach var="movie" items="${action}" begin="5" end="9">
								<a href="/movie/detail/${movie.id}"><div>
										<img src="<c:out value="${movie.img}"/>" class="d-block mainPicture">
										<div class="mt-1">
											<p class="mb-0 text-light">
												제목:
												<c:out value="${movie.title}" />
											</p>
											<p class="mb-0 text-light">
												개봉일:
												<fmt:formatDate pattern="yyyy-MM-dd" value="${movie.releaseDate}" />
											</p>
											<p class="mb-0 text-light">
												장르:
												<c:out value="${movie.genre}" />
											</p>
											<p class="mb-0 text-light">
												감독:
												<c:out value="${movie.director}" />
											</p>
										</div>
									</div> </a>
							</c:forEach>
						</div>
					</div>
				</div>
				<button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleControls2" data-bs-slide="prev">
					<span class="carousel-control-prev-icon" aria-hidden="true"></span> <span class="visually-hidden">Previous</span>
				</button>
				<button class="carousel-control-next" type="button" data-bs-target="#carouselExampleControls2" data-bs-slide="next">
					<span class="carousel-control-next-icon" aria-hidden="true"></span> <span class="visually-hidden">Next</span>
				</button>
			</div>
		</c:when>
	</c:choose>

	<c:choose>
		<c:when test="${empty romance}">
			<h1>로멘스 영화 데이터 없음</h1>
		</c:when>
		<c:when test="${!empty romance}">
			<div id="carouselExampleControls3" class="carousel slide mt-5" data-bs-ride="carousel">
				<div class="carousel-inner">
					<h2 class="fw-bold mb-3 text-light mt-2">로멘스 영화</h2>
					<div class="carousel-item active mt-2">
						<div class="d-flex flex-owrap">
							<c:forEach var="movie" items="${romance}" begin="0" end="4">
								<a href="/movie/detail/${movie.id}"><div>
										<img src="<c:out value="${movie.img}"/>" class="d-block mainPicture">
										<div class="mt-1">
											<p class="mb-0 text-light">
												제목:
												<c:out value="${movie.title}" />
											</p>
											<p class="mb-0 text-light">
												개봉일:
												<fmt:formatDate pattern="yyyy-MM-dd" value="${movie.releaseDate}" />
											</p>
											<p class="mb-0 text-light">
												장르:
												<c:out value="${movie.genre}" />
											</p>
											<p class="mb-0 text-light">
												감독:
												<c:out value="${movie.director}" />
											</p>
										</div>
									</div> </a>
							</c:forEach>
						</div>
					</div>
					<div class="carousel-item mt-2">
						<div class="d-flex flex-owrap">
							<c:forEach var="movie" items="${romance}" begin="5" end="9">
								<a href="/movie/detail/${movie.id}"><div>
										<img src="<c:out value="${movie.img}"/>" class="d-block mainPicture">
										<div class="mt-1">
											<p class="mb-0 text-light">
												제목:
												<c:out value="${movie.title}" />
											</p>
											<p class="mb-0 text-light">
												개봉일:
												<fmt:formatDate pattern="yyyy-MM-dd" value="${movie.releaseDate}" />
											</p>
											<p class="mb-0 text-light">
												장르:
												<c:out value="${movie.genre}" />
											</p>
											<p class="mb-0 text-light">
												감독:
												<c:out value="${movie.director}" />
											</p>
										</div>
									</div> </a>
							</c:forEach>
						</div>
					</div>
				</div>
				<button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleControls3" data-bs-slide="prev">
					<span class="carousel-control-prev-icon" aria-hidden="true"></span> <span class="visually-hidden">Previous</span>
				</button>
				<button class="carousel-control-next" type="button" data-bs-target="#carouselExampleControls3" data-bs-slide="next">
					<span class="carousel-control-next-icon" aria-hidden="true"></span> <span class="visually-hidden">Next</span>
				</button>
			</div>
		</c:when>
	</c:choose>

</div>

<script type="text/javascript">
	$(document).ready(function() {

		var result = '<c:out value="${result}"/>';
		checkModal(result);
		history.replaceState({}, null, null);
		function checkModal(result) {
			if (result === '' || history.state) {
				return;
			} else {
				alert(result);
			}
		}
	});
</script>

<%@ include file="../layout/footer.jsp"%>