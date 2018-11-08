<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="model.Viw_boardBean" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width; initial-scale=1.0"/>


<link href="css/feed.css" rel="stylesheet">
<link href="css/search.css" rel="stylesheet">
<script src="js/search.js"></script>

<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

<title>Grang Feed</title>
<script type="text/javascript">
	var request=new XMLHttpRequest();
	function searchFunction(){
		request.open("Post","./search.act?hash="+encodeURIComponent(document.getElementById("hash").value),true);
		request.onreadystatechange=searchProcess;
		request.send(null);
	}
	function searchProcess(){
		
	}
	window.onload=function(){
		searchFunction();
	}

	$(function() {
	    $('.navbar-toggle').click(function(){
	      $(this).toggleClass('active');
	    }); 
	});
</script>
</head>
<body>
<!-- 
<form action="logOut" method="post">
	<input type="submit" value="로그아웃">
</form>

 -->
<div id="wrapper">

	<div class="header">
		<a href="myMenu.jsp" class="button menu"><img src="images/menubar.png"></a>
		<span class="title">Grang</span>
	</div>
	
	<!-- 피드방이 5개 이상부터는 화살표를 눌러서 새로운 리스트를 볼 수 있게 -->
	<!-- 
	<div class="feed_list">
		<div class="list"><img src="images/exit.png"></div>
		<div class="list"><img src="images/exit.png"></div>
		<div class="list"><img src="images/exit.png"></div>
		<div class="icon_arrow"><img src="images/arrow2.png"></div>
	</div>
	
	 -->
	
	<!-- 뉴스피드 보여주기  -->
	
	<!-- 
	
	<div class="board_header">
			<span class="board_category"></span><span class="board_name">정지혁</span><span class="board_subscribe">구독하기</span>
		</div>
		<hr>
		<div class="board_photo">
			<img src="images/play.png">
		</div>
		<hr>
		<div class="board_hash">`
			<span>#놀이공원</span><span>#롯데월드</span>
		</div>
		<hr>
		<div class="board_content">
			<p>오랜만에 롯데월드 데이트~!</p>
		</div>
	</div>
	 -->
<div class="feed">
	<c:forEach var="board" items="${list}">
		<div class="board_header">
			<span class="board_user">${board.userName}</span><span class="board_category">${board.category}</span><span class="board_present">${board.present}</span>
		</div>
		<div class="board_photo">
			<a href=""><img src="/GrangT/upload/${board.photoRealName}"></a>
		</div>
		<div class="board_hash"><span class="board_hash">#${board.hash}</span></div>
		<div class="board_content">
			<p>${board.content}</p>
		</div>
		<br>
		<hr>
	</c:forEach>	
	
	
	<div class="blank"></div>
</div>
	
	<input type="button" value="더보기" class="show_more" onclick="location.href='selectAllBoard.act'">

	<%@ include file="bottomMenu.jsp" %>
	<div id="search">
    	<button type="button" class="close">×</button>
    	<form action="search.act" method="post">
       	 	<input type="search" id="hash" onkeyup="searchFunction()" placeholder="해시 검색" />
       		<button type="submit" class="btn btn-primary" onclick="searchFunction();" >검색</button>
    	</form>
	</div>
</div>	


</body>
</html>