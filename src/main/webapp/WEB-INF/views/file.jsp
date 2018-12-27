<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>File</title>
	<style>
		section {
			width: 70%;
			margin-left: 15%;
			padding: 10px;
		}		
		.filebox {display:inline-block; margin-right: 10px;}
		.filebox label {
		  display: inline-block;
		  padding: .1em .3em;
		  color: black;
		  background-color: #efefef;
		  cursor: pointer;
		  border-radius: .1em;
		  border: .6px solid black;
		}
		.filebox input[type="file"] {  /* 파일 필드 숨기기 */
		  position: absolute;
		  width: 1px;
		  height: 1px;
		  padding: 0;
		  margin: -1px;
		  overflow: hidden;
		  clip:rect(0,0,0,0);
		  border: 0;
		}
		#modal {
		  position:absolute;
		  background:rgba(0,0,0,0.2);
		  border-radius:4px;
		  padding:4px;
		}
		#content {
		  border-radius:2px;
		  background:#fff000;
		  padding:20px;
		}
		#close {
		  position:absolute;
		  background:url(resources/img/close.png) 0 0 no-repeat;
		  width:24px;
		  height:27px;
		  display:block;
		  text-indent:-9999px;
		  top:-7px;
		  right:-7px;
		}
	</style>
	<script type="text/javascript" src="${pageContext.request.contextPath}/webjars/jquery/3.3.1/dist/jquery.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			$("#modal").hide();
			var modal = {
					center : function () {
						  var top = Math.max($(window).height() - $("#modal").outerHeight(), 0) / 2;
						  var left = Math.max($(window).width() - $("#modal").outerWidth(), 0) / 2;
						  $("#modal").css({
						    top:top + $(window).scrollTop(), 
						    left:left + $(window).scrollLeft()
						  });
					},open : function (settings) {
						  $("#content").empty().append(settings.content);
						  $("#modal").css({
						    width: settings.width || 'auto', 
						    height: settings.height || 'auto'
						  })
						  modal.center();
						  $("#modal").show();
					},close : function () {
						  $("#modal").hide();
						  $("#content").empty();
					}
			}
			
			function readURL(input, name) {
		        var reader = new FileReader();
		        reader.onload = function (e) {
		        	var img = '<img src="'+ e.target.result +'" alt="'+ name +'"><br>';
		        	$("#file2View").append(img)
		        }
		        reader.readAsDataURL(input);
			}

			$("#file2").change(function() {
				  var files = $(this)[0].files;
				  var names = "";
				  $("#file2View").empty();
				  for(var i = 0; i < files.length; i++){
					  names += files[i].name + " ";
					  readURL(files[i], files[i].name);
				  }
				  setTimeout(function(){
					  $("#file2View img").off().on("click", function(){
							 var index = $("#file2View img").index(this);
							 var name = $("#file2View img").eq(index).attr("alt");
							 modal.open({content: name});
							  $("#close").off().on("click", function(e){
								  e.preventDefault();
								  modal.close();
								  $("#file2View img").eq(index).remove();
							  });
						  });
				  }, 100);
// 				  alert(names);
// 				  confirm(names);
// 				  if(names != ""){
// 					  modal.open({content: names});
// 					  $("#close").off().on("click", function(e){
// 						  e.preventDefault();
// 						  modal.close();
// 					  });
// 				  }
			});
		});
	</script>
</head>
<body>
	<section>
		<form action="" method="post" enctype="multipart/form-data">
			<h1>기본 UI</h1>
			<input type="file" id="file1"><input type="submit" value="저장">
		</form>
	</section>
	<hr>
	<section>
		<form action="" method="post" enctype="multipart/form-data">
			<h1>변경 UI</h1>
			<div class="filebox">
				<label for="file2">파일 선택</label> 
				<input type="file" id="file2" multiple="multiple"> 
			</div>
			<input type="submit" value="저장">
		</form>
		<div id="file2View"></div>
	</section>

	<!-- Modal -->
	<div id='modal'>
		<div id='content'></div>
		<a href='#' id='close'>close</a>
	</div>
</body>
</html>
