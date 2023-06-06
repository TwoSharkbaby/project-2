<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>
<script src="/js/userCheck.js"></script>

<div class="content" style="height: 39em; display: flex; justify-content: center; align-items: center;">
	<form action="/join" method="post" id="regForm" style="background-color: white; border: 3em solid white; padding: 10px; border-radius: 5px;">
		<table class="table text-center">
			<thead>
				<h1 class="text-center mt-3 mb-3">회원가입</h1>
				<tr>
					<th scope="col" style="width: 14em; vertical-align: middle;">아이디</th>
					<td style="width: 25em;"><input type="text" class="form-control" name="username" id="username" placeholder="소문자 + 숫자 ( 4 ~ 12 자리 )"></td>
					<td><button class="btn btn-secondary" type="button" id="idCheck">중복확인</button></td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th scope="row" style="vertical-align: middle;">비밀번호</th>
					<td><input class="form-control" type="password" name="password" id="password" placeholder="영문자 + 숫자 ( 4 ~ 12 자리 )"></td>
					<td></td>
				</tr>
				<tr>
					<th scope="row" style="vertical-align: middle;">비밀번호 확인</th>
					<td><input class="form-control" type="password" id="password1"></td>
					<td></td>
				</tr>
				<tr>
					<th scope="row" style="vertical-align: middle;">닉네임</th>
					<td><input class="form-control" type="text" name="nickname" id="nickname" placeholder="2 ~ 16 자리"></td>
					<td><button class="btn btn-secondary" type="button" id="nicknameCheck">중복확인</button></td>
				</tr>
				<tr>
					<th scope="row" style="vertical-align: middle;">이메일</th>
					<td><input class="form-control" type="text" name="email" id="email"></td>
					<td></td>
				</tr>
			</tbody>
		</table>
		<div class="join_btn" style="display: flex; justify-content: center;">
			<h1 id="checkText"></h1>
		</div>
		<div class="join_btn mb-3" style="display: flex; justify-content: center;">
			<button style="width: 10em; height: 2.8em" class="btn btn-primary text-center" type="button" id="userCheck">가입하기</button>
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