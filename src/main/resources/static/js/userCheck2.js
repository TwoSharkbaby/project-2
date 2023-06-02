$(document).ready(function() {
	
	// 비밀번호 정규식
	var pwJ = /^[A-Za-z0-9]{4,12}$/; 
	// 닉네임 정규식
	var nicknameJ = /^(?=.*[a-z0-9가-힣])[a-z0-9가-힣]{2,16}$/;
	// 이메일 검사 정규식
	var mailJ = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
	
	var inval_Arr = new Array(4).fill(false);
	var regForm = $('#regForm');
	var nicknameCheck = true;
	
	$("#userCheck").on("click", function(e) {
		e.preventDefault();
		
		// 비밀번호가 같은 경우 && 비밀번호 정규식
		if (($('#password').val() == ($('#password1').val()))
				&& pwJ.test($('#password').val())) {
			inval_Arr[0] = true;
		} else {
			inval_Arr[0] = false;
		}
		// 닉네임 정규식
		if (nicknameJ.test($('#nickname').val())) {
			inval_Arr[1] = true;	
		} else {
			inval_Arr[1] = false;
		}
		// 이메일 정규식
		if (mailJ.test($('#email').val())){
			inval_Arr[2] = true;
		} else {
			inval_Arr[2] = false;
		}
		// 닉네임 중복 체크
		if (nicknameCheck) {
			inval_Arr[3] = true;
		} else {
			inval_Arr[3] = false;
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
	
	$('#nickname').change(function(){
		nicknameCheck = false;
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