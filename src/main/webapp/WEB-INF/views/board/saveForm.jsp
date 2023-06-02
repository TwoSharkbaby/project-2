<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>


<div class="content" style="height: 39em; display: flex; justify-content: center; align-items: center;">
	<div id="boardSave">
		<div style="display: flex; justify-content: center;">
			<h3 class="fw-bold text-center mt-2">게시글 작성</h3>
		</div>
		<form action="/auth/board/save" method="post" id="boardSave">
			<input type="hidden" name="nickname" value="${principal.user.nickname}" /> 
			<input type="hidden" name="user.id" value="${principal.user.id}" />
			<input type="hidden" name="role" value="${principal.user.role}" />
			<div class="mb-3">
				<label for="title" class="form-label fw-bold">제목</label> 
				<input type="text" class="form-control" id="title" name="title">
			</div>
			<div class="mb-3">
				<label for="content" class="form-label fw-bold">내용</label>
				<textarea rows="5" class="form-control summernote" id="content" name="content"></textarea>
			</div>
			<div style="display: flex; justify-content: center;">
				<button class="btn btn-primary text-center mt-4">등록하기</button>
			</div>
		</form>
	</div>

</div>

<script>
	$('.summernote').summernote(
			{
				tabsize : 2,
				height : 300,
				toolbar : [ [ 'style', [ 'style' ] ],
						[ 'font', [ 'bold', 'underline', 'clear' ] ],
						[ 'color', [ 'color' ] ],
						[ 'para', [ 'ul', 'ol', 'paragraph' ] ],
						[ 'table', [ 'table' ] ],
						[ 'insert', [ 'link', 'picture', 'video' ] ],
						[ 'view', [ 'fullscreen', 'codeview', 'help' ] ] ]
			});
</script>

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