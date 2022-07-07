<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<!DOCTYPE html>
<html>
<c:import url="../../include/head.jsp"/> 
<c:import url="../../include/plugin_js.jsp"/>

<!-- moment JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js"></script>

<!-- todo JS-->
<script src="/js/todo.js"></script>
<script>
$(document).ready(function(){
	todocate_load();
	todotime_load();
});
</script>

<body id="page-top">
<!-- Page Wrapper -->
<div id="wrapper">
	
 <!-- Content Wrapper -->
 <div id="content-wrapper" class="bg-white">

	<!-- Main Content -->
	<div id="content">
	
	 <!-- Topbar -->
	 <c:import url="../../include/topbar.jsp"/> 
		<!-- Begin Page Content -->
		<div class="container-fluid col-6">
		
         <!-- Page Heading -->
         <div class="py-5">
		  <h2 class="mb-2 text-center text-gray-800">TODO리킷 - 할일 추가</h2>
		   <p class="text-center">할일을 효율적으로 관리해보세요!</p>
		 </div>
		
			<!-- Page Content -->
			<c:if test="${fail != null }">
				<div class="text-center">
					<div class="alert alert-info" role="alert"><c:out value="${fail}"/>
						<a href="<c:url value='/todocate/insert/todocate'/>" class="d-none d-sm-inline-block btn btn-info shadow-sm"> TODO리킷 등록</a>
					</div>
				</div>
			</c:if>
			
			<c:if test="${success != null }">
	        <form action="/todo/insert" id="sendForm" method="post" onsubmit="return false">
				<div class="form-group">
					<label for="inputCateCode" class="font-weight-bold py-1">TODO리킷(카테고리)</label>
					 <select class="form-control" id="cateCode" name="cateCode">
					 	<option value="">카테고리를 선택하세요.</option>
					 </select>
					<div class="check_font" id="todoName_check"></div>
				</div>
				
				<div class="form-group">
					<label for="inputTodoTitle" class="font-weight-bold py-1">할일 제목</label>
						<input type="text" class="form-control col-lg" id="todoTitle" name="todoTitle" value="할일테스트 제목" >
						 <small id="todoTitleHelp" class="form-text text-muted py-1"> 1~15자 이내로 입력해주세요.</small>
						<div class="check_font" id="todoTitle_check"></div>
				</div>
				
				<div class="form-group">
					<label for="inputTodoContent" class="font-weight-bold py-1">할일 내용</label>
						<input type="text" class="form-control col-lg" id="todoContent" name="todoContent" value="할일 테스트 내용" >
						 <small id="todoContentHelp" class="form-text text-muted py-1">1~100자 이내로 입력해주세요.</small>
						<div class="check_font" id="todoContent_check"></div>
				</div>
				<div class="form-group">
					<label for="inputTododay" class="font-weight-bold py-1">일정 설정</label>
					<small id="tododayHelp" class="form-text text-muted py-1"> 날짜를 입력해주세요.(숫자만 입력 가능합니다. 예시: 20220629)</small>
	                <input type="text" class="form-control col-lg" id="tododay" name="tododay" value="" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');">
	                <div class="check_font" id="tododay_check"></div>

		        </div>

		        <div class="form-group">
					<small id="emailHelp" class="form-text text-muted py-1"> 시간을 선택해주세요.</small>
                	<select class="form-control" id="todotime" name="todotime" >
					 	<option value="">시간을 선택하세요.(선택안함)</option>
					</select>
					<div class="check_font" id="todotime_check"></div>
                </div>
         
				 <div class="col text-center py-5">
				  <input type="hidden" class="class" name="userId" id="todocateId" value="<c:out value="${LoginVO.userId}"/>">
				  <input type="hidden" class="class" name="todoId" id="todoId" value="<c:out value="${todoId}"/>">
	                  <button type="button" id="saveBtn" class="btn btn-info col-lg-12" onclick='insert_ch()'><h5>할일 추가</h5></button>
	             </div>
	         </form>
	         </c:if>
	        </div> <!-- End Page Content -->
              
         <!-- Footer -->
         <c:import url="../../include/footer.jsp"/> 
		
       </div> <!-- End of Main Content -->
   </div> <!-- End of Content Wrapper -->
</div> <!-- End of Page Wrapper -->

<!-- Scroll to Top Button-->
 <c:import url="../../include/scrollTop_button.jsp"/> 
	
</body>
</html>