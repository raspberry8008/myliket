
	
function  insert_ch() {
	
	var todocateName = $('input[name=todocateName]').val();
	
	/*문자열 앞뒤 공백 제거*/
	var selectedVal = todocateName.trim();	

	if ($('#todocateName').val() == '') {
		$('#todocateName_check').text("이름을 입력해주세요.");
		$('#todocateName_check').css('color', 'red');
		return false;
	} else {
		$('#todocateName_check').text("");
	}
	
	if ( selectedVal.length < 1 || 15 < selectedVal.length) {
		$('#todocateName_check').text("1~15자리로 입력해주세요.");
		$('#todocateName_check').css('color', 'red');
		return false;
	} else {
		$('#todocateName_check').text("");
	}

	document.getElementById('sendForm').submit();
	
}

	
