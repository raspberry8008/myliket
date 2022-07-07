<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<!DOCTYPE html>
<html>
<c:import url="../../include/head.jsp"/> 
<c:import url="../../include/plugin_js.jsp"/>

<!-- category_ch -->
<script src="../../js/category.js"></script>

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
		  <h2 class="mb-2 text-center text-gray-800">TODO리킷 수정</h2>
		   <p class="text-center">TODO리킷의 변경할 이름을 입력해주세요.</p>
		 </div>
		
			<!-- Page Content -->
	        <form action="/todocate/update" id="sendForm" method="post">
				<div class="form-group">
					<label for="inputAddress" class="font-weight-bold py-1">수정할 TODO리킷(카테고리) 이름</label>
						<input type="text" class="form-control col-lg" id="todocateName" name="todocateName" value="<c:out value="${success.todocateName}"/>" >
						 <small id="emailHelp" class="form-text text-muted py-1"> 1~15자 이내로 입력해주세요.</small>
						<div class="check_font" id="todocateName_check"></div>
						
	 		            <input type="hidden" class="class" name="todocateCode" id="todocateCode" value="<c:out value="${success.todocateCode}"/>">
	 		            <input type="hidden" class="class" name="todocateId" id="todocateId" value="<c:out value="${LoginVO.userId}"/> ">
					</div>
				 <div class="col text-center py-5">
	                  <button type="button" id="saveBtn" class="btn btn-info col-lg-12" onclick='insert_ch()'><h5>수 정</h5></button>
	             </div>
	         </form>
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