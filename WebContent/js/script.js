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
	$("#updateContent").click(function(){
		var result=confirm('거래처를 수정 하시겠습니까?');
		var num=$("#num").val();
		var cusCode=$("#cusCode").val();
		var cusName=$("#cusName").val();
		var cusRep=$("#cusRep").val();
		var licenseNum=$("#licenseNum").val();
		var cusNumber=$("#cusNumber").val();
		var cusAddress=$("#cusAddress").val();
		if(result){
			$.ajax({type:"POST",
				url:"../CustomerController?cmd=updateCustomer",
				data:{num:num,
					cusCode:cusCode,
					cusName:cusName,
					cusRep:cusRep,
					licenseNum:licenseNum,
					cusNumber:cusNumber,
					cusAddress:cusAddress
				},
				success:function(data) {
					alert("거래처 정보가 수정되었습니다.");
					location.replace('../index.jsp');
				}
			});
		}
	});

	// 거래처 삭제dd
	$("#deleteCus").click(function(){
		var result=confirm('거래처를 삭제 하시겠습니까?');
		var cusCode=$("#cusCode").val();
		var num=$("#num").val();
		var pageNum=$("pageNum").val();
		if(result) {
			$.ajax({type:"POST",
				url:"../CustomerController?cmd=deleteCustomer",
				data:{num:num,
					cusCode:cusCode,
					pageNum:pageNum },
					success:function(data){
						alert("거래처가 삭제되었습니다.");
						location.replace('../index.jsp');
					}
			});
		};
	});
});