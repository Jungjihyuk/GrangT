<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>해시 검색</title>
<meta name="viewport" content="width=device-width; initial-scale=1.0"/>
<link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script></head>
<script src="js/search.js"></script>
<link href="css/search.css" rel="stylesheet">
<script type="text/javascript">
	var request=new XMLHttpRequest();
	function searchFunction(){
		request.open("Post","./search.act?hash="+encodeURIComponent(document.getElementById("hash").value),true);
		request.onreadystatechange=searchProcess;
		request.send(null);
	}
	function searchProcess(){
		var feed=document.getElementById("ajaxFeed");
		feed.innerHTML="";
		if(request.readyState==4 && request.status==200){
			
			
			var object=eval('('+request.responseText+')');
			var result=object.result;
			for(var i=0; i<result.length; i++){
				var row=table.insertRow(0);
				for(var j=0; j<result[i].length; j++){
					var cell=row.insertCell(j);
					cell.innerHTML=result[i][j].value;
				}
			}
		}
	}
	window.onload=function(){
		searchFunction();
	}
	
</script>
<body>
<!------ Include the above in your HEAD tag ---------->

<div id="icon search">
	<a href="#search" class="footer_icon"><img src="images/searchtool.png"></a><br>
	<span class="text">Search</span>
</div>

<div id="search">
    <button type="button" class="close">×</button>
    <form action="search.act" method="post">
        <input type="search" value="" placeholder="해시 검색" />
    </form>
</div>
<div class="feed" id="ajaxFeed">

</div>
</body>
</html>