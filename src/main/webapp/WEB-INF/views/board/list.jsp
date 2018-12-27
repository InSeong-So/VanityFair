<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>리스트</title>
	<meta charset="UTF-8">
	<script type="text/javascript" src="/sis/webjars/jquery/3.3.1/dist/jquery.min.js"></script>
	<script>
		$(document).ready(function(){
			
				$.ajax({
					  type : "post",
					  url : "bbld"
				  }).done(function(data){
					  var d = JSON.parse(data)
					  console.log(d);
					  var list = d.list;
					  $("#list").empty();
					  for(var i = 0; i < list.length; i++){
						  var html = "<li>";
						  /****************************************************/
						  		html += "<a href='bSelect?boardNo=";
						  		html += list[i].boardNo;
						  		html += "'>";
						  		html += list[i].boardTitle;
						  		html += "</a>";
						  /****************************************************/
						  		html += "</li>";
						  $("#list").append(html);
					  }
				  });
			
		});
	</script>
</head>
<body>
	<h1><a href="/app">리스트</a></h1>
	<a href="bInsert">작성</a>
	<ul id="list"></ul>
</body>
</html>