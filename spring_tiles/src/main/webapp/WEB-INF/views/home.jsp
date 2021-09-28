<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
<title>Home</title>
<style>
	#header{            
	    width:100%;
	    height:50px;
	    text-align: center;
	    background-color: aqua;
	}
	#left{
	    float:left;
	     width:15%;
	    background-color: gray;
	}
	#main{
	    float:left;
	     width:85%;
	    background-color: lime;
	}
	#footer{
	    width: 100%;
	    height: 50px;            
	    text-align: center;
	    background-color: orange;
	    clear:both;
	}
	 #left, #main{ 
	       min-height: 600px;
	 } 
</style>
</head>
<body>
	<div style="width: 100%; height: 100%;">
		<div id="header">
			<%@include file="/WEB-INF/tiles/header.jsp" %>
		</div>
		<div id="left">
			<%@include file="/WEB-INF/tiles/aside.jsp" %>
		</div>
		
		<div id="main">
			<h1>content main</h1>
		</div>
		<div id="footer">
			<%@include file="/WEB-INF/tiles/footer.jsp" %>
		</div>
	</div>	
</body>
</html>
