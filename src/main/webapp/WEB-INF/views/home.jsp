<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Editor</title>
	<script type="text/javascript" src="${pageContext.request.contextPath}/webjars/jquery/3.3.1/dist/jquery.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
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
				var title = $("#title").val();
				var content = CKEDITOR.instances['edit'].getData();
				
				// 데이터 초기화 하기
				$("#title").val("");
				CKEDITOR.instances['edit'].setData("");
				
				// 서버로 데이터 전송하기
				$.ajax({
					url : "insert",
					data : {"title" : title, "contents" : content},
					type : "POST"
				}).done(function(data){
					// 결과값 화면 처리하기
					var title = data.title;
					var contents= data.contents;
					$("#input").hide();
					$("#view").show();
					$("#viewTitle").empty().html(title);
					$("#viewContents").empty().html(contents);
				});
			});
			$("#load").on("click", function(){
				var title = $("#viewTitle").text();
				var contents = $("#viewContents").html();
				$("#view").hide();
				$("#input").show();
				$("#title").val(title);
				CKEDITOR.instances['edit'].setData(contents);
			});
		});
	</script>
</head>
<body>
	<section id="input">
		<h1>입력 화면</h1>
		<input type="text" id="title" name="title" placeholder="제목을 입력하세요.">
		<hr>
		<textarea id="edit" name="edit"></textarea>
		<button type="button" id="save">출력</button>
	</section>
	
	<section id="view">
		<h1>상세 화면</h1>
		<h2 id="viewTitle"></h2>
		<hr>
		<p id="viewContents"></p>
		<button type="button" id="load">수정</button>
	</section>
</body>
</html>
