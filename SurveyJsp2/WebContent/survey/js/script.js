function idCheck(id){
	if(id == ""){
		alert("아이디를 입력해주세요.");
		document.regForm.id.focus();
	}
	else{
		var popWidth = 300;
		var popHeight = 200;
		var winHeight = document.body.clientHeight;		
		var winWidth = document.body.clientWidth;		
		var winX = window.screenLeft;					
		var winY = window.screenTop;					
		var popX = winX + (winWidth - popWidth) /2;
		var popY = winY + (winHeight - popHeight) /2;
		url = "idCheck.jsp?id="+id;
		window.open(url, "post", "left="+popX+",top="+popY+",width="+popWidth+",height="+popHeight);
	}
}

function passcheck(){
	if(!document.myForm.password.value){
		alert("비밀번호를 입력하지 않았습니다.");
		document.myForm.password.focus();
		return false;
	}
}

function inputCheck(){
	console.log(document.title);
	if(document.regForm.id.value == ""){
		alert("아이디를 입력해 주세요.");
		document.regForm.id.focus();
		return;
	}
	if(document.regForm.password.value == ""){
		alert("비밀번호를 입력해 주세요.");
		document.regForm.password.focus();
		return;
	}
	if(document.regForm.repassword.value == ""){
		alert("비밀번호를 확인해 주세요.");
		document.regForm.repassword.focus();
		return;
	}
	if(document.regForm.password.value != document.regForm.repassword.value){
		alert("비밀번호가 일치하지 않습니다.");
		document.regForm.repassword.focus();
		return;
	}
	if(document.regForm.email1.value == "" || document.regForm.email2.value == ""){
		alert("이메일을 입력해 주세요.");
		document.regForm.email1.focus();
		return;
	}
	
	document.regForm.submit();
}

function updateCheck(){
	if(document.regForm.password.value == ""){
		alert("비밀번호를 입력해 주세요.");
		document.regForm.password.focus();
		return;
	}
	if(document.regForm.repassword.value == ""){
		alert("비밀번호를 확인해 주세요.");
		document.regForm.repassword.focus();
		return;
	}
	if(document.regForm.password.value != document.regForm.repassword.value){
		alert("비밀번호가 일치하지 않습니다.");
		document.regForm.repass.focus();
		return;
	}
	if(document.regForm.email1.value == ""){
		alert("이메일을 입력해 주세요.");
		document.regForm.email.focus();
		return;
	}
	
	document.regForm.submit();
}

function writeSave(){
	if(document.writeForm.name.value == ""){
		alert("작성자를 입력하세요.");
		document.writeForm.name.focus();
		return false;
	}
	if(document.writeForm.title.value == ""){
		alert("제목을 입력하세요.");
		document.writeForm.title.focus();
		return false;
	}
	if(document.writeForm.content.value == ""){
		alert("내용을 입력하세요.");
		document.writeForm.content.focus();
		return false;
	}
	
	document.writeForm.submit();
}

function articleCheck(){
	var inputTag = document.surveyForm.getElementsByTagName('input');
	for(var i = 0; i < inputTag.length; i++){
		if(inputTag[i].type == 'radio'){
			if(radioCheck(inputTag[i]) == false){
				alert("입력하지 않은 항목이 있습니다.");
				return false;
			}
		}
	}
	document.surveyForm.submit();
}

function radioCheck(tag){
	var radioName = tag.name;
	var chk_radio = document.getElementsByName(radioName);
	var box = null;
	
	for(var i = 0; i < chk_radio.length; i++){
		if(chk_radio[i].checked == true){
			box = chk_radio[i].value;
		}
	}
	if(box == null){
		return false;
	}
}

function fileDown(num){
	var id = document.getElementById("ifrm_file");
	id.src = "downFile.do?num=" + num;
}

function fileDelete(num){
	var id = document.getElementById("ifrm_file");
	id.src = "deleteFile.do?num=" + num;
	var input = document.createElement("input");
	input.type = "file";
	input.name = "uploadfile";
	var filetd = document.getElementById("filetd")
	while(filetd.firstChild){
		filetd.removeChild(filetd.firstChild);
	}
	filetd.appendChild(input);
}