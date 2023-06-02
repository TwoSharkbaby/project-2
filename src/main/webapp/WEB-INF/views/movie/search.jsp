<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>


<div class="container content">
	<h2 class="fw-bold mt-5 mb-3 text-light mt-2">검색 키워드 : ${keyword}</h2>
	<c:choose>
		<c:when test="${empty search}">
			<h1>검색 결과 없음</h1>
		</c:when>
		<c:when test="${!empty search}">
			<div id="carouselExampleControls1" class="carousel slide mt-5" data-bs-ride="carousel">
				<div class="carousel-inner">
					<div class="carousel-item active mt-2">
						<div class="d-flex flex-owrap">
							<c:forEach var="movie" items="${search.content}">
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
			</div>
		</c:when>
	</c:choose>
	<nav aria-label="..." class="mt-5">
			<ul class="pagination justify-content-center" id="page">
				<c:choose>
					<c:when test="${movie.first}">
						<li class="page-item disabled"><a class="page-link">Previous</a></li>
					</c:when>
					<c:otherwise>
						<li class="page-item disabled"><a class="page-link" href="/search?keyword=${keyword}&page=${movie.number-1}">Previous</a></li>
					</c:otherwise>
				</c:choose>

				<c:forEach var="i" begin="0" end="${movie.totalPages > 0 ? movie.totalPages - 1 : 0}">
					<c:choose>
						<c:when test="${i eq movie.number}">
							<li class="page-item active" aria-current="page"><a class="page-link" href="#">${i+1}</a></li>
						</c:when>
						<c:otherwise>
							<li class="page-item"><a class="page-link" href="/search?keyword=${keyword}&page=${i}">${i+1}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>

				<c:choose>
					<c:when test="${movie.last}">
						<li class="page-item disabled"><a class="page-link">Next</a></li>
					</c:when>
					<c:otherwise>
						<li class="page-item disabled"><a class="page-link" href="/search?keyword=${keyword}&page=${movie.number+1}">Next</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</nav>
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