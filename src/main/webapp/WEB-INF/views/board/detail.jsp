<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<%-- <%	String boardNo = request.getParameter("boardNo");%> --%>
<html>
<head>
	<title>상세보기</title>
	<meta charset="UTF-8">
	<script type="text/javascript" src="webjars/jquery/3.3.1/dist/jquery.min.js"></script>
	<script>
		$(document).ready(function(){
			var boardNo = "${param.boardNo}";
			var userNo = "${param.userNo}";
			$("#view").hide();
			
			 $.ajax({url:"/sis/session"}).done(function(data){
				 var e = JSON.parse(data);
				 
				 if(e != null){
					$("#view").show();
					$.ajax({
						  type : "post",
						  url : "boardDetail",
						  data : {"boardNo" : boardNo, "userNo" : userNo}
					  }).done(function(data){
						  var d = JSON.parse(data)
						  var boardData = d.boardData;
						  console.log(boardData);
						  boardHTML(boardData);
					  });
				 } else {
					alert("로그인해주세요.");
					location.href="/sis";
				 }
			 });
		});
		
		function boardHTML(data){
			console.log("boardHTML", data);
			var boardTitle = data.boardTitle;
			var boardContents = data.boardContents;
			$("#boardTitle").text(boardTitle);
			$("#boardContents").text(boardContents);
		}
	</script>
</head>
<body>
	<h1 id="boardTitle"></h1>
	<p id="boardContents"></p>
	<div id="view">
		<a href="/sis/resources/main.html">목록</a>
		<a href="boardCheck?boardNo=${param.boardNo}&userNo=${param.userNo}">수정</a>
		<a href="boardDelete?boardNo=${param.boardNo}">삭제</a>
	</div>
</body>
</html>
