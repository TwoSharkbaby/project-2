<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>


<div class="container content">
	<div class="m-5">
		<div class="d-flex flex-wrap justify-content-between">
			<h5 class="fw-bold mb-3 text-light mt-2">기본 정보</h5>
			<sec:authorize access="hasRole('ROLE_ADMIN')">
				<div class="me-3">
					<a href="/admin/movie/updateForm/${movie.id}"><button class="btn btn-secondary ms-5 mt-1" style="height: 2.3em; padding-top: 0.25em; padding-bottom: 0.25em;">영화 수정</button></a>
					<button name="deleteMovie" class="btn btn-secondary ms-2 mt-1" style="height: 2.3em; padding-top: 0.25em; padding-bottom: 0.25em;">영화 삭제</button>
				</div>
			</sec:authorize>
		</div>
		<c:choose>
			<c:when test="${empty movie}">
				<h1>기본 정보 없음</h1>
			</c:when>
			<c:when test="${!empty movie}">
				<div class="d-flex flex-owrap">
					<img src="<c:out value="${movie.img}"/>" class="d-block detailPicture">
					<div class="mt-4 ms-4">
						<p class="mb-1 text-light">
							제목 :
							<c:out value="${movie.title}" />
						</p>
						<p class="mb-1 text-light">
							개봉일 :
							<fmt:formatDate pattern="yyyy-MM-dd" value="${movie.releaseDate}" />
						</p>
						<p class="mb-1 text-light">
							장르 :
							<c:out value="${movie.genre}" />
						</p>
						<p class="mb-1 text-light">
							상영시간 :
							<c:out value="${movie.runtime}" />
						</p>
						<c:choose>
							<c:when test="${'ROLE_ADMIN' eq principal.user.role}">
								<p class="mb-1 text-light">
									평점 :
									<fmt:formatNumber value="${empty movie.scorePoint ? 0.0 : movie.scorePoint}" pattern="0.0" />
									점
								</p>
								<p class="mb-3 text-light">
									작성자 :
									<c:out value="${movie.editor}" />
								</p>
							</c:when>
							<c:otherwise>
								<p class="mb-3 text-light">
									평점 :
									<fmt:formatNumber value="${empty movie.scorePoint ? 0.0 : movie.scorePoint}" pattern="0.0" />
									점
								</p>
							</c:otherwise>
						</c:choose>
						<p class="mb-0 text-light">
							<c:out value="${movie.synopsis}" />
						</p>
					</div>
				</div>
			</c:when>
		</c:choose>
	</div>
	<hr />
	<div class="m-5">
		<div class="d-flex flex-owrap justify-content-between">
			<h5 class="fw-bold mb-3 text-light mt-2">감독 / 배우</h5>
			<sec:authorize access="hasRole('ROLE_ADMIN')">
				<a href="/admin/filmmaker/saveForm/${movie.id}"><button class="btn btn-secondary me-3 mt-1" style="height: 2.3em; padding-top: 0.25em; padding-bottom: 0.25em;">배우 등록</button></a>
			</sec:authorize>
		</div>
		<c:choose>
			<c:when test="${empty filmmaker}">
				<h1>감독 / 배우 없음</h1>
			</c:when>
			<c:when test="${!empty filmmaker}">
				<div id="carouselExampleControls1" class="carousel slide mt-3" data-bs-ride="carousel">
					<div class="carousel-inner">
						<div class="carousel-item active">
							<div class="d-flex flex-owrap">
								<c:forEach var="filmmaker" items="${filmmaker}" begin="0" end="1">
									<div id="filmmaker">
										<div class="d-flex flex-owrap justify-content-between">
											<div class="d-flex flex-owrap">
												<img src="<c:out value="${filmmaker.img}" />" class="d-block personPicture">
												<div>
													<p class="mt-3 mb-1 text-light">
														<c:out value="${filmmaker.name}" />
													</p>
													<p class="mb-1 text-light">
														<c:out value="${filmmaker.role}" />
													</p>
												</div>
											</div>
											<div>
												<button name="filmmaker_modal" class="btn btn-secondary mt-4" data-idx="<c:out value="${filmmaker.id}" />">상세보기</button>
											</div>
										</div>
										<hr />
									</div>
								</c:forEach>
							</div>
							<div class="d-flex flex-owrap">
								<c:forEach var="filmmaker" items="${filmmaker}" begin="2" end="3">
									<div id="filmmaker">
										<div class="d-flex flex-owrap justify-content-between">
											<div class="d-flex flex-owrap">
												<img src="<c:out value="${filmmaker.img}" />" class="d-block personPicture">
												<div>
													<p class="mt-3 mb-1 text-light">
														<c:out value="${filmmaker.name}" />
													</p>
													<p class="mb-1 text-light">
														<c:out value="${filmmaker.role}" />
													</p>
												</div>
											</div>
											<div>
												<button name="filmmaker_modal" class="btn btn-secondary mt-4" data-idx="<c:out value="${filmmaker.id}" />">상세보기</button>
											</div>
										</div>
										<hr />
									</div>
								</c:forEach>
							</div>
							<div class="d-flex flex-owrap">
								<c:forEach var="filmmaker" items="${filmmaker}" begin="4" end="5">
									<div id="filmmaker">
										<div class="d-flex flex-owrap justify-content-between">
											<div class="d-flex flex-owrap">
												<img src="<c:out value="${filmmaker.img}" />" class="d-block personPicture">
												<div>
													<p class="mt-3 mb-1 text-light">
														<c:out value="${filmmaker.name}" />
													</p>
													<p class="mb-1 text-light">
														<c:out value="${filmmaker.role}" />
													</p>
												</div>
											</div>
											<div>
												<button name="filmmaker_modal" class="btn btn-secondary mt-4" data-idx="<c:out value="${filmmaker.id}" />">상세보기</button>
											</div>
										</div>
										<hr />
									</div>
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

	</div>
	<hr />
	<div class="m-5">
		<div class="d-flex flex-owrap justify-content-between">
			<h5 class="fw-bold text-light mt-2">리뷰</h5>
			<button name="reviewSave" data-idx="${movie.id}" class="btn btn-secondary mt-1" style="height: 2.3em; padding-top: 0.25em; padding-bottom: 0.25em;">리뷰 등록</button>
		</div>
	</div>
	<div>
		<ul class="list-group" id="review">
			<c:choose>
				<c:when test="${empty review}">
					<li class="list-group-item">
						<div>
							<div class="d-flex flex-owrap justify-content-between">
								<p>닉네임</p>
								<p>등록일</p>
							</div>
						</div>
						<div>
							<div class="d-flex flex-owrap justify-content-between">
								<div>
									<p>별점</p>
								</div>
								<div class="d-flex flex-owrap">
									<button id="upDown" class="me-1">👍</button>
									<p id="upButton" class="me-3">0</p>
									<button id="upDown" class="me-1">👎</button>
									<p id="downButton" class="me-2">0</p>
								</div>
							</div>
						</div>
						<div>
							<p>제목</p>
						</div>
						<div>
							<p>내용</p>
						</div>
					</li>
				</c:when>
				<c:when test="${!empty review}">
					<c:forEach var="review" items="${review.content}">
						<li class="list-group-item" id="pMargin">
							<div class="mt-2">
								<div class="d-flex flex-owrap justify-content-between">
									<p>
										닉네임 :
										<c:out value="${review.nickname}" />
									</p>
									<p>
										<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${review.createDate}" />
									</p>
								</div>
							</div>
							<div>
								<div class="d-flex flex-owrap justify-content-between">
									<div>
										<p>
											평점 :
											<c:out value="${review.scorePoint}" />
											점
										</p>
									</div>
									<div class="d-flex flex-owrap">
										<button name="upButton" id="upDown" class="me-1" data-idx="${review.id}">👍</button>
										<p id="upButton" class="me-3 mt-1">
											<c:out value="${review.good}" />
										</p>
										<button name="downButton" id="upDown" class="me-1" data-idx="${review.id}">👎</button>
										<p id="downButton" class="me-3 mt-1">
											<c:out value="${review.bad}" />
										</p>
										<c:choose>
											<c:when test="${review.user.id eq principal.user.id}">
												<a href="/auth/review/updateForm/${review.id}/${review.user.id}"><button class="btn btn-secondary btn-sm me-2" style="height: 2em;">수정</button></a>
												<button name="deleteReview" data-idx="${review.id}" data-idx1="${review.user.id}" class="btn btn-secondary btn-sm me-2" style="height: 2em;">삭제</button>
											</c:when>
											<c:when test="${'ROLE_ADMIN' eq principal.user.role}">
												<button name="deleteReview" data-idx="${review.id}" data-idx1="${review.user.id}" class="btn btn-secondary btn-sm me-2" style="height: 2em;">관리자 삭제</button>
											</c:when>
											<c:otherwise>
											</c:otherwise>
										</c:choose>
									</div>
								</div>
							</div>
							<div>
								<p>
									제목 :
									<c:out value="${review.title}" />
								</p>
							</div>
							<div class="mb-2">
								<p style="line-height: 1.4em;">
									<c:out value="${review.content}" />
								</p>
							</div>
						</li>
					</c:forEach>
				</c:when>
			</c:choose>
		</ul>
		<nav aria-label="..." class="mt-4">
			<ul class="pagination justify-content-center" id="page">
				<c:choose>
					<c:when test="${review.first}">
						<li class="page-item disabled"><a class="page-link">Previous</a></li>
					</c:when>
					<c:otherwise>
						<li class="page-item"><a class="page-link" href="?page=${review.number-1}">Previous</a></li>
					</c:otherwise>
				</c:choose>

				<c:forEach var="i" begin="0" end="${review.totalPages}">
					<c:choose>
						<c:when test="${i eq review.number}">
							<li class="page-item active" aria-current="page"><a class="page-link" href="#">${i+1}</a></li>
						</c:when>
						<c:otherwise>
							<li class="page-item"><a class="page-link" href="?page=${i}">${i+1}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>

				<c:choose>
					<c:when test="${review.last}">
						<li class="page-item disabled"><a class="page-link">Next</a></li>
					</c:when>
					<c:otherwise>
						<li class="page-item"><a class="page-link" href="?page=${review.number+1}">Next</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</nav>
	</div>

