<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>


<div class="content" style="height: 39em; display: flex; justify-content: center; align-items: center;">
	<div class="text-center mt-5" style="margin-top: 5em;">
		<main class="form-signin w-100 m-auto b-5" style="background-color: white; border: solid 4em white; border-radius: 10px;">
			<form class="mt-5" action="/login" method="post" id="regForm">
				<div class="form-floating">
					<input type="text" name="username" class="form-control" id="floatingInput" placeholder="name@example.com"> <label for="floatingInput">아이디</label>
				</div>
				<div class="form-floating">
					<input type="password" name="password" class="form-control" id="floatingPassword" placeholder="Password"> <label for="floatingPassword">비밀번호</label>
				</div>
				<button class="w-100 btn btn-lg btn-primary mt-3 mb-3">로그인</button>
			</form>
			<ul style="list-style: none; margin-left: -2em" class="mt-2">
				<li class="mb-2"><a href="/joinForm"><button class="btn btn-secondary fw-bold" style="width: 16.5em; height: 2.8em">일반 회원가입</button></a></li>
				<li class="mb-2"><a href="/oauth2/authorization/google"><img width="267em" src="/image/구글.png"></a></li>
				<li class="mb-2"><a href="/oauth2/authorization/facebook"><img width="266em" src="/image/페이스북.png"></a></li>
				<li class="mb-2"><a href="/oauth2/authorization/naver"><img width="266em" src="/image/네이버.png"></a></li>
				<li class="mb-2"><a href="/oauth2/authorization/kakao"><img width="266em" src="/image/카카오.png"></a></li>
			</ul>
		</main>
	</div>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		var regForm = $('#regForm');
		$(".btn-primary").on("click", function(e) {
			e.preventDefault();
			if (!regForm.find("input[name='username']").val()) {
				alert("아이디를 입력해주세요!");
				return false;
			}
			if (!regForm.find("input[name='password']").val()) {
				alert("비밀번호를 입력해주세요!");
				return false;
			}
			regForm.submit();
		});

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