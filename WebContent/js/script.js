$(document).ready(function(){
	// 로그인
	$("#adminLogin").click(function() {
		var logID = $("#adID").val();
		var logPwd = $("#adPass").val();
		if(logID==""){
			alert("ID를 입력해주세요.");
		} else if(logPwd==""){
			alert("비밀번호를 입력해주세요.");
		} else { 
			$.ajax({type:"POST",
				url:"memberController?cmd=login",
				data:{"memID":logID,
					"memPwd":logPwd},				
					success:function(data){
						if(data!=null) {
							alert(data+"님 환영합니다.");
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
		if(memID==""){
			alert("ID를 입력해주세요");
		}else if(memPwd==""){
			alert("비밀번호를 입력해주세요.");
		}else if(memName==""){
			alert("이름을 입력해주세요.");
		}else if(memPhone==""){
			alert("핸드폰 번호를 입력해주세요.");
		}else{
			$.ajax({type:"POST",
				url:"memberController?cmd=join",
				data:{"memID":memID,
					"memName":memName,
					"memPwd":memPwd,
					"memPhone":memPhone
					},				
					success:function(data){
						var join=JSON.parse(obj).idCheck;
						if($.trim(join)=="false") {
							alert("회원가입이 완료되었습니다.");
							location.replace('../index.jsp');
						} else {
							alert("회원가입 불가. 관리자에 문의하세요.");
						}
					}, error:function(request,status,error){
						alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
					}
			});
		}
	});
	
	// 거래처 수정
	/*$("#updateContent").click(function(){
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
					location.replace('../ex/cusListEx.jsp');
				}
			});
		}
	});*/
	
	$("#updateContent").click(function(){
		var result=confirm('거래처 정보를 수정 하시겠습니까?');
		var updateCus=$('form').serialize();
		console.log(updateCus);
		if(result) {
			$.ajax({type:"POST",
				url:"../CustomerController?cmd=updateCustomer",
				data:updateCus,
				success:function(data) {
					alert("거래처 정보가 수정되었습니다.");
					location.replace('../ex/cusListEx.jsp');
				}
			});
		}
	});

	// 거래처 삭제
	$("#deleteCus").click(function(){
		var result=confirm('거래처를 삭제 하시겠습니까?');
		var updateCus=$('form').serialize();
		if(result) {
			$.ajax({type:"POST",
				url:"../CustomerController?cmd=deleteCustomer",
				data:updateCus,
					success:function(data){
						alert("거래처가 삭제되었습니다.");
						location.replace('../ex/cusListEx.jsp');
					} 
			});
		};
	});
});