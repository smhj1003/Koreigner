	var setCookie = function(name, value, exp) {
		var date = new Date();
		date.setTime(date.getTime() + exp*60*60*1000);
		document.cookie = name + '=' + value + ';expires=' + date.toUTCString() + ';path=/';
	};

//로그인 처리 함수
function loginBt() {
	var inputId = $('#mem_id').val();
	var inputPw = $('#mem_pw').val();
	var inputCate = $('input[name=mem_cate]:checked').val();
	var autoLogin = $('input[name=autoLogin]:checked').val();
	var jsonObj = {"inputId":inputId, "inputPw":inputPw, "inputCate":inputCate, "autoLogin":autoLogin};
	
	$.ajax({
		url : 'login.do',
		type : 'POST',
		contentType: "application/json; charset=UTF-8",
		data : JSON.stringify(jsonObj),
		dataType : 'text',
		success : function(tokenStr){
			if(tokenStr !='fail'){
				// 로그인 성공
				sessionStorage.setItem("tokenStr", tokenStr);
				
				setCookie("userToken", tokenStr, 1);
				
				if(autoLogin == 1) {
					setCookie("autoCookie", tokenStr, 24*7);
				}
				
				alert("Hello there! :)"+ "\n" + "Move to the MainPage");
				
				location.href='main.do';
				
			} else if(tokenStr ==='fail'){
				// 로그인 실패
				if(inputId == "" || inputPw == ""){
					alert('Please fill in the blank :(');
					
				} else if(inputCate == null) {
					alert("Please check the type of what you're going to sign in!");
					
				} else {
					alert("There is no such user :(" + "\n" + "Please check your Email or Password");
					$("#mem_id").val("");
					$("#mem_pw").val("");   
				}
			}
		},
		error: function(){
			alert("로그인 에러");
		}
	});
}
	            	

