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
	
	// 거래처 수정
	$("#cusUpdate").on("click",function(){
		var check=$(this).is(":checked");
		if(check==false){
			alert("수정할 거래처를 체크해주세요.");
		}else{
			
		}
	});

	// 거래처 삭제
	$("#cusUpdate").click(function(){
		
	});
});