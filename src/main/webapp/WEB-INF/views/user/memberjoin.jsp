<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<!DOCTYPE html>
<html lang="ko" xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="../include/head.jsp" %>
<%@ include file="../include/plugin_js.jsp" %>

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
	// 우편주소 찾기 API
    function sample6_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    document.getElementById("sample6_extraAddress").value = extraAddr;
                
                } else {
                    document.getElementById("sample6_extraAddress").value = '';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample6_postcode').value = data.zonecode;
                document.getElementById("userAddress").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("sample6_detailAddress").focus();
            }
        }).open();
    }	//우편주소 찾기 API end.
    
	// 유효성 검사
	function check() {
		var form = document.memberjoin;
		
		var id = form.userId.value;
		var pw = form.userPassword.value;
		var pw1 = form.userPassword1.value;
		var name = form.userName.value;
		var nikName = form.userNickname.value;
		var phone = form.userPhone.value;
		var address = form.userAddress.value;
		
		//아이디
		if(id == ""){
			alert("아이디를 입력해주세요.\n아이디는 이메일 형식으로 기입해주세요.\nex) example@email.com");
			form.userId.focus();
			return false;
		} else if(id.indexOf(" ") >= 0){
			alert("아이디에는 공백이 들어가면 안됩니다.");
			form.userPassword1.focus();
			return false;
		}

		//비밀번호
		if(pw == ""){
			alert("비밀번호를 입력해주세요.");
			form.userPassword.focus();
			return false;
		} else if(pw.length < 4){
			alert("비밀번호는 4자리 이상 입력해주세요.");
			form.userPassword.focus();
			return false;
		} else if(pw.indexOf(" ") >= 0){
			alert("비밀번호에는 공백이 들어가면 안됩니다.");
			form.userPassword.focus();
			return false;
		} else if(pw1 == ""){
			alert("비밀번호에는 공백이면 안됩니다.");
			form.userPassword1.focus();
			return false;
		} else if(pw != pw1){
			alert("비밀번호가 일치하지 않습니다.\n다시 입력해주세요");
			form.userPassword1.focus();
			return false;
		}
		
		//그 외
		//이름
		if(name == ""){
			alert("이름을 입력해주세요.");
			form.userName.focus();
			return false;
		}
		//닉네임
		if(nikName == ""){
			alert("닉네임을 입력해주세요.");
			form.userNickname.focus();
			return false;
		} else if(nikName.length < 2 || nikName.length > 16){
			alert("닉네임은 2~15자 이상입니다.");
			form.userNickname.focus();
			return false;
		}
		//연락처
		if(phone == ""){
			alert("연락처를 입력해주세요.");
			form.userPhone.focus();
			return false;
		}
		//주소
		if(address == ""){
			alert("상세주소를 입력해주세요.");
			form.userAddress.focus();
			return false;
		}
	}
	//아이디 중복확인
    $(function idCheck(){
        var responseMessage = "<c:out value="${message}" />";
        if(responseMessage != ""){
            alert(responseMessage)
        }
    })
   
</script>

<body class="bg-gradient-success" style="margin-left: 16%">
    <div class="container">

        <!-- Outer Row -->
        <div class="row justify-content-center">

            <div class="col-xl-10 col-lg-12 col-md-9">

                <div class="card o-hidden border-0 shadow-lg my-5" style="width: 70%;">
                        <!-- Nested Row within Card Body -->
                                <div class="p-5">
                                    <div class="text-center">
                                        <h1 class="h2 text-gray-700 mb-4" >IMH REGISTER</h1>
                                        <p>Thank you for coming to register with Team.IMH.</p>
                                    </div>
                                    <hr>
                                    <form class="user" name="memberjoin" action="/memberjoin" method="post" onsubmit="return check()">
                                        <P>아이디</P>
		                                <div class="form-group row">
		                                    <div class="col-sm-6 mb-3 mb-sm-0">
	                                            <input type="text" class="form-control form-control-user" id="userId" name="userId" placeholder="example@email.com">
		                                    </div>
		                                    <div class="col-sm-6">
		                                    	<button type="button" style="color: white; background-color: rgb(37, 214, 179); font-size: 15px; border: none;" class="btn btn-success btn-user btn-block"
		                                    	 onclick="idCheck"><b>아이디 중복확인</b></button>
		                                    </div>
		                                </div>
                                        <div class="form-group">
	                                        <P>비밀번호</P>
                                            <input type="text" class="form-control form-control-user" id="userPassword" name="userPassword" placeholder="·····">
                                        </div>
                                        <div class="form-group">
	                                        <P>비밀번호 재확인</P>
                                            <input type="text" class="form-control form-control-user" id="userPassword1" name="userPassword1" placeholder="·····">
                                        </div>
                                        <div class="form-group">
	                                        <P>이름</P>
                                            <input type="text" class="form-control form-control-user" id="userName" name="userName" placeholder="Name">
                                        </div>
                                        <div class="form-group">
	                                        <P>닉네임</P>
                                            <input type="text" class="form-control form-control-user" id="userNickname" name="userNickname" placeholder="Nickname">
                                        </div>
                                        <div class="form-group">
	                                        <P>연락처</P>
                                            <input type="text" class="form-control form-control-user" id="userPhone" name="userPhone" placeholder="010 - OOOO - OOOO">
                                        </div>
                                        <P>주소</P>
		                                <div class="form-group row">
		                                    <div class="col-sm-6 mb-3 mb-sm-0">
	                                            <input type="text" class="form-control form-control-user" id="sample6_postcode" name="sample6_postcode" 
	                                            placeholder="우편번호" readonly="readonly">
		                                    </div>
		                                    <div class="col-sm-6">
		                                    	<button type="button" style="color: white; background-color: rgb(37, 214, 179); font-size: 15px; border: none;" class="btn btn-success btn-user btn-block"
		                                    	 onclick="sample6_execDaumPostcode()"><b>우편번호 찾기</b></button>
		                                    </div>
		                                </div>
                                        <div class="form-group">
	                                        <input type="text" class="form-control form-control-user" id="userAddress" name="userAddress" placeholder="주소" readonly="readonly">
                                        </div>
		                                <div class="form-group row">
		                                    <div class="col-sm-6 mb-3 mb-sm-0">
	                                            <input type="text" class="form-control form-control-user" id="sample6_detailAddress" name="sample6_detailAddress" 
	                                            placeholder="상세주소">
		                                    </div>
		                                    <div class="col-sm-6">
	                                            <input type="text" class="form-control form-control-user" id="sample6_extraAddress" name="sample6_extraAddress" 
	                                            placeholder="참고항목" readonly="readonly">
		                                    </div>
		                                </div>
                                        <button class="btn btn-success btn-user btn-block" type="submit" style="font-size: 20px"><b>가입하기</b></button>
                                    </form>
                                </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>