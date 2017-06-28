$(document).ready(function(){
	// 로그인
	$("#adminLogin").click(function() {
		var logID = $("#adID").val();
		var logPwd = $("#adPass").val();
		var logName;
		if(logID==""){
			alert("ID를 입력해주세요.");
		} else if(logPwd==""){
			alert("비밀번호를 입력해주세요.");
		} else { 
			$.ajax({type:"POST",
				url:"memberController?cmd=login",
				data:{"memID":logID,
					"memPwd":logPwd,
					"name":logName},				
					success:function(data){
						if(data=="yes") {
							alert("로그인 되었습니다.");
							location.replace('ex/cusListEx.jsp');
						} else {
							alert("아이디와 비밀번호를 확인해주세요.");
						}
					}, error:function(request,status,error){
						alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
					}
			});
		}
	});

	// 로그아웃
	$("#idLogout").click(function(){
		var result=confirm('로그아웃 하시겠습니까?');
		if(result) {
			$.ajax({type:"POST",
				url:"../memberController?cmd=logout",
				success:function(data){
					alert(data);
					location.replace('../index.jsp');
				}
			});
		};
	});
	
	// 회원가입
	$("#join").click(function(){
		var memID=$("#memID").val();
		var memPwd=$("#memPwd").val();
		var memName=$("#memName").val();
		var memPhone=$("#memPhone").val();

		if((memID&&memPwd&&memName)=="") {
			alert("ID, 패스워드, 이름은 필수 입력입니다.");
		} 
		if(memPhone==""){
			memPhone = " ";
		}
		
		if($("#idcheck").val()==1){
			$.ajax({type:"POST",
				url: "../memberController?cmd=join",
				data: {'memID':memID, 'memPwd':memPwd,
					'memName':memName, 'memPhone':memPhone},
					success:function(data){
						if($.trim(data)=="yes") {
							alert("회원가입이 완료되었습니다.");
							location.replace('../index.jsp');
						} else {
							alert("회원가입 불가. 관리자에 문의하세요.");
						}
					}
			});
		} else {
			alert("ID 중복체크를 하세요");
		}

	});
	
	// 회원가입시 ID 중복체크
	$("#idCheck").click(function(){
		var memID=$("#memID").val();
		if(memID=="") {
			alert("ID를 입력해주세요");
		} else {
			$.ajax({type:"POST",
				url: "../memberController?cmd=idCheck",
				data: {'memID' : memID},
				success:function(data){
					if($.trim(data)=="no") {
						alert("이 ID는 이미 사용중입니다.");
						$("#idcheckbox").prop('checked', false);
						$("#idcheck").val(0);
					} else {
						alert("사용가능ID");
						$("#idcheckbox").prop('checked', true);
						$("#idcheck").val(1);
					}
				}
			});
		}
	});

	// 중복체크박스 false 고정
	$("#idcheckbox").click(function(){ 
		return false;
	});
	
	// 거래처 수정
	$("#modify").click(function() {
		var cusCode=$("#cusCode").val();
		var memPwd=$("#memPwd").val();
		var memName=$("#memName").val();
		var memPhone=$("#memPhone").val();
		var memAddress=$("#memAddress").val();

		if((memPwd&&memName)=="") {
			alert("패스워드, 이름은 필수 입력입니다.");
		} else {
			$.ajax({type:"POST",
				url: "../memberController?cmd=updateCustomer",
				data: {'memID':memID, 'memPwd':memPwd,
					'memName':memName, 'memPhone':memPhone,
					'memAddress':memAddress},
					success:function(data){
						if($.trim(data)=="yes") {
							alert("수정이 완료되었습니다.");
							location.replace('../login/indexLogout.jsp');
						}
					}
			});
		}
	});

});