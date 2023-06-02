<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>


<div class="content" style="height: 39em; display: flex; justify-content: center; align-items: center;">
	<form action="/admin/movie/save" method="post" id="regForm" style="background-color: white; border: 3em solid white; padding: 10px; border-radius: 5px;" class="mt-5">
		<input type="hidden" name="editor" value="${principal.user.nickname}"/>
		<input type="hidden" name="user.id" value="${principal.user.id}"/>
		<table class="table text-center">
			<thead>
				<h1 class="text-center mt-3 mb-3">영화 등록</h1>
				<tr>
					<th scope="col" style="width: 14em; vertical-align: middle;">제목</th>
					<td style="width: 25em;"><input type="text" class="form-control" name="title" id="title"></td>
					<td></td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th scope="col" style="width: 14em; vertical-align: middle;">감독</th>
					<td style="width: 25em;"><input type="text" class="form-control" name="director" id="director"></td>
					<td></td>
				</tr>
				<tr>
					<th scope="col" style="width: 14em; vertical-align: middle;">장르</th>
					<td style="width: 25em;"><input type="text" class="form-control" name="genre" id="genre"></td>
					<td></td>
				</tr>
				<tr>
					<th scope="col" style="width: 14em; vertical-align: middle;">줄거리</th>
					<td style="width: 25em;"><textarea rows="4" class="form-control" name="synopsis" id="synopsis"></textarea></td>
					<td></td>
				</tr>
				<tr>
					<th scope="col" style="width: 14em; vertical-align: middle;">개봉일</th>
					<td style="width: 25em;"><input type="date" class="form-control" name="releaseDate" id="releaseDate"></td>
					<td></td>
				</tr>
				<tr>
					<th scope="col" style="width: 14em; vertical-align: middle;">상영시간</th>
					<td style="width: 25em;"><input type="text" class="form-control" name="runtime" id="runtime"></td>
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