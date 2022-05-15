<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<!DOCTYPE html>
<html lang="ko" xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="../include/head.jsp" %>
<%@ include file="../include/plugin_js.jsp" %>
<body class="bg-gradient-success">
    <div class="container">

        <!-- Outer Row -->
        <div class="row justify-content-center">

            <div class="col-xl-10 col-lg-12 col-md-9">

                <div class="card o-hidden border-0 shadow-lg my-5">
                    <div class="card-body p-0">
                        <!-- Nested Row within Card Body -->
                        <div class="row">
                            <div class="col-lg-6 d-none d-lg-block bg-login-image"></div>
                            <div class="col-lg-6">
                                <div class="p-5">
                                    <div class="text-center">
                                        <h1 class="h2 text-gray-700 mb-4" >IMH ADMIN</h1>
                                        <p>For the things we have to learn before we can do them, we learn by doing them.</p>
                                    </div>
                                    <hr>
                                    <form class="user" action="/login" method="post">
                                        <div class="form-group">
                                        <P>아이디</P>
                                            <input type="text" class="form-control form-control-user"
                                                id="id" name="id" aria-describedby="emailHelp" placeholder="아이디">
                                        </div>
                                        <div class="form-group">
                                        <P>비밀번호</P>
                                            <input type="password" class="form-control form-control-user"
                                                id="pw" name="pw" placeholder="비밀번호">
                                        </div>
                                        <button class="btn btn-success btn-user btn-block" type="submit" style="font-size: 15px"><b>로그인</b></button>
                                        <br>
										<div align="center" style="color: gray;">
											아직 회원이 아니신가요?<button type="button" class="btn btn-link" style="color: rgb(37, 214, 179);"
												onclick="location.href='/memberjoin'">회원가입</button>
										</div>
									</form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>