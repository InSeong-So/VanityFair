<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<html>
<head>
	<title>Editor</title>
	<script type="text/javascript" src="${pageContext.request.contextPath}/webjars/jquery/3.3.1/dist/jquery.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			
			var boardNo = "${param.boardNo}";
			
			$("#view").hide();
			/* 
				SITE : https://ckeditor.com
				TYPE : BASIC > STANDARD > FULL
			*/
			var CDN_FULL = "https://cdn.ckeditor.com/4.7.3/full-all/ckeditor.js";
			var CDN_STANDARD = "https://cdn.ckeditor.com/4.9.2/standard/ckeditor.js";
			var CDN_BASIC = "https://cdn.ckeditor.com/4.9.2/basic/ckeditor.js";
			$.getScript(CDN_FULL).done(function() {
		          if (CKEDITOR.instances['edit']) {
		              CKEDITOR.instances['edit'].destroy(); /* 기존 CKEDITOR 종료 */
		          }
		          /* CKEDITOR 생성*/
		          CKEDITOR.replace('edit', {
		        	  customConfig: '${pageContext.request.contextPath}/resources/js/config.js',
		        	  filebrowserUploadUrl: '${pageContext.request.contextPath}/fileUpload'
		          });
		      });
			$("#save").on("click", function(){
				// 데이터 가져오기
				var boardTitle = $("#boardTitle").val();
				var boardContents = CKEDITOR.instances['edit'].getData();
				var boardClass = $("#boardClass").val();
				// 데이터 초기화 하기
				$("#boardTitle").val("");
				CKEDITOR.instances['edit'].setData("");
				
				// 서버로 데이터 전송하기
				$.ajax({
					url : "insert",
					data : {"boardTitle" : boardTitle, "boardContents" : boardContents, "boardClass" : boardClass},
					type : "POST"
				}).done(function(data){
					var d = JSON.parse(data);
					// 결과값 화면 처리하기
					var boardTitle = d.boardTitle;
					var boardContents = d.boardContents;
					console.log(d);
					$("#input").hide();
					$("#view").show();
					$("#viewTitle").empty().html(boardTitle);
					$("#viewContents").empty().html(boardContents);
				});
			});
			
			$("#load").on("click", function(){
				var boardTitle = $("#viewTitle").text();
				var boardContents = $("#viewContents").html();
				$("#view").hide();
				$("#input").show();
				$("#boardTitle").val(boardTitle);
				CKEDITOR.instances['edit'].setData(boardContents);
			});
			
			$("#back").on("click", function(){
				location.href="/sis";
			});
			
			$("#back2").on("click", function(){
				location.href="/sis";
			});
		});
	</script>
</head>
<body>
	<section id="input">
		<h1>글 작성</h1>
		<input type="text" id="boardTitle" name="boardTitle" placeholder="제목을 입력하세요.">
		<select name="boardClass" id="boardClass">
			<c:if test="${user.userNo != 1}">
				<option value="9">고객의견</option>
			</c:if>
			
			<c:if test="${user.userNo == 1}">
				<option value="1">공지사항</option>
				<option value="2">가입문의</option>
				<option value="3">예약문의</option>
				<option value="4">결제문의</option>
				<option value="5">쿠폰문의</option>
				<option value="6">탈퇴문의</option>
				<option value="7">고객의견</option>
			</c:if>
		</select><br>
		<hr>
		<textarea id="edit" name="edit"></textarea>
		<button type="button" id="save">작성</button>
		<button type="button" id="back">돌아가기</button>
	</section>
	
	<section id="view">
		<h1>상세 화면</h1>
		<h2 id="viewTitle"></h2>
		<hr>
		<p id="viewContents"></p>
		<button type="button" id="load">수정</button>
		<button type="button" id="back2">목록으로</button>
		<a href="boardDelete?boardNo=${param.boardNo}">삭제</a>
	</section>
</body>
</html>
