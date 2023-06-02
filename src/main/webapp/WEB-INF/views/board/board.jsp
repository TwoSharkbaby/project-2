<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>


<div class="container content">
	<div class="m-5">
		<div class="d-flex flex-owrap justify-content-between">
			<h5 class="fw-bold text-light mt-2">자유게시판</h5>
			<button name="boardSave" class="btn btn-secondary mt-1 me-2" style="height: 2.3em; padding-top: 0.25em; padding-bottom: 0.25em;">글쓰기</button>
		</div>
	</div>
	<div>
		<div class="container text-center" id="board">
			<c:choose>
				<c:when test="${!empty notice || !empty board.content}">
					<div class="row" id="line">
						<div class="col-1">유형</div>
						<div class="col-6">제목</div>
						<div class="col-2">글쓴이</div>
						<div class="col-1">조회수</div>
						<div class="col-1">좋아요</div>
						<div class="col-1">싫어요</div>
					</div>

					<c:forEach var="notice" items="${notice}">
						<div class="row" id="notice">
							<div class="col-1"><c:out value="${notice.type}"></c:out></div>
							<div class="col-6 text-start"><a href="/board/detail/${notice.id}"><c:out value="${notice.title}"></c:out></a></div>
							<div class="col-2"><c:out value="${notice.nickname}"></c:out></div>
							<div class="col-1"><c:out value="${notice.counting}"></c:out></div>
							<div class="col-1"><c:out value="${notice.good}"></c:out></div>
							<div class="col-1"><c:out value="${notice.bad}"></c:out></div>
						</div>
					</c:forEach>

					<c:forEach var="board" items="${board.content}">
						<div class="row" id="normal">
							<div class="col-1"><c:out value="${board.type}"></c:out></div>
							<div class="col-6 text-start"><a href="/board/detail/${board.id}"><c:out value="${board.title}"></c:out></a></div>
							<div class="col-2"><c:out value="${board.nickname}"></c:out></div>
							<div class="col-1"><c:out value="${board.counting}"></c:out></div>
							<div class="col-1"><c:out value="${board.good}"></c:out></div>
							<div class="col-1"><c:out value="${board.bad}"></c:out></div>
						</div>

					</c:forEach>
				</c:when>
				<c:otherwise>
					<div class="row" id="line">
						<div class="col-1">유형</div>
						<div class="col-7">제목</div>
						<div class="col-2">글쓴이</div>
						<div class="col-1">조회수</div>
						<div class="col-1">좋아요</div>
					</div>
				</c:otherwise>
			</c:choose>
		</div>

				<nav aria-label="..." class="mt-5">
					<ul class="pagination justify-content-center" id="page">
						<c:choose>
							<c:when test="${board.first}">
								<li class="page-item disabled"><a class="page-link">Previous</a></li>
							</c:when>
							<c:otherwise>
								<li class="page-item"><a class="page-link" href="?page=${board.number-1}">Previous</a></li>
							</c:otherwise>
						</c:choose>

						<c:forEach var="i" begin="0" end="${board.totalPages}">
							<c:choose>
								<c:when test="${i eq board.number}">
									<li class="page-item active" aria-current="page"><a class="page-link" href="#">${i+1}</a></li>
								</c:when>
								<c:otherwise>
									<li class="page-item"><a class="page-link" href="?page=${i}">${i+1}</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>

						<c:choose>
							<c:when test="${board.last}">
								<li class="page-item disabled"><a class="page-link">Next</a></li>
							</c:when>
							<c:otherwise>
								<li class="page-item"><a class="page-link" href="?page=${board.number+1}">Next</a></li>
							</c:otherwise>
						</c:choose>
					</ul>
				</nav>
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

		$("button[name='boardSave']").on("click", function(e) {

			e.preventDefault();
			var nickname = '${principal.user.nickname}';
			var userId = '${principal.user.id}';

			if (userId == null || userId === "") {
				alert("로그인 이후 리뷰를 등록하실수 있습니다. 로그인 페이지로 이동합니다.");
				location.href = "/loginForm"
			} else if (nickname == null || nickname === "") {
				alert("닉네임이 없으면 리뷰를 등록하실수 없습니다. 회원정보 페이지로 이동합니다.");
				location.href = "/updateForm/" + userId;
			} else {
				location.href = "auth/board/saveForm";
			}

		});

	});
</script>

<%@ include file="../layout/footer.jsp"%>