<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
	<title>글 수정</title>
	<meta charset="UTF-8">
	<script type="text/javascript" src="webjars/jquery/3.3.1/dist/jquery.min.js"></script>
	<script>

		$(document).ready(function(){
			var boardNo = "${param.boardNo}";
			var userNo = "${param.userNo}";
			var status = "${status}";
			console.log("status :" + status );
			if(status == 1){
				alert("접근했습니다.");
				$("form").submit(function(e){
					e.preventDefault();
					$.ajax({
						type : "post",
						url : "boardUpdate",
						data : {
							"boardNo" : boardNo,
							"boardTitle" : $("form input").eq(0).val(),
							"boardContents" : $("form input").eq(1).val()
						}
					}).done(function(data){
						var d = JSON.parse(data);
						if(d.status == 1){
							location.href="boardSelect?boardNo=" + d.boardNo;
							alert("수정하였습니다.");
						}
					});
				});
			} else {
				alert("접근 권한이 없습니다.");
				location.href="/sis/resources/main.html";
			}
		});
	</script>
</head>
<body>
	<h1>글 수정</h1>
	<form action="">
		<input type="text" name="boardTitle" placeholder="제목을 입력하세요."><br>
		<input type="text" name="boardContents" placeholder="내용을 입력하세요."><br>
		<input type="submit" value="글수정">
	</form>
</body>
</html>
