<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<script type="text/javascript">

$('#cpActive').on('click', function(){
    var userId = $("#userId").val();
    var coCode = $("#cpCost").val();
    
    var paramData = {
			  "userId" : '${coupon.userId}',
			  "cpCode" : '${coupon.cpCode}'
	  };
    $.ajax({
        url: '${pageContext.request.contextPath}/coupon/cpActive',
        type: 'POST',
        data: 'json',
        success: function(result){
        	alert("쿠폰이 등록되었습니다.");
        },
        error: function(){
            alert("등록 실패");
        }
    });
});

</script>
						<form action="/coupon/cpActive/" method="post" role="form" >
							<input type="hidden" id="userId" name="userId" value="user1">
							<input type="hidden" id="cpCode" name="cpCode" value="2">
							<button type="submit" class="btn btn-primary">임시생성</button>
						</form>	
</html>