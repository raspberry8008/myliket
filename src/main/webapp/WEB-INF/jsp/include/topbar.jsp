<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>

 <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">
 
 <c:if test="${LoginVO  == null }">
 
 	<!-- Navbar Brand-->
     <a class="navbar-brand ps-5" href="/login"> <img alt="myliket" src="/img/myliket_benner.png"></a>

   </c:if>
   
  <c:if test="${LoginVO  != null }">
 	<!-- Navbar Brand-->
     <a class="navbar-brand ps-5" href="/home"> <img alt="myliket" src="/img/myliket_benner.png"></a>

	     <!-- Topbar Navbar -->
	     <ul class="navbar-nav ml-auto">
	
	         <!-- Nav Item - User Information -->
	         <li class="nav-item dropdown no-arrow">
	             <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
	                 data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	                 <span class="mr-2 d-none d-lg-inline text-gray-600">안녕하세요, <c:out value="${LoginVO.userName}"/>님</span>
	                 <img class="img-profile rounded-circle" src="/img/<c:out value="${LoginVO.userImage}"/>">
	                 <input type="hidden" class="class" name="userId" id="userId" value="<c:out value="${LoginVO.userId}"/>">
	             </a>
	             <!-- Dropdown - User Information -->
	             <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
	                 aria-labelledby="userDropdown">
	                 <a class="dropdown-item" href="/logout" data-toggle="modal" data-target="#logoutModal">
	                     <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
	                     Logout
	                 </a>
	             </div>
	         </li>
    	 </ul>
   </c:if>
 </nav>
 <!-- End of Topbar -->
  
  <!-- Logout Modal-->
   <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
       aria-hidden="true">
       <div class="modal-dialog" role="document">
           <div class="modal-content">
               <div class="modal-header">
                   <h5 class="modal-title" id="exampleModalLabel">Logout</h5>
                   <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                       <span aria-hidden="true">×</span>
                   </button>
               </div>
               <div class="modal-body">로그아웃 하시겠습니까?</div>
               <div class="modal-footer">
                   <button class="btn btn-secondary" type="button" data-dismiss="modal">취소</button>
                   <a class="btn btn-info" href="/login/logout">로그아웃</a>
               </div>
           </div>
       </div>
   </div>
