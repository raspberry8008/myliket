<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<!DOCTYPE html>
<html lang="ko" xmlns="http://www.w3.org/1999/xhtml">
<c:import url="../include/head.jsp"/>
<c:import url="../include/plugin_js.jsp"/>
<!--  login check -->
<script src="/js/login.js"></script>

<body id="page-top">
 <!-- Page Wrapper -->
	<div id="wrapper">

		<!-- Content Wrapper -->
		<div id="content-wrapper" class="bg-white">

			<!-- Main Content -->
			<div id="content">

				<!-- Topbar -->
				<c:import url="../include/topbar.jsp" />
				
				<!-- Begin Page Content -->
				<div class="container-fluid col-6">

					<div class="p-5">
						<div class="text-center">
							<img alt="myliket" src="../../img/myliket_benner.png">
						</div>
							<c:if test="${fail != null }">
							<hr>
								<div class="text-center">
									<div class="alert alert-info" role="alert"><c:out value="${fail}"/></div>
								</div>
							<hr>	
							</c:if>
				 		<form class="user" id="sendForm" action="/login/user" method="post" onsubmit="return false"> 
							<div class="form-group">
								<P>이메일</P>
								<input type="email"  class="form-control form-control-user" id="userId" name="userId" aria-describedby="emailHelp" placeholder="이메일" value="test@myliket.com" >
								<div class="check_font" id="userId_check"></div>
							</div>
							<div class="form-group">
								<P>비밀번호</P>
								<input type="password" class="form-control form-control-user" id="userPwd" name="userPwd" placeholder="비밀번호" value="test1234!">
									<div class="check_font" id="userPwd_check"></div>
							</div>
							<button class="btn btn-info btn-user btn-block" type="button" style="font-size: 15px" onclick='login_ch()'>
								<b>로그인</b>
							</button>
<!-- 							<p class="mt-5 mb-3 text-muted text-center">아이디찾기 | 비밀번호 찾기</p>
							<br>
							<div align="center" style="color: gray;">
								아직 회원이 아니신가요?
								<button type="button" class="btn btn-link" style="color: rgb(35, 163, 184);"
									>회원가입</button>
							</div> -->
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>