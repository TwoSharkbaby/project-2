<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>


<div class="container content">
	<div class="m-5">
		<div class="d-flex flex-owrap justify-content-end">
			<c:choose>
				<c:when test="${board.user.id eq principal.user.id}">
					<a href="/auth/board/updateForm/${board.id}/${board.user.id}"><button name="boardSave" class="btn btn-secondary mt-1 me-2" style="height: 2.3em; padding-top: 0.25em; padding-bottom: 0.25em;">수정</button></a>
					<button name="boardDelete" data-idx="${board.id}" data-idx1="${board.user.id}" class="btn btn-secondary mt-1 me-2" style="height: 2.3em; padding-top: 0.25em; padding-bottom: 0.25em;">삭제</button>
				</c:when>
				<c:when test="${'ROLE_ADMIN' eq principal.user.role}">
					<button name="boardDelete" data-idx="${board.id}" data-idx1="${board.user.id}" class="btn btn-secondary mt-1 me-2" style="height: 2.3em; padding-top: 0.25em; padding-bottom: 0.25em;">관리자
						삭제</button>
				</c:when>
				<c:otherwise>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	<div>
		<div class="container" id="board">
			<c:choose>
				<c:when test="${!empty board}">
					<div class="row" id="line2">
						<div class="col-9">
							<c:out value="${board.title}" />
						</div>
						<div id="s" class="col-3" style="text-align: right;">
							<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${board.createDate}" />
						</div>
					</div>

					<div class="row" id="line3">
						<div class="col-6">
							닉네임&nbsp;&nbsp;
							<c:out value="${board.nickname}" />
						</div>
						<div id="s" class="col-2" style="text-align: right;">
							조회수&nbsp;&nbsp;
							<c:out value="${board.counting}" />
						</div>
						<div id="s" class="col-2" style="text-align: right;">
							추천수&nbsp;&nbsp;
							<c:out value="${board.good}" />
						</div>
						<div id="s" class="col-2" style="text-align: right;">
							비추수&nbsp;&nbsp;
							<c:out value="${board.bad}" />
						</div>
					</div>

					<div class="row" id="normal">
						<div class="col-12 m-3">${board.content}</div>
					</div>
				</c:when>
				<c:otherwise>
					<div class="row" id="line2">
						<div class="col-9">제목</div>
						<div id="s" class="col-3" style="text-align: right;">작성일</div>
					</div>

					<div class="row" id="line3">
						<div class="col-6">닉네임&nbsp;&nbsp;</div>
						<div id="s" class="col-2" style="text-align: right;">조회수&nbsp;&nbsp;</div>
						<div id="s" class="col-2" style="text-align: right;">추천수&nbsp;&nbsp;</div>
						<div id="s" class="col-2" style="text-align: right;">비추수&nbsp;&nbsp;</div>
					</div>

					<div class="row" id="normal">
						<div class="col-12 m-3">내용</div>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	<div class="container" id="board2">
		<div class="mt-4 mb-4">
			<div class="d-flex flex-owrap justify-content-center">
				<button name="upButton" data-idx="${board.id}" class="me-1 btn btn-secondary">👍&nbsp;&nbsp;좋아요</button>
				<p id="upButton" class="ms-2 me-4" style="text-align: center; line-height: 2.5em; height: 25px;">
					<c:out value="${board.good}" />
				</p>
				<button name="downButton" data-idx="${board.id}" class="me-1 btn btn-secondary">👎&nbsp;&nbsp;싫어요</button>
				<p id="downButton" class="ms-2 me-4" style="text-align: center; line-height: 2.5em; height: 25px;">
					<c:out value="${board.bad}" />
				</p>
			</div>
		</div>
	</div>
	<div class="container" id="board">
		<div class="m-4">
			<div class="d-flex flex-owrap">
				<h5 class="fw-bold text-light mt-2">댓글&nbsp;</h5>
				<h5 class="fw-bold text-light mt-2">${count}</h5>
				<h5 class="fw-bold text-light mt-2">&nbsp;개</h5>
			</div>
		</div>
		<div>
			<ul class="list-group" id="reply1">
				<c:choose>
					<c:when test="${empty reply.content}">
						<li class="list-group-item">
							<div>
								<div class="d-flex flex-owrap justify-content-between">
									<div class="d-flex flex-owrap">
										<p>닉네임</p>
										<p>&nbsp;&nbsp;</p>
										<p>등록일</p>
									</div>
									<div class="d-flex flex-owrap">
										<button id="upDown" class="me-1">👍</button>
										<p id="replyUpButton" class="me-3">0</p>
										<button id="upDown" class="me-1">👎</button>
										<p id="replyDownButton" class="me-2">0</p>
									</div>
								</div>
								<div>
									<p class="mb-2">내용</p>
								</div>
							</div>
						</li>
						<li class="list-group-item mt-3">
							<div>
								<div class="d-flex flex-owrap">
									<p class="mb-3">댓글 작성</p>
								</div>
								<div class="d-flex flex-owrap justify-content-between">
									<div class="form-floating mb-3">
										<textarea id="reply" class="form-control" placeholder="Leave a comment here" id="floatingTextarea2" style="height: 100px"></textarea>
									</div>
									<button name="replyButton" id="replyButton" class="btn btn-secondary ms-1">등록</button>
								</div>
							</div>
						</li>
					</c:when>
					<c:when test="${!empty reply}">
						<c:forEach var="reply" items="${reply.content}">
							<li class="list-group-item" style="margin-left: calc(var(--reply-indent) * ${reply.dep});">
								<div>
									<div class="d-flex flex-owrap justify-content-between">
										<div class="d-flex flex-owrap">
											<p>
												<c:out value="${reply.nickname}" />
											</p>
											<p>&nbsp;&nbsp;</p>
											<p>
												<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${reply.createDate}" />
											</p>
										</div>
										<div class="d-flex flex-owrap">
											<button name="replyUpButton" data-idx="${reply.id}" id="upDown" class="">👍</button>
											<p id="replyUpButton">
												<c:out value="${reply.good}" />
											</p>
											<button name="replyDownButton" data-idx="${reply.id}" id="upDown" class="ms-1">👎</button>
											<p id="replyDownButton">
												<c:out value="${reply.bad}" />
											</p>
											<div class="ms-2">
												<c:choose>
													<c:when test="${reply.user.id eq principal.user.id}">
														<button name="replyUpdate" data-idx="${reply.id}" data-idx1="${reply.content}" data-idx2="${reply.user.id}" class="btn btn-secondary btn-sm mb-2" style="height: 1.8em; line-height: 1em;">수정</button>
														<button name="replyDelete" data-idx="${reply.id}" data-idx1="${reply.user.id}" class="btn btn-secondary btn-sm mb-2" style="height: 1.8em; line-height: 1em;">삭제</button>
													</c:when>
													<c:when test="${'ROLE_ADMIN' eq principal.user.role}">
														<button name="replyDelete" data-idx="${reply.id}" data-idx1="${reply.user.id}" class="btn btn-secondary btn-sm mb-2" style="height: 1.8em; line-height: 1em;">관리자 삭제</button>
													</c:when>
												</c:choose>
												<button name="reReply" data-idx="${reply.id}" data-idx1="${reply.nickname}" class="btn btn-secondary btn-sm mb-2" style="height: 1.8em; line-height: 1em;">댓글</button>
											</div>
										</div>
									</div>
								</div>
								<div class="d-flex flex-owrap">
									<p class="me-3 mb-1">
									<c:choose>
										<c:when test="${reply.dep ne 0}">
											<img src="/image/re.png" style="width: 1em;">&nbsp;&nbsp;<c:out value="${reply.content}" />
										</c:when>
										<c:otherwise>
											<c:out value="${reply.content}" />
										</c:otherwise>
									</c:choose>
									</p>
								</div>
							</li>
						</c:forEach>
						<li class="list-group-item mt-3">
							<div>
								<div class="d-flex flex-owrap">
									<p class="mb-3" id="replyModify">댓글 작성</p>
								</div>
								<div class="d-flex flex-owrap justify-content-between">
									<div class="form-floating mb-3">
										<textarea id="reply" class="form-control" placeholder="Leave a comment here" id="floatingTextarea2" style="height: 100px"></textarea>
									</div>
									<div id="addModify">
										<button name="replyButton" id="replyButton" class="btn btn-secondary ms-1">등록</button>
									</div>
								</div>
							</div>
						</li>
					</c:when>
				</c:choose>
			</ul>
			<nav aria-label="..." class="mt-4">
				<ul class="pagination justify-content-center" id="page">
					<c:choose>
						<c:when test="${reply.first}">
							<li class="page-item disabled"><a class="page-link">Previous</a></li>
						</c:when>
						<c:otherwise>
							<li class="page-item"><a class="page-link" href="?page=${reply.number-1}">Previous</a></li>
						</c:otherwise>
					</c:choose>

					<c:forEach var="i" begin="0" end="${reply.totalPages}">
						<c:choose>
							<c:when test="${i eq reply.number}">
								<li class="page-item active" aria-current="page"><a class="page-link" href="#">${i+1}</a></li>
							</c:when>
							<c:otherwise>
								<li class="page-item"><a class="page-link" href="?page=${i}">${i+1}</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>

					<c:choose>
						<c:when test="${reply.last}">
							<li class="page-item disabled"><a class="page-link">Next</a></li>
						</c:when>
						<c:otherwise>
							<li class="page-item"><a class="page-link" href="?page=${reply.number+1}">Next</a></li>
						</c:otherwise>
					</c:choose>
				</ul>
			</nav>
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

		$("button[name='boardDelete']").on("click", function(e) {

			e.preventDefault();
			var boardId = $(this).data("idx");
			var userId = $(this).data("idx1");

			$.ajax({
				type : "DELETE",
				url : "/auth/board/" + boardId + "/" + userId,
				contentType : "application/json; charset=utf-8",
				dataType : "json"
			}).done(function(response) {
				alert("삭제 되었습니다");
				location.href = "/board";
			}).fail(function(error) {
				alert("올바르지 않은 경로 입니다");
				location.href = "/board/detail/" + boardId;
			});

		});

		$("button[name='upButton']").on("click", function(e) {

			e.preventDefault();
			var boardId = $(this).data("idx");
			var userId = '${principal.user.id}';

			var boardUpDown = {
				board : {
					id : boardId
				},
				user : {
					id : userId
				}
			};

			if (userId == null || userId === "") {
				alert("로그인 이후 좋아요를 누를수 있습니다. 로그인 페이지로 이동합니다.");
				location.href = "/loginForm"
			} else {

				$.ajax({
					type : "POST",
					url : "/auth/board/up",
					data : JSON.stringify(boardUpDown),
					contentType : "application/json; charset=utf-8",
					dataType : "json"
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
			var boardId = $(this).data("idx");
			var userId = '${principal.user.id}';

			var boardUpDown = {
				board : {
					id : boardId
				},
				user : {
					id : userId
				}
			};

			if (userId == null || userId === "") {
				alert("로그인 이후 좋아요를 누를수 있습니다. 로그인 페이지로 이동합니다.");
				location.href = "/loginForm"
			} else {

				$.ajax({
					type : "POST",
					url : "/auth/board/down",
					data : JSON.stringify(boardUpDown),
					contentType : "application/json; charset=utf-8",
					dataType : "json"
				}).done(function(response) {
					//alert("");
					location.reload();
				}).fail(function(error) {
					//alert("");
				});

			}

		});
		
		var replyId = null;
		var userId = null;

		$("button[name='replyButton']").on("click", function(e) {

			e.preventDefault();
			var userId = '${principal.user.id}';
			var userNickname = '${principal.user.nickname}';
			var content = document.getElementById("reply").value;
			var boardId = '${board.id}';
			var id = replyId;

			var reply = {
				id : id,
				nickname : userNickname,
				content : content,
				board : {
					id : boardId
				},
				user : {
					id : userId
				}
			};

			if (userId == null || userId === "") {
				alert("로그인 이후 좋아요를 누를수 있습니다. 로그인 페이지로 이동합니다.");
				location.href = "/loginForm"
			} else {

				$.ajax({
					type : "POST",
					url : "/auth/reply",
					data : JSON.stringify(reply),
					contentType : "application/json; charset=utf-8",
					dataType : "json"
				}).done(function(response) {
					alert("댓글 등록 성공");
					location.reload();
				}).fail(function(error) {
					alert("댓글이 삭제 되었거나 올바르지 않은 요청입니다");
					location.reload();
				});

			}

		});

		$("button[name='reReply']").on("click", function(e) {

			e.preventDefault();
			var userId = '${principal.user.id}';
			
			if (userId == null || userId === "") {
				alert("로그인 이후 좋아요를 누를수 있습니다. 로그인 페이지로 이동합니다.");
				location.href = "/loginForm"
			} else {
				replyId = $(this).data("idx");
				var nickname = $(this).data("idx1");
				
				document.getElementById("replyButton").innerHTML = "등록";
				document.getElementById("replyModify").innerHTML = nickname + "에 대한 댓글";
			}

		});
		
		
		$("button[name='replyUpdate']").on("click", function(e) {

			e.preventDefault();

 			replyId = $(this).data("idx");
 			var replyContent = $(this).data("idx1");
 			userId = $(this).data("idx2");
				
			document.getElementById("replyModify").innerHTML = "댓글 수정";
			document.getElementById("reply").value = replyContent;
			$("#addModify").html(`<button name="updateButton" id="replyButton" class="btn btn-secondary ms-1">수정</button>`);
			
		});
		
		
		$(document).on("click", "button[name='updateButton']", function(e) {

			e.preventDefault();

			var content = document.getElementById("reply").value;
			
			var reply = {
					id : replyId,
					content : content,
					user : {
						id : userId
					}
				};
				
			$.ajax({
				type : "PUT",
				url : "/auth/reply",
				data : JSON.stringify(reply),
				contentType : "application/json; charset=utf-8",
				dataType : "json"
			}).done(function(response) {
				alert("댓글 수정 성공");
				location.reload();
			}).fail(function(error) {
				alert("댓글 수정 실패");
				location.reload();
			});
			
		});
		
		$("button[name='replyDelete']").on("click", function(e) {

			e.preventDefault();
			var replyId = $(this).data("idx");
			var userId = $(this).data("idx1");

			$.ajax({
				type : "DELETE",
				url : "/auth/reply/" + replyId + "/" + userId,
				contentType : "application/json; charset=utf-8",
				dataType : "json"
			}).done(function(response) {
				alert("삭제 되었습니다");
				location.reload();
			}).fail(function(error) {
				alert("올바르지 않은 경로 입니다");
				location.reload();
			});

		});
		
		$("button[name='replyUpButton']").on("click", function(e) {

			e.preventDefault();
			var replyId = $(this).data("idx");
			var userId = '${principal.user.id}';
			
			var replyUpDown = {
					reply: {
					    id: replyId
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
					url: "/auth/reply/up",
					data : JSON.stringify(replyUpDown),
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
		
		$("button[name='replyDownButton']").on("click", function(e) {

			e.preventDefault();
			var replyId = $(this).data("idx");
			var userId = '${principal.user.id}';
			
			var replyUpDown = {
					reply: {
					    id: replyId
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
					url: "/auth/reply/down",
					data : JSON.stringify(replyUpDown),
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

	});
</script>

<%@ include file="../layout/footer.jsp"%>