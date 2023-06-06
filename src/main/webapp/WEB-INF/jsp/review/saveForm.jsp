<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>


<div class="content" style="height: 39em; display: flex; justify-content: center; align-items: center;">
	<form action="/auth/review/save" method="post" id="regForm" style="background-color: white; border: 3em solid white; padding: 10px; border-radius: 5px;" class="mt-5">
		<input type="hidden" name="nickname" value="${principal.user.nickname}" /> 
		<input type="hidden" name="user.id" value="${principal.user.id}" /> 
		<input type="hidden" name="movie.id" value="${movie}" />
		<table class="table text-center">
			<h1 class="text-center  mb-3">리뷰 등록</h1>
			<tr class="mb-0">
				<th scope="col" style="width: 14em; vertical-align: middle;">평점</th>
				<td style="width: 25em;"><div id="myform" class="mb-4">
						<fieldset>
							<input type="radio" name="scorePoint" value="5" id="rate1"><label for="rate1">⭐</label> <input type="radio" name="scorePoint" value="4" id="rate2"><label for="rate2">⭐</label>
							<input type="radio" name="scorePoint" value="3" id="rate3"><label for="rate3">⭐</label> <input type="radio" name="scorePoint" value="2" id="rate4"><label for="rate4">⭐</label>
							<input type="radio" name="scorePoint" value="1" id="rate5"><label for="rate5">⭐</label>
						</fieldset>
					</div></td>
			</tr>
			<tr>
				<th scope="col" style="width: 14em; vertical-align: middle;">리뷰 제목</th>
				<td style="width: 25em;"><input type="text" class="form-control" name="title" id="title"></td>
				<td></td>
			</tr>

			<tr>
				<th scope="col" style="width: 14em; vertical-align: middle;">리뷰 내용</th>
				<td style="width: 25em;"><textarea rows="9" class="form-control" name="content" id="content"></textarea></td>
				<td></td>
			</tr>
		</table>
		<div class="join_btn" style="display: flex; justify-content: center;">
			<h1 id="checkText"></h1>
		</div>
		<div class="join_btn" style="display: flex; justify-content: center;">
			<button style="width: 10em; height: 2.8em" class="btn btn-primary text-center" type="submit" id="userCheck">등록하기</button>
		</div>
	</form>
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