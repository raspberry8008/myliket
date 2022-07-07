/* 완료 상태로 변경 후 페이지 새로고침 */
function create_check(){

  var selectedCode = $("#todoId").val();
  var selectedId = $("#userId").val();
  var selectedDay = $("#tododay").val();

     $.ajax({
         async: true,
         type : 'get',
         data : {	"todoId" : selectedCode
        		 , "userId" : selectedId
        		 , "tododay" : selectedDay},
         url : '/todo/complete/todo',
         dataType : 'json',
          contentType : 'application/json; charset=UTF-8',  
         success : function(data) {
        	 var msg = Object.values(data);
        	 if (msg=="success"){
        	 	alert('할일 완료 처리되었습니다.');
           		location.reload();
        	 } else {
        		 console.log("실패"); 
        	 }
    
			}, error : function() {
			console.log("실패");
			}       
	});
}

/* todocate load */
function todocate_load(){

  	var selectedVal = $("#todocateId").val();

       $.ajax({
           async: true,
           type : 'get',
           data : {"userId" : selectedVal},
           url : '/todocate/list/todocate',
           dataType : 'json',
            contentType : 'application/json; charset=UTF-8',  
           success : function(data) {	            		            	
           	var target =$("select[name='cateCode']");
           	console.log(Object.values(data));
           	if(data.lenght < 1){
           		targer.append('<option value="">등록된 카테고리가 없습니다.\</option>');

           	} else {
           		var laborOption = "";
           		for(var k in data){
           		  var obj = data[k];
           		console.log(obj);
           		  var todocateCode = obj.todocateCode; 
           		console.log(todocateCode);
           		  var todocateName = obj.todocateName;

           		  laborOption = "<option value='" + todocateCode + "'>" + todocateName + "</option>";
           		 
           		  $("#cateCode").append(laborOption);
           		} 
           	}

			}, error : function() {
					console.log("실패");
			}
		});
}

/* todotime load */
function todotime_load() {
	$("#todotime").click(function() {
		$("select[name='todotime'] option").remove();
		$("select[name='todotime']").append('<option value="">선택하세요.(선택안함)</option>');
		$("select[name='todotime']").append('<option value="060000">06:00</option>');
		$("select[name='todotime']").append('<option value="120000">12:00</option>');
		$("select[name='todotime']").append('<option value="180000">18:00</option>');
		$("select[name='todotime']").append('<option value="210000">21:00</option>');
	});
}

function insert_ch() {

    var regDay= RegExp(/[0-9]{8}$/);   

         
    var todoTitle =  $('#todoTitle').val();
    var todoContent =  $('#todoContent').val();

	var tododay =  $('#tododay').val();
	var todotime =  $('#todotime').val();
    
	/*오늘 날짜*/
    var today =moment().format("YYYY년MM월DD일");
    
    /*오늘 날짜 이전인지 확인*/
    var pastDay=moment(tododay, "YYYYMMDD").isBefore(moment().format("YYYYMMDD"));
	
	/*현재 시간*/
	var nowtime = parseInt(moment().format("HHmmss"));


	if ($('#cateCode').val() == '') {
		$('#todoName_check').text("카테고리를 선택하세요.");
		$('#todoName_check').css('color', 'red');
		return false;
	} else {
		$('#cateCode_check').text("");
	}
	
	if (todoTitle.trim() == '' || todoTitle.trim().length < 1 || 15 < todoTitle.trim().length) {
		$("#todoTitle").focus();
		$('#todoTitle_check').text("제목을 입력해주세요.(1~15자 이내)");
		$('#todoTitle_check').css('color', 'red');
		return false;
	} else {
		$('#todoTitle_check').text("");
	}	
	
	if (todoContent.trim() == '' || todoContent.trim().length < 1 || 101 < todoContent.trim().length) {
		$('#todoContent').focus();
		$('#todoContent_check').text("내용을 입력해주세요.(1~100자 이내)");
		$('#todoContent_check').css('color', 'red');
		return false;
	} else {
		$('#todoContent_check').text("");
	}

	
	if (tododay.trim() == '') {
		$('#tododay').focus();
		$('#tododay_check').text("날짜를 입력해주세요.( 숫자 8자리 - 예시 : 20220629)");
		$('#tododay_check').css('color', 'red');
		return false;
	} else if (!regDay.test(tododay)){
		$('#tododay').focus();
		$('#tododay_check').text("날짜를 8자리로 입력해주세요.");
		$('#tododay_check').css('color', 'red');
		return false;
	} else if (pastDay){
		$('#tododay').focus();
		$('#tododay_check').text("오늘 이전으로 등록할 수 없습니다.(오늘: "+ today +")");
		$('#tododay_check').css('color', 'red');
		return false;
	} else {
		$('#tododay_check').text("");
		
		if ($('#todotime').val() == '') {

			 if (confirm("시간 설정은 하지 않고 등록하시겠습니까?") == true){ 
  
			 } else {
				return false;
			 }

		} else {
		
			if (todotime < nowtime) {
				$('#todotime_check').text("현재시간 이전으로 등록할 수 없습니다.");
				$('#todotime_check').css('color', 'red');
				return false;
			} else {
				$('#todotime_check').text("");
			}
		}		
	}

	document.getElementById('sendForm').submit();
 } 