<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page session="true" %>
<!DOCTYPE html>
<html>
<c:import url="include/head.jsp"/>
<c:import url="include/plugin_js.jsp"/>

<body id="page-top">
  <!-- Page Wrapper -->
   <div id="wrapper">
      
     <!-- Content Wrapper -->
     <div id="content-wrapper" class="d-flex flex-column">
       
       <!-- Main Content -->
       <div id="content">
        
        <!-- Topbar -->
  		<c:import url="include/topbar.jsp"/>
               
        <!-- Begin Page Content -->
        <div class="container-fluid">

            <!-- Page Heading -->
            <div class="d-sm-flex align-items-center justify-content-between mb-4">
                <h1 class="h3 mb-0 text-gray-800">Dashboard</h1>
                
                <div class="btn-group" role="group" aria-label="Basic example">
				    <a href="<c:url value='/todocate/insert/todocate'/>" class="d-none d-sm-inline-block btn btn-info shadow-sm">
                    	TODO리킷 등록</a> 
                    <a href="#" class="d-none d-sm-inline-block btn btn-outline-info shadow-sm" onclick="location.href='/todo/insert/todo'">
                    	 일정 추가</a> 
				</div>
            </div>
            
             <!-- todolist리킷 list  -->
			 <div class="card shadow mb-4">
			    <div class="card-header bg-white py-3">
			         <h5 class="m-0 font-weight-bold text-secondary"><i class="bi bi-calendar-check"></i> TODO 리킷</h5>
			         <small class="form-text text-muted py-1"> TODO리킷(카테고리) 목록 입니다.</small>
			    </div>
				    <div class="card-body">
				     
					<c:if test="${msg != null }">
						<div class="text-center">
							<div class="alert alert-light" role="alert">
								<c:out value="${msg}"/>
						    	<a href="<c:url value='/todocate/insert/todocate'/>" class="d-none d-sm-inline-block btn btn-info shadow-sm"> TODO리킷 등록</a>
							</div>
						</div>
					</c:if>
				
					<c:if test="${list != null }">
						<div class="table-responsive">
							<table class="table table-hover">
								<thead>
									<tr>
										<th class="text-center" scope="col"></th>
										<th class="text-center" scope="col">TODO리킷(카테고리)</th>
										<th class="text-center" scope="col">상세메뉴</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="TodocateVO" items="${list}">
										<tr>
											<td class="text-center col-1">
												<a class="text-secondary" href="#" onclick="location.href='/todo/list/ready?todocateCode=<c:out value="${TodocateVO.todocateCode}"/>'" > 
												<i class="bi bi-caret-down-fill"></i></a>
												</td>
											<td class="text-center">
												<a class="text-secondary" href="#" onclick="location.href='/todo/list/ready?todocateCode=<c:out value="${TodocateVO.todocateCode}"/>'" > 
												<c:out value="${TodocateVO.todocateName}"/> <span class="badge badge-secondary"> <c:out value="${TodocateVO.todoCount}"/></span></a>
											</td>
											<td class="text-center">
												<a class="dropdown" href="#" id="todolistDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
								                 <i class="fas fa-ellipsis-v fa-sm fa-fw text-gray-400"></i> </a>
								             <!-- Dropdown - User Information -->
								             <div class="dropdown-menu " aria-labelledby="todolistDropdown">
								                 <a class="dropdown-item" href="#" onclick="location.href='/todocate/update/todocate?todocateCode=<c:out value="${TodocateVO.todocateCode}"/>'" >
								                    <i class="bi bi-calendar-fill fa-sm fa-fw mr-2 text-gray-400"></i> 수정</a>
								                 <a class="dropdown-item btn-modal" href="#" data-toggle="modal" data-target="#todolistDeleteModal" data-id="<c:out value="${TodocateVO.todocateCode}"/>">
								                    <i class="bi bi-calendar-x-fill fa-sm fa-fw mr-2 text-gray-400"></i> 삭제</a>
								             </div>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</c:if>
				 </div>
			</div> <!-- end todolist리킷 list-->
            
			<!-- Dashboard -->
             <!-- 미리알림  -->
             <div class="card shadow mb-4">
                 <div class="card-header bg-white py-3">
                     <h5 class="m-0 font-weight-bold text-secondary"><i class="bi bi-alarm"></i> 미리알림</h5>
                     <small class="form-text text-muted py-1"> 일정을 미리 확인해보세요. 오늘 하루도 화이팅!</small>
                 </div>
                 <div class="card-body">
                     <div class="row">
                     
                      <!-- 오늘일정 Card -->
                        <div class="col-xl-3 col-md-6 mb-4">
                            <div class="card border-left-info h-100 py-2">
                                <div class="card-body">
                                    <div class="row no-gutters align-items-center">
                                        <div class="col mr-2">
                                            <div class="text-xs font-weight-bold text-info text-uppercase mb-1">오늘할일</div>
                                            <div class="h5 mb-0 font-weight-bold text-gray-800">
                                            <a class="text-secondary" href="#" onclick="location.href='/todo/list/today'"><c:out value="${TodoTotalVO.todayReadyCnt}"/>건</a></div>
                                        </div>
                                        <div class="col-auto">
                                            <i class="fas fa-calendar fa-2x text-gray-300"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- 지난일정 Card -->
                        <div class="col-xl-3 col-md-6 mb-4">
                            <div class="card border-left-warning h-100 py-2">
                                <div class="card-body">
                                    <div class="row no-gutters align-items-center">
                                        <div class="col mr-2">
                                            <div class="text-xs font-weight-bold text-warning text-uppercase mb-1">
                                                지난할일</div>
                                            <div class="h5 mb-0 font-weight-bold text-gray-800">
                                            <a class="text-secondary" href="#" onclick="location.href='/todo/list/past'"><c:out value="${TodoTotalVO.pastReadyCnt}"/>건</a></div>
                                        </div>
                                        <div class="col-auto">
                                            <i class="bi bi-calendar-x-fill fa-2x text-gray-300"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        
                        <!-- 예정일정 Card -->
                        <div class="col-xl-3 col-md-6 mb-4">
                            <div class="card border-left-primary h-100 py-2">
                                <div class="card-body">
                                    <div class="row no-gutters align-items-center">
                                        <div class="col mr-2">
                                            <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">예정할일</div>
                                            <div class="h5 mb-0 font-weight-bold text-gray-800">
                                            	<a class="text-secondary" href="#" onclick="location.href='/todo/list/before'"><c:out value="${TodoTotalVO.beforeReadyCnt}"/>건</a>
                                            </div>
                                        </div>
                                        <div class="col-auto">
                                            <i class="bi bi-calendar-week-fill fa-2x text-gray-300"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div> <!-- end 미리알림-->
            
            <!-- todo완료 -->
            <div class="card shadow mb-4">
                <div class="card-header bg-white py-3">
                   <h5 class="m-0 font-weight-bold text-secondary"><i class="bi bi-calendar-check"></i> TODO 완료</h5>
                    <small class="form-text text-muted py-1"> 완료한 TODO 입니다! 앞으로도 화이팅!</small>
                </div>
                <div class="card-body">
                     
                    <div class="row">                
                       
                       <!-- 오늘완료 Card -->
                       <div class="col-xl-3 col-md-6 mb-4">
                           <div class="card border-left-success h-100 py-2">
                               <div class="card-body">
                                   <div class="row no-gutters align-items-center">
                                       <div class="col mr-2">
                                           <div class="text-xs font-weight-bold text-success text-uppercase mb-1">
                                               오늘완료</div>
                                           <div class="h5 mb-0 font-weight-bold text-gray-800"><c:out value="${TodoTotalVO.todaycomCnt}"/>건</div>
                                       </div>
                                       <div class="col-auto">
                                           <i class="bi bi-calendar2-check-fill fa-2x text-gray-300"></i>
                                       </div>
                                   </div>
                               </div>
                           </div>
                       </div>
                       
                       <!-- 총완료건 Card -->
                       <div class="col-xl-3 col-md-6 mb-4">
                           <div class="card border-left-secondary h-100 py-2">
                               <div class="card-body">
                                   <div class="row no-gutters align-items-center">
                                       <div class="col mr-2">
                                           <div class="text-xs font-weight-bold text-secondary text-uppercase mb-1">
                                               지금까지 완료</div>
                                           <div class="h5 mb-0 font-weight-bold text-gray-800"><c:out value="${TodoTotalVO.totalcomCnt}"/>건</div>
                                       </div>
                                       <div class="col-auto">
                                           <i class="bi bi-calendar2-heart-fill fa-2x text-gray-300"></i>
                                       </div>
                                   </div>
                               </div>
                           </div>
                       </div>
                       
                    </div>
                </div>
            </div> <!-- end todo완료 ->

            <!-- /.container-fluid -->
           </div>

            </div>
            <!-- End of Main Content -->

            <!-- Footer -->
            <c:import url="include/footer.jsp"/>
            <!-- End of Footer -->

        </div>
        <!-- End of Content Wrapper -->

    </div>
    <!-- End of Page Wrapper -->

    <!-- Scroll to Top Button-->
    <c:import url="include/scrollTop_button.jsp"/>
    
  <!-- todolistDelete Modal-->
   <div class="modal fade" id="todolistDeleteModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
       aria-hidden="true">
       <div class="modal-dialog" role="document">
       <form action="/todocate/delete" method="post">
           <div class="modal-content">
               <div class="modal-header">
                   <h5 class="modal-title" id="exampleModalLabel">TODO리킷 삭제</h5>
                   <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                       <span aria-hidden="true">×</span>
                   </button>
               </div>
               <div class="modal-body">TODO리킷을 삭제하시겠습니까?</div>
                <input type="hidden" class="class" name="todocateCode" id="todocateCode" value="<c:out value="${TodocateVO.todocateCode}"/>">
                <input type="hidden" class="class" name="todocateId" id="todocateId" value="<c:out value="${LoginVO.userId}"/>">
               <div class="modal-footer">
                   <button class="btn btn-secondary" type="button" data-dismiss="modal">취소</button>
                   <button class="btn btn-info" type="submit" >삭제</button>
               </div>
           </div>
           </form>
       </div>
   </div>    
</body>

<script>
/*todolistDelete modal*/
$(".btn-modal").click(function(){
	var data = $(this).data('id');
    $("#todocateCode").val(data);
});
</script>    
</html>