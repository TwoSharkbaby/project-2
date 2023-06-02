$(document).ready(function() {
	
	//아이디 정규식
	var idJ = /^[a-z0-9]{4,12}$/;
	// 비밀번호 정규식
	var pwJ = /^[A-Za-z0-9]{4,12}$/; 
	// 닉네임 정규식
	var nicknameJ = /^(?=.*[a-z0-9가-힣])[a-z0-9가-힣]{2,16}$/;
	// 이메일 검사 정규식
	var mailJ = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
	
	var inval_Arr = new Array(6).fill(false);
	var regForm = $('#regForm');
	var idCheck = false;
	var nicknameCheck = false;
	
	$("#userCheck").on("click", function(e) {
		e.preventDefault();
		
		// 아이디 정규식
		if (idJ.test($('#username').val())) {
			inval_Arr[0] = true;	
		} else {
			inval_Arr[0] = false;
		}
		// 비밀번호가 같은 경우 && 비밀번호 정규식
		if (($('#password').val() == ($('#password1').val()))
				&& pwJ.test($('#password').val())) {
			inval_Arr[1] = true;
		} else {
			inval_Arr[1] = false;
		}
		// 닉네임 정규식
		if (nicknameJ.test($('#nickname').val())) {
			inval_Arr[2] = true;	
		} else {
			inval_Arr[2] = false;
		}
		// 이메일 정규식
		if (mailJ.test($('#email').val())){
			inval_Arr[3] = true;
		} else {
			inval_Arr[3] = false;
		}
		// 아이디 중복 체크
		if (idCheck) {
			inval_Arr[4] = true;
		} else {
			inval_Arr[4] = false;
			alert("아이디 중복 확인을 눌러주세요 :)");
		}
		// 닉네임 중복 체크
		if (nicknameCheck) {
			inval_Arr[5] = true;
		} else {
			inval_Arr[5] = false;
			alert("닉네임 중복 확인을 눌러주세요 :)");
		}
		var validAll = true;
		for(var i = 0; i < inval_Arr.length; i++){
			
			if(inval_Arr[i] == false){
				validAll = false;
			}
		}
		
		if(validAll){ // 유효성 모두 통과
			regForm.submit();
		} else{
			alert('입력한 정보들을 다시 한번 확인해주세요 :)')
		}	 
		
	});
	$('#idCheck').on("click", function(e) {
		e.preventDefault();
		
		var user = $('#username').val();
		
		$.ajax({
			type : "GET",
			url : "/idCheck/" + user,
			contentType : "application/json; charset=utf-8", 
			dataType : "json" 
			}).done(function(response) { 
				if(response == false){
					idCheck = true;
					alert("사용 가능한 아이디 입니다 :)");
				} else {
					alert("이미 사용중인 아이디 입니다 :)");
				}
			}).fail(function(error) {
				alert("올바른 경로가 아닙니다 :)");
			});
	});
	
	$('#nicknameCheck').on("click", function(e) {
		e.preventDefault();
		
		var nickname = $('#nickname').val();
		
		$.ajax({
			type : "GET",
			url : "/nicknameCheck/" + nickname,
			contentType : "application/json; charset=utf-8", 
			dataType : "json"
			}).done(function(response) { 
				if(response == false){
					nicknameCheck = true;
					alert("사용 가능한 닉네임 입니다 :)");
				} else {
					alert("이미 사용중인 닉네임 입니다 :)");
				}
			}).fail(function(error) {
				alert("올바른 경로가 아닙니다");
			});
	});
	
	$('#username').change(function(){
		idCheck = false;
	});
	
	$('#nickname').change(function(){
		nicknameCheck = false;
	});
	
	$('#username').blur(function(){
		if(idJ.test($(this).val())){
			$("#checkText").text('');
		} else {
			$('#checkText').text('아이디를 확인해주세요 :)');
			$('#checkText').css('color', 'red');
		}
	});
	
	$("#password").blur(function(){
		if(pwJ.test($("#password").val())){
			$("#checkText").text("");
		} else {
			$("#checkText").text("숫자 or 문자로만 4~12자리 입력 :)");
			$("#checkText").css("color", "red");
		}
	});
	
	$("#password1").blur(function(){
		if($("#password").val() != $(this).val()){
			$("#checkText").text("비밀번호가 일치하지 않습니다 :)");
			$("#checkText").css("color", "red");
		} else {
			$("#checkText").text("");
		}
	});
	
	
	$("#nickname").blur(function() {
		if (nicknameJ.test($(this).val())) {
			$("#checkText").text('');
		} else {
			$('#checkText').text('닉네임을 확인해주세요 :)');
			$('#checkText').css('color', 'red');
		}
	});
	
	$('#email').blur(function(){
		if(mailJ.test($(this).val())){
			$("#checkText").text('');
		} else {
			$('#checkText').text('이메일을 확인해주세요 :)');
			$('#checkText').css('color', 'red');
		}
	});
	
});