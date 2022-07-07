
function login_ch() {

	 var regEmail=/([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
	 var regPwd= RegExp(/^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,16}$/);

    /*아이디 공백확인*/
    if($("#userId").val() == ""){
    
      $('#userId_check').text("아이디를 입력해주세요.");
      $('#userId_check').css('color', 'red');
      $("#userId").focus();
      
      return false;
	      
    } 
    
    /* 아이디 이메일 형식 확인 */
    if (!regEmail.test($("#userId").val())){
	
		$('#userId').focus();
		$('#userId_check').text("이메일 형식으로 입력해주세요.");
		$('#userId_check').css('color', 'red');
		
		return false;
	}
	
    /* 비밀번호 공백확인 */
    if($("#userPwd").val() == ""){ 
    
	    $("#userPwd_check").text("비밀번호를 입력해주세요.");
	    $("#userPwd_check").css('color', 'red');
	    $("#userPwd").focus();
	    
	    return false;
   }
   
    /* 비밀번호 형식 확인 */
    if (!regPwd.test($("#userPwd").val())){
    
	    $("#userPwd_check").text("아이디와 비밀번호를 확인해주세요.");
	    $("#userPwd_check").css('color', 'red');
	    $("#userPwd").focus();
	    
	    return false;
   }
   
   document.getElementById('sendForm').submit();
} 