<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<!DOCTYPE html>
<html>
<c:import url="../../include/head.jsp"/> 
<c:import url="../../include/plugin_js.jsp"/>
<script>
function update_ch() {
		document.getElementById('sendForm').submit();
	} 
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

			
		<c:if test="${fail != null }">
			 <!-- Page Heading -->
	        <div class="py-5">
			 <h2 class="mb-2 text-center text-gray-800">TODO리킷 - 할일 조회 실패</h2>
			</div>

			 <!-- Page Content -->
			<div class="text-center">
				<div class="alert alert-danger" role="alert"><c:out value="${fail}"/></div>
				<div class="alert alert-light" role="alert">할일 조회를 다시 시도해 주시기 바랍니다.</div>
				
				<hr>
				
				<div class="alert alert-light" role="alert">
					재시도를 하신 후에도 계속해서 같은 오류가 발생한다면<br>
					마이리킷 고객센터(1588-0614)로 문의 주시기 바랍니다.
				</div>
					
				<div class="btn-group" role="group" aria-label="Basic example">
					 <button type="button" class="btn btn-info"   onclick="history.back()">뒤로가기</button>
	 					<a href="<c:url value='/home'/>" class="btn btn-outline-info">홈으로</a> 
				</div>
			</div>
		</c:if>
			
		<c:if test="${success != null }">
		 <!-- Page Heading -->
         <div class="py-5">
		  <h2 class="mb-2 text-center text-gray-800">TODO리킷 - 할일 상세조회</h2>
		   <p class="text-center">등록된 할일을 확인해보세요!</p>
		   		
		 </div>
		 <!-- Page Content -->
	        <form action="/todo/update/todo" id="sendForm" method="get">
				<div class="form-group">
					<label for="inputAddress" class="font-weight-bold py-1">TODO리킷(카테고리)</label>
					<div class="alert alert-secondary"><c:out value="${success.cateName}"/></div>
				</div>
				
				<div class="form-group">
					<label for="inputAddress" class="font-weight-bold py-1">할일 제목</label>
					<div class="alert alert-secondary"><c:out value="${success.todoTitle}"/></div>
				</div>
				
				<div class="form-group">
					<label for="inputAddress" class="font-weight-bold py-1">할일 내용</label>
					<div class="alert alert-secondary"><c:out value="${success.todoContent}"/></div>
				</div>
				<div class="form-group">
					<label for="inputAddress" class="font-weight-bold py-1">날짜</label>
					<div class="alert alert-secondary"><c:out value="${success.tododay}"/></div>
		        </div>
		                
		                
		        <div class="form-group">
					<small id="emailHelp" class="form-text text-muted py-1"> 시간</small>
                	<div class="alert alert-secondary"><c:out value="${success.todotime}"/></div>
                </div>
                
                <input type="hidden" class="class" name="todoId" id="todoId" value="<c:out value="${success.todoId}"/>">
                
         
				 <div class="col text-center py-5">
	                  <button type="button" id="saveBtn" class="btn btn-info col-lg-12" onclick='update_ch()'><h5>수정</h5></button>
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