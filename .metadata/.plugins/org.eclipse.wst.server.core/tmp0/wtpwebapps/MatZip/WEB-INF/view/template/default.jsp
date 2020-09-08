<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<link rel="stylesheet" type="text/css" href="/res/css/base.css">
	</head>
	<body>
		<div id="container">
			<jsp:include page="/WEB-INF/view/${view}.jsp"></jsp:include>	
		</div>
	</body>
</html>