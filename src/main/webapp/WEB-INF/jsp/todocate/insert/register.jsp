<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<!DOCTYPE html>
<html lang="ko" xmlns="http://www.w3.org/1999/xhtml">
<c:import url="../../include/head.jsp"/> 
<c:import url="../../include/plugin_js.jsp"/>

<!-- category_ch -->
<script src="/js/category.js"></script>
<script>
history.pushState(null, null, location.href);
window.onpopstate = function(event) {
    history.go(1);
};
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
		  <h2 class="mb-2 text-center text-gray-800">TODO리킷 등록</h2>
		   <p class="text-center">나만의 TODOLIST 카테고리를 만들어보세요!</p>
		 </div>
		
			<!-- Page Content -->
	        <form action="/todocate/insert" id="sendForm" method="post" onsubmit="return false" >
				<div class="form-group">
					<label for="inputAddress" class="font-weight-bold py-1">TODO리킷(카테고리) 이름</label>
						<input type="text" class="form-control col-lg" id="todocateName" name="todocateName" value="" >
						 <small id="emailHelp" class="form-text text-muted py-1"> 1~15자 이내로 입력해주세요.</small>
						<div class="check_font" id="todocateName_check"></div>
					</div>
				 <div class="col text-center py-5">
				  <input type="hidden" class="class" name="todocateCode" id="todocateCode" value="<c:out value="${todocateCode}"/>"  >
				  <input type="hidden" class="class" name="todocateId" id="todocateId" value="<c:out value="${LoginVO.userId}"/>"  >
	                  <button type="button"  class="btn btn-info col-lg-12" onclick='insert_ch()'><h5>등 록</h5></button>
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