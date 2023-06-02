<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>


<div class="content" style="height: 39em; display: flex; justify-content: center; align-items: center;">
	<form action="/admin/filmmaker/saveForm" method="post" id="regForm" style="background-color: white; border: 3em solid white; padding: 10px; border-radius: 5px;" class="mt-5">
		<input type="hidden" name="editor" value="${principal.user.nickname}"/>
		<input type="hidden" name="user.id" value="${principal.user.id}"/>
		<input type="hidden" name="movie.id" value="${movieId}"/>
		<table class="table text-center">
			<thead>
				<h1 class="text-center mt-3 mb-3">감독 / 배우 등록</h1>
				<tr>
					<th scope="col" style="width: 14em; vertical-align: middle;">이름</th>
					<td style="width: 25em;"><input type="text" class="form-control" name="name" id="name"></td>
					<td></td>
				</tr>
			</thead>
			<tbody>
							<tr>
					<th scope="col" style="width: 14em; vertical-align: middle;">출생일</th>
					<td style="width: 25em;"><input type="date" class="form-control" name="birth" id="birth"></td>
					<td></td>
				</tr>
				<tr>
					<th scope="col" style="width: 14em; vertical-align: middle;">성별</th>
					<td style="width: 25em;"><input type="text" class="form-control" name="sex" id="sex"></td>
					<td></td>
				</tr>
				<tr>
					<th scope="col" style="width: 14em; vertical-align: middle;">캐스팅</th>
					<td style="width: 25em;"><input type="text" class="form-control" name="role" id="role"></td>
					<td></td>
				</tr>
				<tr>
					<th scope="col" style="width: 14em; vertical-align: middle;">정보</th>
					<td style="width: 25em;"><textarea rows="4" class="form-control" name="info" id="info"></textarea></td>
					<td></td>
				</tr>

				<tr>
					<th scope="col" style="width: 14em; vertical-align: middle;">이미지</th>
					<td style="width: 25em;"><input type="text" class="form-control" name="img" id="img"></td>
					<td></td>
				</tr>
			</tbody>
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