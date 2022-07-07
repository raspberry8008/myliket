<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page session="true" %>
<!DOCTYPE html>
<html>
<c:import url="../../include/head.jsp"/> 
<c:import url="../../include/plugin_js.jsp"/>

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
			 <h2 class="mb-2 text-center text-gray-800">TODO리킷 등록 실패</h2>
			</div>
	
			 <!-- Page Content -->
			<div class="text-center">
			
				<div class="alert alert-danger" role="alert"><c:out value="${fail}"/></div>
				<div class="alert alert-light" role="alert">TODO리킷을 등록을 다시 시도해 주시기 바랍니다.</div>
				
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
			 <h2 class="mb-2 text-center text-gray-800">TODO리킷 등록 완료</h2>
			 <hr>
			</div>
		
			 <!-- Page Content -->
			<div class="text-center">
				<div class="alert alert-info" role="alert"><c:out value="${success}"/><br>
				</div>
				
				<div class="alert alert-light" role="alert">TODO리킷의 할일을 추가해보세요!</div>
				<div class="btn-group" role="group" aria-label="Basic example">
  					<a href="<c:url value='/todo/insert/todo'/>" class="btn btn-info">할일 추가</a> 
  					<a href="<c:url value='/home'/>" class="btn btn-outline-info">홈으로</a> 
				</div>			</div>
			</c:if>

	       </div> <!-- End Page Content -->
	        
	              
         <!-- Footer -->
         <c:import url="../../include/footer.jsp"/> 
		
       </div> <!-- End of Main Content -->
   </div> <!-- End of Content Wrapper -->
</div> <!-- End of Page Wrapper -->

</body>
</html>