</div>

<div class="modal" tabindex="-1">
	<div class="modal-dialog modal-dialog-centered" style="width: 800em;">
		<div class="modal-content ps-4 pe-4 pt-1 pb-1">
			<div class="modal-header">
				<h3 class="fw-bold">감독 / 배우 상세보기</h3>
				<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<div class="d-flex flex-owrap">
					<div id="filmmakerImg"></div>
					<div class="fw-bold">
						<p class="mt-4">이&nbsp;&nbsp;&nbsp;름 :</p>
						<p>출생일 :</p>
						<p>성&nbsp;&nbsp;&nbsp;별 :</p>
						<p>캐스팅 :</p>
						<sec:authorize access="hasRole('ROLE_ADMIN')">
							<p>작성자 :</p>
						</sec:authorize>
						<p></p>
					</div>
					<div class="fw-bold ms-2">
						<p id="filmmakerName" class="mt-4"></p>
						<p id="filmmakerBrith"></p>
						<p id="filmmakerSex"></p>
						<p id="filmmakerRole"></p>
						<sec:authorize access="hasRole('ROLE_ADMIN')">
							<p id="filmmakerEditor"></p>
						</sec:authorize>
						<p></p>
					</div>
				</div>
				<hr style="border: solid 1px gray;" />
				<div>
					<p id="filmmakerInfo" style="line-height: 1.6em;">상세내용</p>
				</div>
			</div>
			<div class="modal-footer">
				<sec:authorize access="hasRole('ROLE_ADMIN')">
					<div id="filmmakerUD"></div>
				</sec:authorize>
				<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
			</div>
		</div>
	</div>
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
		
		$("button[name='filmmaker_modal']").on("click", function(e) {

			e.preventDefault();
			var filmmakerId = $(this).data("idx");
			
			$.ajax({
				type: "GET",
				url: "/filmmaker/" + filmmakerId,
				contentType: "application/json; charset=utf-8",
				dataType: "json"
			}).done(function(response) {
				showActorDetail(response);
			}).fail(function(error) {
				alert("올바른 경로가 아닙니다");
			});


		});

		function showActorDetail(response) {
			$("#filmmakerName").html(response.name);
			$("#filmmakerBrith").html(displayTime(response.birth));
			$("#filmmakerSex").html(response.sex);
			$("#filmmakerRole").html(response.role);
			$("#filmmakerInfo").html(response.info);
			$("#filmmakerImg").html("<img src=" + response.img + ">");
			<sec:authorize access="hasRole('ROLE_ADMIN')">
				$("#filmmakerEditor").html(response.editor);
				$("#filmmakerUD").html("<a href='/admin/filmmaker/updateForm/" + response.id + "/" + ${principal.user.id} + "'><button name='filmmakerUpdate' type='button' class='btn btn-secondary'>수정하기</button></a>"+
				"<button type='button' name='filmmakerDelete' data-idx='" + response.id + "' class='btn btn-secondary ms-2'>삭제하기</button>");
			</sec:authorize>
			$(".modal").modal("show");

		};
		
		$(document).on("click", "button[name='filmmakerDelete']", function(e) {

			e.preventDefault();
			var filmmakerId = $(this).data("idx");
			
			$.ajax({
				type: "DELETE",
				url: "/admin/filmmaker/" + filmmakerId,
				contentType: "application/json; charset=utf-8",
				dataType: "json"
			}).done(function(response) {
				alert("삭제 되었습니다");
				location.reload();
			}).fail(function(error) {
				alert("올바른 경로가 아닙니다");
			});


		});
		
		$("button[name='deleteMovie']").on("click", function(e) {

			e.preventDefault();
			var movieId = '<c:out value="${movie.id}"/>'
			
			$.ajax({
				type: "DELETE",
				url: "/admin/movie/" + movieId,
				contentType: "application/json; charset=utf-8",
				dataType: "json"
			}).done(function(response) {
				alert("삭제 되었습니다");
				location.href = "/";
			}).fail(function(error) {
				alert("올바른 경로가 아닙니다");
			}); 


		});
		
		$("button[name='deleteReview']").on("click", function(e) {

			e.preventDefault();
			var reviewId = $(this).data("idx");
			var userId = $(this).data("idx1");
			
			$.ajax({
				type: "DELETE",
				url: "/auth/review/" + reviewId + "/" + userId,
				contentType: "application/json; charset=utf-8",
				dataType: "json"
			}).done(function(response) {
				alert("삭제 되었습니다");
				location.reload();
			}).fail(function(error) {
				alert("올바른 경로가 아닙니다");
			}); 


		});
		
		$("button[name='reviewSave']").on("click", function(e) {

			e.preventDefault();
			var movieId = $(this).data("idx");
			var nickname = '${principal.user.nickname}';
			var userId = '${principal.user.id}';
			
			if(userId == null || userId === ""){
				alert("로그인 이후 리뷰를 등록하실수 있습니다. 로그인 페이지로 이동합니다.");
				location.href = "/loginForm"
			} else if (nickname == null || nickname === ""){
				alert("닉네임이 없으면 리뷰를 등록하실수 없습니다. 회원정보 페이지로 이동합니다.");
				location.href = "/updateForm/" + userId;
			} else {
				location.href = "/auth/review/saveForm/" + movieId;
			}

		});
		
		$("button[name='upButton']").on("click", function(e) {

			e.preventDefault();
			var reviewId = $(this).data("idx");
			var userId = '${principal.user.id}';
			
			var reviewUpDown = {
					  movieReview: {
					    id: reviewId
					  },
					  user: {
					    id: userId
					  }
					};
			
			if(userId == null || userId === ""){
				alert("로그인 이후 좋아요를 누를수 있습니다. 로그인 페이지로 이동합니다.");
				location.href = "/loginForm"
			} else {
				
				$.ajax({
					type: "POST",
					url: "/auth/review/up",
					data : JSON.stringify(reviewUpDown),
					contentType: "application/json; charset=utf-8",
					dataType: "json"
				}).done(function(response) {
					//alert("");
					location.reload();
				}).fail(function(error) {
					//alert("");
				}); 
				
			}

		});
		
		$("button[name='downButton']").on("click", function(e) {

			e.preventDefault();
			var reviewId = $(this).data("idx");
			var userId = '${principal.user.id}';
			
			var reviewUpDown = {
					  movieReview: {
					    id: reviewId
					  },
					  user: {
					    id: userId
					  }
					};
			
			if(userId == null || userId === ""){
				alert("로그인 이후 좋아요를 누를수 있습니다. 로그인 페이지로 이동합니다.");
				location.href = "/loginForm"
			} else {
				
				$.ajax({
					type: "POST",
					url: "/auth/review/down",
					data : JSON.stringify(reviewUpDown),
					contentType: "application/json; charset=utf-8",
					dataType: "json"
				}).done(function(response) {
					//alert("");
					location.reload();
				}).fail(function(error) {
					//alert("");
				}); 
				
			}

		});
		
		function displayTime(timeValue){
	      var today = new Date();
	      var gap = today.getTime() - timeValue;
	      var dateObj = new Date(timeValue);

	      if(gap < (1000 * 60 * 60 * 24)){
	        var hh = dateObj.getHours();
	        var mi = dateObj.getMinutes();
	        var ss = dateObj.getSeconds();
	        return [
	          (hh>9?'':'0')+hh, ':', 
	          (mi>9?'':'0')+mi, ':',
	          (ss>9?'':'0')+ss
	        ].join('');
	      } else {
	        var yy = dateObj.getFullYear();
	        var mm = dateObj.getMonth() + 1;
	        var dd = dateObj.getDate();
	        return [
	          yy, '/', (mm>9?'':'0') + mm, 
	          '/', (dd>9?'':'0') + dd
	        ].join('');
	      }
	   }

	  return  { 
	            displayTime : displayTime
	          };
	          
		
	});
</script>

<%@ include file="../layout/footer.jsp"%>