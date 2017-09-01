$(document).ready(function(){

	// 로그인
	$("#loginButton").click(function() {
		var logID = $("#loginID").val();
		var logPwd = $("#loginPwd").val();
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
							location.replace('login/logoutForm.jsp');
						} else {
							alert("로그인에 실패하였습니다.");
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
						alert("사용불가ID");
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

	// 회원가입 
	$("#join").click(function(){
		var memID=$("#memID").val();
		var memPwd=$("#memPwd").val();
		var memName=$("#memName").val();
		var memPhone=$("#memPhone").val();
		var memAddress=$("#memAddress").val();

		if((memID&&memPwd&&memName)=="") {
			alert("ID, 패스워드, 이름은 필수 입력입니다.");
		} 
		if(memPhone==""){
			memPhone = " ";
		}
		if(memAddress==""){
			memAddress = " ";
		}
		if($("#idcheck").val()==1){
			$.ajax({type:"POST",
				url: "../memberController?cmd=join",
				data: {'memID':memID, 'memPwd':memPwd,
					'memName':memName, 'memPhone':memPhone,
					'memAddress':memAddress},
					success:function(obj){
						var join=JSON.parse(obj).idCheck;
						if($.trim(join)=="false") {
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

	// 정보 조회
	$("#selectMember").click(function() {
		var memID=$("#memID").val();
		alert("정보조회 버튼 클릭");
		$.ajax({
			type:"POST",
			url:"../memberController?cmd=selectMember",
			data:{'memID':memID},
			success:function(obj) {
				var pwd=JSON.parse(obj).pwd;
				var name=JSON.parse(obj).name;
				var phone=JSON.parse(obj).phone;
				var address=JSON.parse(obj).address;
				$("#memPwd").val(pwd);
				$("#memName").val(name);
				$("#memPhone").val(phone);
				$("#memAddress").val(address);
			},
			error:function(request,status,error){   
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			}
		});
	});

	// 정보 수정
	$("#modify").click(function() {
		var memID=$("#memID").val();
		var memPwd=$("#memPwd").val();
		var memName=$("#memName").val();
		var memPhone=$("#memPhone").val();
		var memAddress=$("#memAddress").val();

		if((memPwd&&memName)=="") {
			alert("패스워드, 이름은 필수 입력입니다.");
		} else {
			$.ajax({type:"POST",
				url: "../memberController?cmd=updateMember",
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

	// 회원 탈퇴
	$("#memberdelete").click(function() {
		var memID=$("#memID").val();
		var memPwd=$("#memPwd").val();
		if(memPwd=="") {
			alert("탈퇴하시려면 비밀번호를 입력해주세요.");
		}else if(memPwd!=null){
			$.ajax({type:"POST",
				url: "../memberController?cmd=deleteMember",
				data: {'memID':memID, 'memPwd':memPwd},
				success:function(data){
					if($.trim(data)=="yes") {
						alert("탈퇴가 완료되었습니다.");
						location.replace('../index.jsp');
					} else {
						alert("비밀번호를 정확히 입력해주세요.");
					}
				}
			});
		}
	});

	// 전체회원조회
	$("#memberPrint").click(function(){
		$.ajax({
			type:"POST",
			url:"../memberController?cmd=selectAllMember",
			dataType:"json",
			cache:false,
			success:function(data){
				$("#con").html(""); // div를 일단 공백으로 초기화해줌 , 왜냐면 버튼 여러번 눌리면 중첩되니깐
				$("<table/>").css({ // 테이블 생성
					backgroundColor : "#ffffff",
					border : "solid 3px #E4F7BA",

				}).appendTo("#con"); // 테이블을 생성하고 그 테이블을 div에 추가함
				var key =  Object.keys(data["memberlist"][0]); // id , pw , addr , tel 의 키값을 가져옴
				$("<tr>" , {

					html : "<td>" + key[3] + "</td>"+  // 컬럼명들
					"<td>" + key[2] + "</td>"+
					"<td>" + key[1] + "</td>"+
					"<td>" + key[0] + "</td>"
				}).appendTo("table") // 이것을 테이블에붙임
				$.each(data.memberlist, function(index, memberlist) { // 이치를 써서 모든 데이터들을 배열에 넣음	
					var items = [];
					items.push("<td>" + memberlist.memID + "</td>"); // 여기에 id pw addr tel의 값을 배열에 넣은뒤
					items.push("<td>" + memberlist.name + "</td>");
					items.push("<td>" + memberlist.phone + "</td>");
					items.push("<td>" + memberlist.address + "</td>");
					$("<tr/>", {
						html : items // 티알에 붙임,
					}).appendTo("table"); // 그리고 그 tr을 테이블에 붙임
				});//each끝
			}
		});
	});
});