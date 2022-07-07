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

<!-- todo JS-->
<script src="/js/todo.js"></script>

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
		  <h2 class="mb-2 text-center text-gray-800">TODO리킷 - 지난 할일 조회</h2>
		   <p class="text-center">놓친 할일을 확인하세요!</p>
		 </div>
		 
	 		<div class="text-right py-3">
		      <div class="btn-group" role="group" aria-label="Basic example">
			    <a href="<c:url value='/todocate/insert/todocate'/>" class="d-none d-sm-inline-block btn btn-info shadow-sm">
	                  	TODO리킷 등록</a> 
	                  <a href="#" class="d-none d-sm-inline-block btn btn-outline-info shadow-sm" onclick="location.href='/todo/insert/todo'">
	                  	 일정 추가</a> 
			 </div>
			</div>
		
			<!-- Page Content -->
			<c:if test="${fail != null }">
				<div class="text-center">
					<div class="alert alert-info" role="alert"><c:out value="${fail}"/>
					</div>	 
				</div>
			</c:if>
			

			
			<c:if test="${success != null }">
				<div class="table-responsive">
	         		<table class="table table-hover">
						<thead>
							<tr>
								<th class="text-center" scope="col">카테고리</th>
								<th class="text-center" scope="col">완료체크</th>
								<th class="text-center" scope="col">제목</th>
								<th class="text-center" scope="col">날짜</th>
								<th class="text-center" scope="col">시간</th>
								<th class="text-center" scope="col">상세메뉴</th>
							</tr>
						</thead>
							<tbody>
							<c:forEach var="TodoVO" items="${success}">
								<tr>
									<td class="text-center"><c:out value="${TodoVO.cateName}"/></td>
									
									<td class="text-center">
									    <div class="form-check">
									      <input class="form-check-input" type="checkbox" name="todoId" id="todoId" value="<c:out value="${TodoVO.todoId}"/>" onclick="create_check()">
									    </div>
									</td>
									
									<td class="text-center">
										<a class="text-secondary" href="#" onclick="location.href='/todo/detail/todo?todoId=<c:out value="${TodoVO.todoId}"/>'">
										<c:out value="${TodoVO.todoTitle}"/></a>
									</td>
									<td class="text-center">
									 <fmt:parseDate value="${TodoVO.tododay}" pattern="yyyyMMdd" var="date"/>
                   					  <fmt:formatDate pattern="yyyy년MM월dd일" type="both" value="${date}"/>
									 <input type="hidden" class="class" name="tododay" id="tododay" value="<c:out value="${TodoVO.tododay}"/>" >
									</td>
									<td class="text-center"><c:out value="${TodoVO.todotime}"/></td>
									<td class="text-center">
										<a class="dropdown" href="#" id="todoDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						                	 <i class="fas fa-ellipsis-v fa-sm fa-fw text-gray-400"></i> </a>
						        	     <!-- Dropdown - todoDetail Menu -->
							             <div class="dropdown-menu " aria-labelledby="todoDropdown">
							                 <a class="dropdown-item" href="#" onclick="location.href='/todo/update/todo?todoId=<c:out value="${TodoVO.todoId}"/>'">
							                    <i class="bi bi-calendar-fill fa-sm fa-fw mr-2 text-gray-400"></i> 수정</a>
							                 <a class="dropdown-item btn-modal" href="#" data-toggle="modal" data-target="#todoDeleteModal" 
							                 data-todoid="<c:out value="${TodoVO.todoId}"/>" data-todoDay="<c:out value="${TodoVO.tododay}"/>" data-userid="<c:out value="test@myliket.com"/>">
							                    <i class="bi bi-calendar-x-fill fa-sm fa-fw mr-2 text-gray-400"></i> 삭제</a>
							             </div>
									</td>
								</tr>
							</c:forEach>
						<tbody>
					</table>
				 </div>
			  </c:if>
	       </div> <!-- End Page Content -->
	        
	              
         <!-- Footer -->
         <c:import url="../../include/footer.jsp"/> 
		
       </div> <!-- End of Main Content -->
   </div> <!-- End of Content Wrapper -->
</div> <!-- End of Page Wrapper -->


 <!-- todolistDelete Modal-->
  <div class="modal fade" id="todoDeleteModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
      aria-hidden="true">
      <div class="modal-dialog" role="document">
      <form action="/todo/delete" method="post">
          <div class="modal-content">
              <div class="modal-header">
                  <h5 class="modal-title" id="exampleModalLabel">할일 삭제</h5>
                  <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                      <span aria-hidden="true">×</span>
                  </button>
              </div>
              <div class="modal-body">할일을 삭제하시겠습니까?</div>
   
              <input type="hidden" class="class" name="todoId" id="todoid" value="">
               <input type="hidden" class="class" name="userId" id="userid" value="">
               <input type="hidden" class="class" name="tododay" id="todoDay" value="">
              <div class="modal-footer">
                  <button class="btn btn-secondary" type="button" data-dismiss="modal">취소</button>
                  <button class="btn btn-info" type="submit" >삭제</button>
              </div>
          </div>
          </form>
      </div>
  </div>    

<!-- Scroll to Top Button-->
 <c:import url="../../include/scrollTop_button.jsp"/> 
	
</body>
<script>
/*todolistDelete modal*/
$(".btn-modal").click(function(){
	var todoid = $(this).data('todoid');
    $("#todoid").val(todoid);
    
	var tododay = $(this).data('tododay');
    $("#todoDay").val(tododay); 
    
	var userid = $(this).data('userid');
    $("#userid").val(userid); 
});
</script>   
</html>